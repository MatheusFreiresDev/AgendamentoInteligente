package com.SistemaAgendamento.Agendamento.Paciente;

import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {
    public PacienteDTO map(PacienteModel pacienteModel) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(pacienteModel.getId());
        pacienteDTO.setNome(pacienteModel.getNome());
        pacienteDTO.setAgendamentos(pacienteModel.getAgendamentos());
        return pacienteDTO;
    }
    public PacienteModel map(PacienteDTO pacienteDTO) {
        PacienteModel pacienteModel = new PacienteModel();
        pacienteModel.setId(pacienteDTO.getId());
        pacienteModel.setNome(pacienteDTO.getNome());
        pacienteModel.setAgendamentos(pacienteDTO.getAgendamentos());
        return pacienteModel;
    }
}
