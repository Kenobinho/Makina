package com.kenobinho.Makina.domain.usuarios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(

        @NotBlank(message = "Login é obrigatório!")
        String login,

        @NotBlank(message = "Senha é obrigatória!")
        String senha,

        @NotNull(message = "O perfil é obrigatório!")
        PerfilUsuario perfil
) {
}
