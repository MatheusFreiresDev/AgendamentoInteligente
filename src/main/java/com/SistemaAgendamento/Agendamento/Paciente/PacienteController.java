package com.SistemaAgendamento.Agendamento.Paciente;

import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<String> criar(@RequestBody PacienteDTO pacienteDTO) {
        pacienteService.criar(pacienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Paciente " + pacienteDTO.getNome() + " foi registrado.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> alterar(@RequestBody PacienteDTO pacienteDTO, @PathVariable Long id) {
        PacienteDTO existente = pacienteService.listarId(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente com id " + id + " não encontrado.");
        }
        pacienteService.atualizar(id, pacienteDTO);
        return ResponseEntity.ok("Paciente " + pacienteDTO.getNome() + " foi atualizado.");
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        List<PacienteDTO> lista = pacienteService.lista();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 quando vazio
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        PacienteDTO pacienteDTO = pacienteService.listarId(id);
        if (pacienteDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente com id " + id + " não encontrado.");
        }
        return ResponseEntity.ok(pacienteDTO);
    }

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

    @PatchMapping("/confirmar/{id}")
    public ResponseEntity<String> confirmar(@PathVariable Long id) {
        PacienteDTO pacienteDTO = pacienteService.listarId(id);
        if (pacienteDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente com id " + id + " não encontrado.");
        }
        AgendamentoDTO agendamentoDTO = pacienteDTO.getAgendamentos();
        if (agendamentoDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Paciente não possui agendamento para confirmar.");
        }
        pacienteService.confirmar(id);
        return ResponseEntity.ok("Agendamento de " + agendamentoDTO.getTitulo() + " foi confirmado.");
    }

    @PatchMapping("/cancelar/{id}")
    public ResponseEntity<String> cancelar(@PathVariable Long id) {
        PacienteDTO pacienteDTO = pacienteService.listarId(id);
        if (pacienteDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente com id " + id + " não encontrado.");
        }
        AgendamentoDTO agendamentoDTO = pacienteDTO.getAgendamentos();
        if (agendamentoDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Paciente não possui agendamento para cancelar.");
        }
        pacienteService.cancelar(id);
        return ResponseEntity.ok("Agendamento de " + agendamentoDTO.getTitulo() + " foi cancelado.");
    }

    @PatchMapping("/adicionarAgendamento/{idPaciente}/{idAgendamento}")
    public ResponseEntity<String> adicionarAgendamento(@PathVariable Long idPaciente, @PathVariable Long idAgendamento) {
        String resultado = pacienteService.adicionarAgendamento(idPaciente, idAgendamento);
        if (resultado.toLowerCase().contains("foi adicionado")) {
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
    }

    @PatchMapping("/removerAgendamento/{idPaciente}")
    public ResponseEntity<String> removerAgendamento(@PathVariable Long idPaciente) {
        String resultado = pacienteService.removerAgendamento(idPaciente);
        if (resultado.toLowerCase().contains("foi removido")) {
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
    }

}
