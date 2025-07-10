package com.SistemaAgendamento.Agendamento.Medicos;

import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {

    public MedicoDTO map(MedicoModel medicoModel) {
        if (medicoModel == null) return null;
        MedicoDTO dto = new MedicoDTO();
        dto.setId(medicoModel.getId());
        dto.setNome(medicoModel.getNome());
        dto.setListaAgendamentos(medicoModel.getListaAgendamentos());
        return dto;
    }

    public MedicoModel map(MedicoDTO medicoDTO) {
        if (medicoDTO == null) return null;
        MedicoModel model = new MedicoModel();
        model.setId(medicoDTO.getId());
        model.setNome(medicoDTO.getNome());
        model.setListaAgendamentos(medicoDTO.getListaAgendamentos());
        return model;
    }
}
