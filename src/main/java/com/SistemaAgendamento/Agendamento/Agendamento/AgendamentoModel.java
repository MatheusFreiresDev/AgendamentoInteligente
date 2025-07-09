package com.SistemaAgendamento.Agendamento.Agendamento;
import java.time.LocalDateTime;
import java.util.List;

import com.SistemaAgendamento.Agendamento.Paciente.PacienteModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_Agendamentos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AgendamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private LocalDateTime dataHoraInicio;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "agendamentos")
    @JsonIgnore
    private List<PacienteModel> listaPacientes;
}
