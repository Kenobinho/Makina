package com.kenobinho.Makina.domain.ocorrencias;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencias")
@RequiredArgsConstructor
public class OcorrenciaController {

    private final OcorrenciaService ocorrenciaService;


    // abrir uma ocorrencia
    @PostMapping
    public ResponseEntity<Ocorrencia> abrirOcorrencia(
            @PathVariable Long equipamentoId,
            @RequestBody Ocorrencia novaOcorrencia) {
        Ocorrencia criada =  ocorrenciaService.abrirOcorrencia(equipamentoId, novaOcorrencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }


    @GetMapping // pega todas as ocorrencias
    public ResponseEntity<List<Ocorrencia>> listarTodosOcorrencias() {
        return ResponseEntity.ok(ocorrenciaService.listarTodasOcorrencias());
    }

    // pega as ocorrencias pelo id do equipamento
    @GetMapping("/equipamento/{equipamentoId}")
    public ResponseEntity<List<Ocorrencia>> ListarPorEquipamento (@PathVariable Long equipamentoId) {
        return ResponseEntity.ok(ocorrenciaService.listarPorEquipamento(equipamentoId));
    }


    @PatchMapping("/{id}/status") // atualizar status
    public ResponseEntity<Ocorrencia> atualizarStatus(@PathVariable Long id, @RequestBody AtualizarStatusDTO dados) {

        return ResponseEntity.ok(ocorrenciaService.atualizarStatus(id, dados.novoStatus()));

    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<Ocorrencia> finalizarOccorrencia(@PathVariable Long id, @RequestBody FinalizarOcorrenciaDTO dados){

        Ocorrencia finalizada = ocorrenciaService.finalizarOcorrencia(
                id, dados.solucaoAplicada(), dados.retiradoPor());
        return ResponseEntity.ok(finalizada);
    }

    //dto
    public record AtualizarStatusDTO(StatusOcorrencia novoStatus) {}
    public record FinalizarOcorrenciaDTO(String solucaoAplicada, String retiradoPor) {}

}
