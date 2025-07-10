package com.SistemaAgendamento.Agendamento.Medicos;

import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoDTO;
import com.SistemaAgendamento.Agendamento.Agendamento.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
@Tag(name = "Médicos", description = "Endpoints para gerenciar médicos e seus agendamentos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @Operation(summary = "Criar um médico", description = "Cria um novo médico com os dados fornecidos")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Médico criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<MedicoDTO> criar(@RequestBody MedicoDTO medicoDTO) {
        MedicoDTO criado = medicoService.criar(medicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @Operation(summary = "Listar todos os médicos", description = "Retorna uma lista com todos os médicos cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum médico cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<?> listar() {
        List<MedicoDTO> lista = medicoService.listar();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum médico registrado.");
        }
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Buscar médico por ID", description = "Retorna os dados de um médico com base no ID fornecido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Médico encontrado"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@Parameter(description = "ID do médico") @PathVariable Long id) {
        MedicoDTO medicoDTO = medicoService.listarPorId(id);
        if (medicoDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico com id " + id + " não encontrado.");
        }
        return ResponseEntity.ok(medicoDTO);
    }

    @Operation(summary = "Deletar médico por ID", description = "Remove um médico com base no ID fornecido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Médico removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@Parameter(description = "ID do médico") @PathVariable Long id) {
        MedicoDTO medicoDTO = medicoService.listarPorId(id);
        if (medicoDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico com id " + id + " não encontrado.");
        }
        medicoService.deletar(id);
        return ResponseEntity.ok("Médico com id " + id + " removido com sucesso.");
    }

    @Operation(summary = "Atender próximo agendamento pendente", description = "Retorna o próximo agendamento pendente e marca como atendido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agendamento atendido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum agendamento pendente encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PatchMapping("/atender")
    public ResponseEntity<?> atender() {
        List<AgendamentoDTO> listaPendentes = medicoService.agendamentoRepository
                .findByStatusOrderByDataHoraInicioAsc(Status.PENDENTE)
                .stream()
                .map(medicoService.agendamentoMapper::map)
                .toList();

        if (listaPendentes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum agendamento pendente encontrado.");
        }

        AgendamentoDTO agendamentoDTO = medicoService.atender();
        return ResponseEntity.ok(agendamentoDTO);
    }
}
