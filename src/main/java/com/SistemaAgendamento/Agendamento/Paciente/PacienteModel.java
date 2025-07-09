package com.SistemaAgendamento.Agendamento.Paciente;

import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_Pacientes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PacienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "agendamentos_id")
    private AgendamentoModel agendamentos;
}
