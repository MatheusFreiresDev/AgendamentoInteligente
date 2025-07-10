package com.SistemaAgendamento.Agendamento.Agendamento;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@Tag(name = "Agendamentos", description = "Endpoints para gerenciar agendamentos médicos")
public class AgendamentoController {

    AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    @Operation(summary = "Criar agendamento", description = "Cria um novo agendamento com os dados fornecidos")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar o agendamento"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<?> criarAgendamento(@RequestBody AgendamentoDTO agendamentoDTO) {
        AgendamentoDTO novoAgendamento = agendamentoService.adicionarAgendamento(agendamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Agendamento de " + agendamentoDTO.getTitulo() + " (" + agendamentoDTO.getId() + ") foi criado com sucesso.");
    }

    @GetMapping
    @Operation(summary = "Listar todos os agendamentos", description = "Retorna todos os agendamentos cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de agendamentos retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<?> listarAgendamentos(){
        List<AgendamentoDTO> list = agendamentoService.lista();
        if(list.isEmpty()) {
            return ResponseEntity.ok("Ainda nao existem cadastros.");
        } else {
            List<AgendamentoDTO> lista = agendamentoService.lista();
            return ResponseEntity.ok(lista);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar agendamento por ID", description = "Retorna os dados de um agendamento específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agendamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<?> listarId(@PathVariable Long id) {
        AgendamentoDTO agendamentoDTO = agendamentoService.listarID(id);
        if(agendamentoDTO != null) {
            return ResponseEntity.ok(agendamentoDTO);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O id nao existe.");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar agendamento", description = "Atualiza os dados de um agendamento existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agendamento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<?> atualizar(@PathVariable long id, @RequestBody AgendamentoDTO agendamentoDTO)  {
        AgendamentoDTO agendamentoDTO1 = agendamentoService.listarID(id);
        if(agendamentoDTO1 != null) {
            agendamentoService.atualizar(id,agendamentoDTO);
            return ResponseEntity.ok(agendamentoDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O id nao existe.");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar agendamento", description = "Remove um agendamento existente do sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agendamento removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<?> deletar(@PathVariable long id) {
        AgendamentoDTO agendamentoDTO = agendamentoService.listarID(id);
        if(agendamentoDTO != null) {
            agendamentoService.deletar(id);
            return ResponseEntity.ok(agendamentoDTO.getTitulo() + " - foi deletado.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O id - " + id + " nao existe no nosso sistema.");
        }
    }
}
