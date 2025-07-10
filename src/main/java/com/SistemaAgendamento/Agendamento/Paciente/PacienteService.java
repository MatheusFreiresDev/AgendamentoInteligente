package com.SistemaAgendamento.Agendamento.Paciente;

import com.SistemaAgendamento.Agendamento.Agendamento.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final AgendamentoRepository agendamentoRepository;
    private final AgendamentoService agendamentoService;
    private final PacienteMapper pacienteMapper;

    public PacienteService(PacienteRepository pacienteRepository, AgendamentoRepository agendamentoRepository,
                           AgendamentoService agendamentoService, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.agendamentoService = agendamentoService;
        this.pacienteMapper = pacienteMapper;
    }

    public PacienteDTO criar(PacienteDTO pacienteDTO) {
        // Pega o ID do agendamento do DTO
        Long agendamentoId = pacienteDTO.getAgendamento() != null ? pacienteDTO.getAgendamento().getId() : null;
        AgendamentoModel agendamento = null;
        if (agendamentoId != null) {
            // Busca o agendamento gerenciado do banco
            agendamento = agendamentoRepository.findById(agendamentoId).orElse(null);
        }

        // Mapeia o DTO pra entidade
        PacienteModel paciente = pacienteMapper.map(pacienteDTO);

        // Define o agendamento já gerenciado
        paciente.setAgendamento(agendamento);

        // Salva o paciente
        pacienteRepository.save(paciente);

        return pacienteDTO;
    }

    public List<PacienteDTO> lista() {
        return pacienteRepository.findAll().stream()
                .map(pacienteMapper::map)
                .toList();
    }

    public PacienteDTO listarId(Long id) {
        return pacienteMapper.map(pacienteRepository.findById(id).orElse(null));
    }

    public PacienteDTO atualizar(Long id, PacienteDTO pacienteDTO) {
        pacienteDTO.setId(id);
        pacienteRepository.save(pacienteMapper.map(pacienteDTO));
        return pacienteDTO;
    }

    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }

    public String confirmar(Long idPaciente) {
        PacienteModel paciente = pacienteRepository.findById(idPaciente).orElse(null);
        if (paciente == null) return "Paciente não encontrado";

        AgendamentoModel agendamento = paciente.getAgendamento();
        if (agendamento == null) return "Paciente não tem agendamento";

        agendamento.setStatus(Status.CONFIRMADO);
        agendamentoRepository.save(agendamento);

        return "Agendamento confirmado para " + paciente.getNome();
    }

    public String cancelar(Long idPaciente) {
        PacienteModel paciente = pacienteRepository.findById(idPaciente).orElse(null);
        if (paciente == null) return "Paciente não encontrado";

        AgendamentoModel agendamento = paciente.getAgendamento();
        if (agendamento == null) return "Paciente não tem agendamento";

        agendamento.setStatus(Status.CANCELADO);
        agendamentoRepository.save(agendamento);

        return "Agendamento cancelado para " + paciente.getNome();
    }

    public String proxAgendamento(Long idPaciente) {
        PacienteModel paciente = pacienteRepository.findById(idPaciente).orElse(null);
        if (paciente == null) return "Paciente não encontrado";

        AgendamentoModel agendamento = paciente.getAgendamento();
        if (agendamento == null) return "Paciente não tem agendamento";

        return paciente.getNome() + ", seu agendamento atual: " + agendamento.getTitulo();
    }

    public String removerAgendamento(Long idPaciente) {
        PacienteModel paciente = pacienteRepository.findById(idPaciente).orElse(null);
        if (paciente == null) return "Paciente não encontrado";

        AgendamentoModel agendamento = paciente.getAgendamento();
        if (agendamento == null) return "Paciente não possui agendamento para remover";

        if (agendamento.getStatus() == Status.PENDENTE) {
            return "Esse agendamento não pode ser removido pois está PENDENTE";
        }

        paciente.setAgendamento(null);
        pacienteRepository.save(paciente);

        // Opcional: força atualizar e limpar cache
        pacienteRepository.flush();

        return paciente.getNome() + ", seu agendamento foi removido.";
    }

    public String adicionarAgendamento(Long idPaciente, Long idAgendamento) {
        PacienteModel paciente = pacienteRepository.findById(idPaciente).orElse(null);
        if (paciente == null) return "Paciente não encontrado";

        if (paciente.getAgendamento() != null) {
            return "Você ainda possui um agendamento. Remova-o primeiro.";
        }

        AgendamentoModel agendamento = agendamentoRepository.findById(idAgendamento).orElse(null);
        if (agendamento == null) return "Agendamento não encontrado";

        agendamento.setStatus(Status.PENDENTE);
        paciente.setAgendamento(agendamento);

        pacienteRepository.save(paciente);

        return "O agendamento de " + agendamento.getTitulo() + " foi adicionado.";
    }
    }

