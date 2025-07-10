package com.SistemaAgendamento.Agendamento.Paciente;

import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoDTO;
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
@RequestMapping("/pacientes")
@Tag(name = "Pacientes", description = "Endpoints para gerenciar pacientes médicos")

public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    @Operation(summary = "Criar um Paciente", description = "Cria um novo Paciente com os dados informados")
    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cria um paciente."),
            @ApiResponse(responseCode = "400", description = "Erro ao criar um paciente."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor."

            )

    })
    public ResponseEntity<String> criar(@RequestBody PacienteDTO pacienteDTO ) {
        pacienteService.criar(pacienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Paciente " + pacienteDTO.getNome() + " foi registrado.");
    }


    @Operation(summary = "Altera um Paciente", description = "Altera um paciente com os dados informados")
    @PutMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Altera um paciente."),
            @ApiResponse(responseCode = "400", description = "Erro ao Alterar um paciente."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor."),
            @ApiResponse(responseCode = "404", description = "Paciente nao entrado.")

    })
    public ResponseEntity<String> alterar(@RequestBody PacienteDTO pacienteDTO,@PathVariable Long id) {
        PacienteDTO existente = pacienteService.listarId(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente com id " + id + " não encontrado.");
        }
        pacienteService.atualizar(id, pacienteDTO);
        return ResponseEntity.ok("Paciente " + pacienteDTO.getNome() + " foi atualizado.");
    }
    @Operation(summary = "Lista todos os Pacientes", description = "lista todos os pacientes cadastrados")
    @GetMapping

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "lista todos os pacientes."),
            @ApiResponse(responseCode = "400", description = "Erro ao listar os pacientes."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor."),
            @ApiResponse(responseCode = "204", description = "Nenhum paciente encontrado.")


    })
    public ResponseEntity<?> listar() {
        List<PacienteDTO> lista = pacienteService.lista();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 quando vazio
        }
        return ResponseEntity.ok(lista);
    }


    @Operation(summary = "Pesquisa um Paciente", description = "Pesquisa um Paciente com os dados informados")
    @GetMapping("/{id}")

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "lista um paciente."),
            @ApiResponse(responseCode = "400", description = "Erro ao lista um paciente."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor."),
            @ApiResponse(responseCode = "404", description = "Paciente nao entrado.")

    })
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        PacienteDTO pacienteDTO = pacienteService.listarId(id);
        if (pacienteDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente com id " + id + " não encontrado.");
        }
        return ResponseEntity.ok(pacienteDTO);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "deleta um paciente."),
            @ApiResponse(responseCode = "400", description = "Erro ao deletar um paciente."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor."),
            @ApiResponse(responseCode = "404", description = "Paciente nao entrado.")

    })
    @Operation(summary = "Deleta um Paciente", description = "Deleta um paciente com os dados informados")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        PacienteDTO pacienteDTO = pacienteService.listarId(id);
        if (pacienteDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente com id " + id + " não encontrado.");
        }
        pacienteService.deletar(id);
        return ResponseEntity.ok("Paciente " + pacienteDTO.getNome() + " foi removido dos registros.");
    }



    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Adiciona um Agendamento a um paciente."),
            @ApiResponse(responseCode = "400", description = "Erro ao Adicionar um Agendamento a um paciente."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor."),
            @ApiResponse(responseCode = "404", description = "Paciente/Agendamento nao entrado.")

    })
    @Operation(summary = "Adiciona um Agendamento ao Paciente", description = "Adiciona um Agendamneto a um paciente com os dados informados")
    @PatchMapping("/adicionarAgendamento/{idPaciente}/{idAgendamento}")
    public ResponseEntity<String> adicionarAgendamento(@PathVariable Long idPaciente, @PathVariable Long idAgendamento) {
        String resultado = pacienteService.adicionarAgendamento(idPaciente, idAgendamento);
        if (resultado.toLowerCase().contains("foi adicionado")) {
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
    }

    @Operation(summary = "Remove um Agendamento ao Paciente", description = "Remove um Agendamneto a um paciente com os dados informados")
    @PatchMapping("/removerAgendamento/{idPaciente}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Remove um Agendamento a um paciente."),
            @ApiResponse(responseCode = "400", description = "Erro ao Remover um Agendamento a um paciente."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor."),
            @ApiResponse(responseCode = "404", description = "Paciente/Agendamento nao entrado.")

    })
    public ResponseEntity<String> removerAgendamento(@PathVariable Long idPaciente) {
        String resultado = pacienteService.removerAgendamento(idPaciente);
        if (resultado.toLowerCase().contains("foi removido")) {
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
    }

}
