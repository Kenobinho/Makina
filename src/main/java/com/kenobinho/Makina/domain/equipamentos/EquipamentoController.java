package com.kenobinho.Makina.domain.equipamentos;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos") //rota
@RequiredArgsConstructor
public class EquipamentoController {

    private final EquipamentoService equipamentoService;


    @PostMapping // criar um equipamento
    public ResponseEntity<Equipamento> cadastrarEquipamento(@RequestBody Equipamento equipamento) {
        Equipamento salvo = equipamentoService.cadastrarEquipamento(equipamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping// listar equipamentos
    public ResponseEntity<List<Equipamento>> listarTodosEquipamentos() {
        return ResponseEntity.ok(equipamentoService.listarTodosEquipamentos());
    }



    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscarEquipamentoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(equipamentoService.buscarPorId(id));
    }
}
