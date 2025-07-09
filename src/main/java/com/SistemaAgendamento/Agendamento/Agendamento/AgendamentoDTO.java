package com.SistemaAgendamento.Agendamento.Agendamento;

import com.SistemaAgendamento.Agendamento.Paciente.PacienteModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDTO {
    private Long id;
    private String titulo;
    private LocalDateTime dataHoraInicio;
    private Status status;
    private List<PacienteModel> listaPacientes;

}
