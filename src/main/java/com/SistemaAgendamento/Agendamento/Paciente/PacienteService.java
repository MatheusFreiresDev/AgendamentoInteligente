package com.SistemaAgendamento.Agendamento.Paciente;

public class PacienteService {
    PacienteRepository pacienteRepository;
    PacienteMapper pacienteMapper;

    public PacienteService(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
    }


}
