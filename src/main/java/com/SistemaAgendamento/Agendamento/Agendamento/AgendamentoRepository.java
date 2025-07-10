package com.SistemaAgendamento.Agendamento.Agendamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<AgendamentoModel, Long> {
    List<AgendamentoModel> findByStatusOrderByDataHoraInicioAsc(Status status);
}
