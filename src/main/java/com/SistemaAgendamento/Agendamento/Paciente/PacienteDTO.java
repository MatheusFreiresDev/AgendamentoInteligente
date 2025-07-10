package com.SistemaAgendamento.Agendamento.Paciente;

import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoDTO;
import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacienteDTO {
    @Schema(description = "Id do paciente", example = "1")
    private Long id;
    @Schema(description = "Nome completo do paciente", example = "João Silva")
    private String nome;
    @Schema(description = "Agendamento do paciente")
    private AgendamentoDTO agendamento;
}
