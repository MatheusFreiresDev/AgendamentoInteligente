package com.SistemaAgendamento.Agendamento.Paciente;

import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoDTO;
import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacienteDTO {
    private Long id;
    private String nome;
    private AgendamentoDTO agendamento;
}
