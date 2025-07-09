package com.SistemaAgendamento.Agendamento.Agendamento;

import org.springframework.stereotype.Component;

@Component
public class AgendamentoMapper {
    public AgendamentoModel map(AgendamentoDTO agendamentoDTO) {
        AgendamentoModel agendamentoModel = new AgendamentoModel();
        agendamentoModel.setId(agendamentoDTO.getId());
        agendamentoModel.setTitulo(agendamentoDTO.getTitulo());
        agendamentoModel.setDataHoraInicio(agendamentoDTO.getDataHoraInicio());
        agendamentoModel.setStatus(agendamentoDTO.getStatus());
        agendamentoModel.setListaPacientes(agendamentoDTO.getListaPacientes());
        return agendamentoModel;
    }
    public AgendamentoDTO map(AgendamentoModel agendamentoModel) {
        AgendamentoDTO agendamentoDTO = new AgendamentoDTO();
        agendamentoDTO.setId(agendamentoModel.getId());
        agendamentoDTO.setTitulo(agendamentoModel.getTitulo());
        agendamentoDTO.setDataHoraInicio(agendamentoModel.getDataHoraInicio());
        agendamentoDTO.setStatus(agendamentoModel.getStatus());
        agendamentoDTO.setListaPacientes(agendamentoModel.getListaPacientes());
        return agendamentoDTO;
    }
}
