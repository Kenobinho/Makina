package com.kenobinho.Makina.domain.ocorrencias;

import jakarta.validation.constraints.NotBlank;

public record DadosAberturaOcorrencia(
        @NotBlank(message = "Informe quem entregou o equipamento!")
        String entreguePor,

        // opcional
        String tecnicoResponsavel,

        @NotBlank(message = "O problema relatado é obrigatório!")
        String problemaRelatado
) {
}