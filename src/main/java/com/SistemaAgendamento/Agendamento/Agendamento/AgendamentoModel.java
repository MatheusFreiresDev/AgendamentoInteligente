package com.SistemaAgendamento.Agendamento.Agendamento;
import java.time.LocalDateTime;
import java.util.List;

import com.SistemaAgendamento.Agendamento.Medicos.MedicoModel;
import com.SistemaAgendamento.Agendamento.Paciente.PacienteModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_Agendamentos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AgendamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private LocalDateTime dataHoraInicio;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.PENDENTE;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private MedicoModel medico;

}
