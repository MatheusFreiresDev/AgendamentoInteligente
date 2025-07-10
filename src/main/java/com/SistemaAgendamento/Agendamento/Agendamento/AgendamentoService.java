package com.SistemaAgendamento.Agendamento.Agendamento;

import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmGeneratorSpecificationType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {
    AgendamentoMapper agendamentoMapper;
    AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, AgendamentoMapper agendamentoMapper) {
        this.agendamentoRepository = agendamentoRepository;
        this.agendamentoMapper = agendamentoMapper;
    }

    //Adicionar
    public AgendamentoDTO adicionarAgendamento(AgendamentoDTO agendamentoDTO) {
        agendamentoRepository.save(agendamentoMapper.map(agendamentoDTO));
        return agendamentoDTO;
    }

    //listar

    public List<AgendamentoDTO> lista() {
     List<AgendamentoModel> listaModel = agendamentoRepository.findAll();
     return listaModel.stream().map(agendamentoMapper::map).collect(Collectors.toList());
    }

    // listar id
    public AgendamentoDTO listarID(Long id){
        Optional<AgendamentoModel> opicional = agendamentoRepository.findById(id);
       return opicional.map(agendamentoMapper::map).orElse(null);
    }

    // Atualizar Agendamento
    public AgendamentoDTO atualizar(Long id,AgendamentoDTO agendamentoDTO) {
        agendamentoDTO.setId(id);
        agendamentoRepository.save(agendamentoMapper.map(agendamentoDTO));
        return agendamentoDTO;

    }

    public void deletar (long id){
        agendamentoRepository.deleteById(id);
    }
    public AgendamentoDTO confimarAgendamento (long id) {
        AgendamentoDTO agendamentoDTO = agendamentoMapper.map( agendamentoRepository.findById(id).orElse(null));
        agendamentoDTO.setStatus(Status.CONFIRMADO);
        agendamentoRepository.save(agendamentoMapper.map(agendamentoDTO));
        return agendamentoDTO;
    }

    public AgendamentoDTO cancelarAgendamento (long id) {
        AgendamentoDTO agendamentoDTO = agendamentoMapper.map( agendamentoRepository.findById(id).orElse(null));
        agendamentoDTO.setStatus(Status.CANCELADO);
        agendamentoRepository.save(agendamentoMapper.map(agendamentoDTO));
        return agendamentoDTO;
    }

}
