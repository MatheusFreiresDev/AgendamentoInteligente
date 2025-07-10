package com.SistemaAgendamento.Agendamento.Medicos;

import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoDTO;
import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoMapper;
import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoModel;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO {
    private Long id;
    private String nome;
    private List<AgendamentoModel> listaAgendamentos;
}
