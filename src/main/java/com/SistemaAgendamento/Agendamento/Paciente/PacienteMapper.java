package com.SistemaAgendamento.Agendamento.Paciente;

import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoMapper;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {
    private final AgendamentoMapper agendamentoMapper;

    public PacienteMapper(AgendamentoMapper agendamentoMapper) {
        this.agendamentoMapper = agendamentoMapper;
    }

    public PacienteDTO map(PacienteModel pacienteModel) {
        if (pacienteModel == null) return null;

        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(pacienteModel.getId());
        pacienteDTO.setNome(pacienteModel.getNome());
        pacienteDTO.setAgendamento(agendamentoMapper.map(pacienteModel.getAgendamento()));

        return pacienteDTO;
    }

    public PacienteModel map(PacienteDTO pacienteDTO) {
        if (pacienteDTO == null) return null;

        PacienteModel pacienteModel = new PacienteModel();
        pacienteModel.setId(pacienteDTO.getId());
        pacienteModel.setNome(pacienteDTO.getNome());
        pacienteDTO.setAgendamento(agendamentoMapper.map(pacienteModel.getAgendamento()));
        return pacienteModel;
    }
}
