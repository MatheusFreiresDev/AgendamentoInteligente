package com.SistemaAgendamento.Agendamento.Agendamento;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO que representa os dados de um agendamento médico")
public class AgendamentoDTO {

    @Schema(description = "ID do agendamento", example = "1")
    private Long id;

    @Schema(description = "Título ou motivo do agendamento", example = "Consulta de rotina")
    private String titulo;

    @Schema(description = "Data e hora de início do agendamento", example = "2025-07-10T14:00:00")
    private LocalDateTime dataHoraInicio;

    @Schema(description = "Status do agendamento", example = "PENDENTE")
    private Status status;
}
