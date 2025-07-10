package com.SistemaAgendamento.Agendamento.Medicos;

import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoDTO;
import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoModel;
import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoRepository;
import com.SistemaAgendamento.Agendamento.Agendamento.Status;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MedicoService {
    MedicoMapper medicoMapper;
    MedicoRepository medicoRepository ;
    AgendamentoRepository agendamentoRepository;

    public MedicoService(MedicoMapper medicoMapper, AgendamentoRepository agendamentoRepository, MedicoRepository medicoRepository) {
        this.medicoMapper = medicoMapper;
        this.agendamentoRepository = agendamentoRepository;
        this.medicoRepository = medicoRepository;
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





}
