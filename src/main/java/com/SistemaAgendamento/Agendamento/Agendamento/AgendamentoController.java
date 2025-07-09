package com.SistemaAgendamento.Agendamento.Agendamento;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping("/criar") // Corrigido: usa path direto
    public ResponseEntity<?> criarAgendamento(@RequestBody AgendamentoDTO agendamentoDTO) {
        AgendamentoDTO novoAgendamento = agendamentoService.adicionarAgendamento(agendamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Agendamento de " + agendamentoDTO.getTitulo() + " (" + agendamentoDTO.getId() + ") foi criado com sucesso.");
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarAgendamentos(){
        List<AgendamentoDTO> list = agendamentoService.lista();
        if(list.isEmpty()) {
            return ResponseEntity.ok("Ainda nao existem cadastros.");
        } else {
            List<AgendamentoDTO> lista = agendamentoService.lista();
            return ResponseEntity.ok(lista);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarId(@PathVariable Long id) {
        AgendamentoDTO agendamentoDTO = agendamentoService.listarID(id);
        if(agendamentoDTO != null) {
            return ResponseEntity.ok(agendamentoDTO);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O id nao existe.");
        }
    }

    @PutMapping("/atualizar/{id}")
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

    @DeleteMapping("/deletar/{id}")
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
