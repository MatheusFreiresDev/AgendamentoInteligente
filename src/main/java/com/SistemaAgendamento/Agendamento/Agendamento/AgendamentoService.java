package com.SistemaAgendamento.Agendamento.Agendamento;

import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmGeneratorSpecificationType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {
    private final AgendamentoMapper agendamentoMapper;
    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, AgendamentoMapper agendamentoMapper) {
        this.agendamentoRepository = agendamentoRepository;
        this.agendamentoMapper = agendamentoMapper;
    }

    public AgendamentoDTO adicionarAgendamento(AgendamentoDTO agendamentoDTO) {
        agendamentoRepository.save(agendamentoMapper.map(agendamentoDTO));
        return agendamentoDTO;
    }

    public List<AgendamentoDTO> lista() {
        return agendamentoRepository.findAll()
                .stream()
                .map(agendamentoMapper::map)
                .collect(Collectors.toList());
    }

    public AgendamentoDTO listarID(Long id){
        return agendamentoRepository.findById(id)
                .map(agendamentoMapper::map)
                .orElse(null);
    }

    public AgendamentoDTO atualizar(Long id, AgendamentoDTO agendamentoDTO) {
        agendamentoDTO.setId(id);
        agendamentoRepository.save(agendamentoMapper.map(agendamentoDTO));
        return agendamentoDTO;
    }

    public void deletar(long id) {
        agendamentoRepository.deleteById(id);
    }

    public AgendamentoDTO confirmarAgendamento(Long id) {
        AgendamentoModel agendamento = agendamentoRepository.findById(id).orElse(null);
        if (agendamento == null) return null;

        agendamento.setStatus(Status.CONFIRMADO);
        agendamentoRepository.save(agendamento);
        return agendamentoMapper.map(agendamento);
    }

    public AgendamentoDTO cancelarAgendamento(Long id) {
        AgendamentoModel agendamento = agendamentoRepository.findById(id).orElse(null);
        if (agendamento == null) return null;

        agendamento.setStatus(Status.CANCELADO);
        agendamentoRepository.save(agendamento);
        return agendamentoMapper.map(agendamento);
    }

}
