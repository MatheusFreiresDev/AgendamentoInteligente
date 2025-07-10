package com.SistemaAgendamento.Agendamento.Medicos;

import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "DTO que representa os dados de um médico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO {

    @Schema(description = "ID único do médico", example = "10")
    private Long id;

    @Schema(description = "Nome completo do médico", example = "Dra. Ana Beatriz")
    private String nome;

    @Schema(description = "Lista de agendamentos associados ao médico")
    private List<AgendamentoModel> listaAgendamentos;
}
