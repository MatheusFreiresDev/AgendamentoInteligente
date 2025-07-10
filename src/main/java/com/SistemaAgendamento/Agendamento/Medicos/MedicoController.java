package com.SistemaAgendamento.Agendamento.Medicos;

import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoDTO;
import com.SistemaAgendamento.Agendamento.Agendamento.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> criar(@RequestBody MedicoDTO medicoDTO) {
        MedicoDTO criado = medicoService.criar(medicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        List<MedicoDTO> lista = medicoService.listar();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body
                    ("Nenhum medico registrado."
            ); // 204 se vazio
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        MedicoDTO medicoDTO = medicoService.listarPorId(id);
        if (medicoDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Médico com id " + id + " não encontrado.");
        }
        return ResponseEntity.ok(medicoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        MedicoDTO medicoDTO = medicoService.listarPorId(id);
        if (medicoDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Médico com id " + id + " não encontrado.");
        }
        medicoService.deletar(id);
        return ResponseEntity.ok("Médico com id " + id + " removido com sucesso.");
    }

    @PatchMapping("/atender")
    public ResponseEntity<?> atender() {
        List<AgendamentoDTO> listaPendentes = medicoService.agendamentoRepository.findByStatusOrderByDataHoraInicioAsc(Status.PENDENTE)
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
