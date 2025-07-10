package com.SistemaAgendamento.Agendamento.Paciente;

import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoDTO;
import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoMapper;
import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoService;
import com.SistemaAgendamento.Agendamento.Agendamento.Status;

import java.util.List;

public class PacienteService {
    PacienteRepository pacienteRepository;
    PacienteMapper pacienteMapper;
    AgendamentoService agendamentoService;

    public PacienteService(PacienteRepository pacienteRepository, AgendamentoService agendamentoService, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.agendamentoService = agendamentoService;
        this.pacienteMapper = pacienteMapper;
    }
    //confirmar agendamento
    // cancelar agendamento
    //mostar prox consulta



    public PacienteDTO criar(PacienteDTO pacienteDTO) {
        pacienteRepository.save(pacienteMapper.map(pacienteDTO));
        return pacienteDTO;
    }

    public List<PacienteDTO> lista() {
        List<PacienteDTO> lista = (pacienteRepository.findAll()).stream().map(pacienteMapper::map).toList();
        return lista;
    }
    public PacienteDTO listarId(Long id) {
        PacienteDTO pacienteDTO = pacienteMapper.map(pacienteRepository.findById(id).orElse(null));
        return pacienteDTO;
    }
    public PacienteDTO atualizar(Long id, PacienteDTO pacienteDTO) {
        pacienteDTO.setId(id);
        pacienteRepository.save(pacienteMapper.map(pacienteDTO));
        return pacienteDTO;
    }

    public void deletar (Long id) {
        pacienteRepository.deleteById(id);
    }
    public AgendamentoDTO confirmar (Long id){
        PacienteDTO pacienteDTO = pacienteMapper.map(pacienteRepository.findById(id).orElse(null));
        AgendamentoDTO agendamentoDTO = pacienteDTO.getAgendamentos();
        agendamentoService.confimarAgendamento(agendamentoDTO);
        agendamentoService.atualizar(agendamentoDTO.getId(),agendamentoDTO);
        return agendamentoDTO;
    }

    public AgendamentoDTO cancelar (Long id){
        PacienteDTO pacienteDTO = pacienteMapper.map(pacienteRepository.findById(id).orElse(null));
        AgendamentoDTO agendamentoDTO = pacienteDTO.getAgendamentos();
        agendamentoService.cancelarAgendamento(agendamentoDTO);
        agendamentoService.atualizar(agendamentoDTO.getId(),agendamentoDTO);
        return agendamentoDTO;
    }

}
