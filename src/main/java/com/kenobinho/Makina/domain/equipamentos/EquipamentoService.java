package com.kenobinho.Makina.domain.equipamentos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


// regras de negocio
@Service
public class EquipamentoService {

    //construtor
    private final EquipamentoRepository equipamentoRepository;
    public EquipamentoService(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;

    }


    //cadastrar o equipamento no sistema
    @Transactional
    public Equipamento cadastrarEquipamento(Equipamento equipamento) {

        if (equipamento.getSetorOrigem() != null) {
            equipamento.setSetorOrigem(equipamento.getSetorOrigem().trim().toUpperCase());
        }

        return equipamentoRepository.save(equipamento);
    }

    // listar todos os equipamentos
public List<Equipamento> listarTodosEquipamentos() {
        return equipamentoRepository.findAll();
    }

    public Equipamento  buscarPorId (Long id) {
        return equipamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado com ID: " + id ));
        }

}