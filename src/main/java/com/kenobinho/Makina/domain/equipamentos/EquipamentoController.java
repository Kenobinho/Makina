package com.kenobinho.Makina.domain.equipamentos;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos") //rota
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;


    @PostMapping // criar um equipamento
    public ResponseEntity<Equipamento> cadastrarEquipamento(@RequestBody @Valid DadosCadastroEquipamento dados) {
        Equipamento salvo = equipamentoService.cadastrarEquipamento(dados);
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
