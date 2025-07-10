package com.SistemaAgendamento.Agendamento.Medicos;

import com.SistemaAgendamento.Agendamento.Agendamento.*;
import com.SistemaAgendamento.Agendamento.Paciente.PacienteDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MedicoService {
    MedicoMapper medicoMapper;
    MedicoRepository medicoRepository ;
    AgendamentoRepository agendamentoRepository;
    AgendamentoMapper agendamentoMapper;

    public MedicoService(MedicoMapper medicoMapper, AgendamentoMapper agendamentoMapper, MedicoRepository medicoRepository, AgendamentoRepository agendamentoRepository) {
        this.medicoMapper = medicoMapper;
        this.agendamentoMapper = agendamentoMapper;
        this.medicoRepository = medicoRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    public MedicoDTO criar(MedicoDTO medicoDTO) {
        MedicoModel model = medicoMapper.map(medicoDTO);
        medicoRepository.save(model);
        medicoDTO.setId(model.getId()); // garante que o ID gerado seja setado no DTO
        return medicoDTO;
    }

    // Listar todos
    public List<MedicoDTO> listar() {
        return medicoRepository.findAll()
                .stream()
                .map(medicoMapper::map)
                .toList();
    }

    // Listar por id
    public MedicoDTO listarPorId(Long id) {
        return medicoRepository.findById(id)
                .map(medicoMapper::map)
                .orElse(null);
    }

    // Remover por id
    public void deletar(Long id) {
        medicoRepository.deleteById(id);
    }

    public AgendamentoDTO atender() {
        List<AgendamentoDTO> lista = agendamentoRepository.findByStatusOrderByDataHoraInicioAsc(Status.PENDENTE).stream().map(agendamentoMapper::map).toList();
        AgendamentoDTO agendamentoDTO = lista.get(0);
        agendamentoDTO.setStatus(Status.CONFIRMADO);
        agendamentoRepository.save(agendamentoMapper.map(agendamentoDTO));
        return agendamentoDTO;
    }



}
