package com.SistemaAgendamento.Agendamento.Paciente;

import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/Criar")
    public ResponseEntity<?> criar (@RequestBody PacienteDTO pacienteDTO) {
        pacienteService.criar(pacienteDTO);
        return ResponseEntity.ok("Paciente " + pacienteDTO.getNome() + " foi registrado.");
    }
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterar(@RequestBody PacienteDTO pacienteDTO, @PathVariable Long id) {
        PacienteDTO pacienteDTO1 = pacienteService.listarId(id);
        if(pacienteDTO1 != null) {
            pacienteService.atualizar(id,pacienteDTO);
            return ResponseEntity.ok("O paciente " + pacienteDTO.getNome() + " Foi atualizado.");
        }
        else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O paciente no id " + id + " nao existe nos registros.");
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        List<PacienteDTO> list = pacienteService.lista();
        if(list.isEmpty()) {
            return ResponseEntity.ok("Ainda nao existe registros.");
        } else {
            return ResponseEntity.ok(list);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarid(@PathVariable Long id) {
        PacienteDTO optionalPacienteDTO = pacienteService.listarId(id);
        if(optionalPacienteDTO != null) {
            return ResponseEntity.ok(optionalPacienteDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O paciente no id " + id + " nao existe nos registros.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar (@PathVariable Long id) {
        PacienteDTO optionalPacienteDTO = pacienteService.listarId(id);
        if(optionalPacienteDTO != null) {
            pacienteService.deletar(id);
            return ResponseEntity.ok("O paciente " + optionalPacienteDTO.getNome() + " foi removido dos registros." );

        } else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O paciente no id " + id + " nao existe nos registros.");
        }
    }

    @PatchMapping("/confirmar/{id}")
    public ResponseEntity<?> confirmar (@PathVariable Long id) {
        PacienteDTO optionalPacienteDTO = pacienteService.listarId(id);
        if(optionalPacienteDTO != null) {
            AgendamentoDTO agendamentoDTO = optionalPacienteDTO.getAgendamentos();
            pacienteService.confirmar(id);
            return ResponseEntity.ok("O agendamento de " + agendamentoDTO.getTitulo() + " foi confirmado.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O paciente no id " + id + " nao existe nos registros.");
        }
    }

@PatchMapping("/cancelar/{id}")
public ResponseEntity<?> cancelar (@PathVariable Long id) {
    PacienteDTO optionalPacienteDTO = pacienteService.listarId(id);
    if(optionalPacienteDTO != null) {
        AgendamentoDTO agendamentoDTO = optionalPacienteDTO.getAgendamentos();
        pacienteService.cancelar(id);
        return ResponseEntity.ok("O agendamento de " + agendamentoDTO.getTitulo() + " foi cancelado.");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("O paciente no id " + id + " nao existe nos registros.");
    }
}

    @PatchMapping("/adicionarAgendamento/{idPaciente}/{idAgendamento}")
    public ResponseEntity<?> adicionarAgendamento(
            @PathVariable Long idPaciente,
            @PathVariable Long idAgendamento) {

        String resultado = pacienteService.adicionarAgendamento(idPaciente, idAgendamento);
        if (resultado.toLowerCase().contains("foi adicionado")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
        }
    }

    @PatchMapping("/removerAgendamento/{idPaciente}")
    public ResponseEntity<?> removerAgendamento(@PathVariable Long idPaciente) {
        String resultado = pacienteService.removerAgendamento(idPaciente);
        if (resultado.toLowerCase().contains("foi removido")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
        }
    }

}