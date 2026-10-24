package com.kenobinho.Makina.domain.usuarios;




//quando eu cadastrar um usuario vai devolver o id e login criados sem senha
public record DadosDetalhamentoUsuario(Long id, String login, PerfilUsuario perfil) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getPerfil());
    }
}

