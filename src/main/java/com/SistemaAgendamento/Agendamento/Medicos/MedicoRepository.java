package com.SistemaAgendamento.Agendamento.Medicos;

import com.SistemaAgendamento.Agendamento.Agendamento.AgendamentoModel;
import com.SistemaAgendamento.Agendamento.Agendamento.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicoRepository extends JpaRepository<MedicoModel,Long> {

}

