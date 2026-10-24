package com.kenobinho.Makina.domain.ocorrencias;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@PostMapping("/equipamento/{equipamentoId}")
    public ResponseEntity<Ocorrencia> abrirOcorrencia(
            @PathVariable Long equipamentoId,
            @RequestBody @Valid DadosAberturaOcorrencia dados) {
        Ocorrencia criada =  ocorrenciaService.abrirOcorrencia(equipamentoId, dados);
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
    public ResponseEntity<Ocorrencia> atualizarStatus(@PathVariable Long id, @RequestBody @Valid AtualizarStatusDTO dados) {

        return ResponseEntity.ok(ocorrenciaService.atualizarStatus(id, dados.novoStatus()));

    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<Ocorrencia> finalizarOcorrencia(@PathVariable Long id, @RequestBody @Valid FinalizarOcorrenciaDTO dados){

        Ocorrencia finalizada = ocorrenciaService.finalizarOcorrencia(
                id, dados.solucaoAplicada(), dados.retiradoPor());
        return ResponseEntity.ok(finalizada);
    }

    //dto
    public record AtualizarStatusDTO(
            @NotNull(message = "O novo status é obrigatório!")
            StatusOcorrencia novoStatus
    ) {}
    public record FinalizarOcorrenciaDTO(
            @NotBlank(message = "A solução aplicada é obrigatória!")
            String solucaoAplicada,
            @NotBlank(message = "Informe quem retirou o equipamento!")
            String retiradoPor
    ) {}
}
