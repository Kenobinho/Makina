package com.kenobinho.Makina.domain.equipamentos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroEquipamento(

        // é um dado opcional
        String numeroPatrimonio,


        @NotBlank(message = "O setor de origem é obrigatório!")
        String setorOrigem,

        @NotNull(message = "O tipo do Equipamento é obrigatório!")
        TipoEquipamento tipoEquipamento,

        @NotBlank(message = "A descrição do equipamento é obrigatória!")
        String descricao
) {
}
