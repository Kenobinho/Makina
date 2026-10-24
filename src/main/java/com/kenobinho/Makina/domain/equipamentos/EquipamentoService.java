package com.kenobinho.Makina.domain.equipamentos;

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
    public Equipamento cadastrarEquipamento(DadosCadastroEquipamento dados) {
        Equipamento equipamento = new Equipamento();
        equipamento.setNumeroPatrimonio(dados.numeroPatrimonio());
        equipamento.setSetorOrigem(dados.setorOrigem().trim().toUpperCase());
        equipamento.setTipoEquipamento(dados.tipoEquipamento());
        equipamento.setDescricao(dados.descricao());
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