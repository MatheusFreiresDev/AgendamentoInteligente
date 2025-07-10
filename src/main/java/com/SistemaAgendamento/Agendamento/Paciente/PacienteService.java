package com.SistemaAgendamento.Agendamento.Paciente;

import com.SistemaAgendamento.Agendamento.Agendamento.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PacienteService {
    PacienteRepository pacienteRepository;
    PacienteMapper pacienteMapper;
    AgendamentoService agendamentoService;
    AgendamentoRepository agendamentoRepository;

    public PacienteService(PacienteRepository pacienteRepository, AgendamentoRepository agendamentoRepository, AgendamentoService agendamentoService, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.agendamentoService = agendamentoService;
        this.pacienteMapper = pacienteMapper;
    }

    public PacienteDTO criar(PacienteDTO pacienteDTO) {
        pacienteRepository.save(pacienteMapper.map(pacienteDTO));
        return pacienteDTO;
    }

    public List<PacienteDTO> lista() {
        List<PacienteDTO> lista = (pacienteRepository.findAll()).stream().map(pacienteMapper::map).toList();
        return lista;
    }

    public PacienteDTO listarId(Long id) {
        PacienteDTO pacienteDTO = pacienteMapper.map(pacienteRepository.findById(id).orElse(null));
        return pacienteDTO;
    }

    public PacienteDTO atualizar(Long id, PacienteDTO pacienteDTO) {
        pacienteDTO.setId(id);
        pacienteRepository.save(pacienteMapper.map(pacienteDTO));
        return pacienteDTO;
    }

    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }

    public AgendamentoDTO confirmar(Long id) {
        PacienteDTO pacienteDTO = pacienteMapper.map(pacienteRepository.findById(id).orElse(null));
        AgendamentoDTO agendamentoDTO = pacienteDTO.getAgendamentos();
        agendamentoService.confimarAgendamento(agendamentoDTO);
        agendamentoService.atualizar(agendamentoDTO.getId(), agendamentoDTO);
        return agendamentoDTO;
    }

    public AgendamentoDTO cancelar(Long id) {
        PacienteDTO pacienteDTO = pacienteMapper.map(pacienteRepository.findById(id).orElse(null));
        AgendamentoDTO agendamentoDTO = pacienteDTO.getAgendamentos();
        agendamentoService.cancelarAgendamento(agendamentoDTO);
        agendamentoService.atualizar(agendamentoDTO.getId(), agendamentoDTO);
        return agendamentoDTO;
    }

    public String proxAgendamento(Long id) {
        PacienteDTO pacienteDTO = pacienteMapper.map(pacienteRepository.findById(id).orElse(null));
        String agendamentoEmTexto = pacienteDTO.getNome() + "seu agendamento atual : " + pacienteDTO.getAgendamentos().getTitulo();
        return agendamentoEmTexto;
    }
    public String removerAgendamento(Long id){
        PacienteModel pacienteModel = pacienteRepository.findById(id).orElse(null);
        if (pacienteModel == null) {
            return "Paciente com id " + id + " não encontrado.";
        }

        if (pacienteModel.getAgendamentos() == null) {
            return "Esse paciente não possui nenhum agendamento para remover.";
        }
        if (pacienteModel.getAgendamentos().getStatus() == Status.PENDENTE) {
            return "Esse agendamento não está disponível pois esta Pendente";
        }

        pacienteModel.setAgendamentos(null);
        pacienteRepository.save(pacienteModel);

        // Força atualizar no banco e limpar cache JPA
        pacienteRepository.flush();

        return pacienteModel.getNome() + ", seu agendamento foi removido.";
    }

    public String adicionarAgendamento(Long idPaciente, Long idAgendamento) {
        PacienteModel pacienteModel = pacienteRepository.findById(idPaciente).orElse(null);
        if (pacienteModel == null) {
            return "Paciente com id " + idPaciente + " não encontrado.";
        }

        if (pacienteModel.getAgendamentos() != null) {
            return "Você ainda possui um agendamento. Remova-o primeiro.";
        }

        AgendamentoModel agendamentoModel = agendamentoRepository.findById(idAgendamento).orElse(null);
        if (agendamentoModel == null) {
            return "Agendamento com id " + idAgendamento + " não encontrado.";
        }
            agendamentoModel.setStatus(Status.PENDENTE);
            pacienteModel.setAgendamentos(agendamentoModel);
            pacienteRepository.save(pacienteModel);

            return "O agendamento de " + agendamentoModel.getTitulo() + " foi adicionado.";
        }

    }

