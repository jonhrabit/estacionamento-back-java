package br.com.auth.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.auth.model.Permissao;
import br.com.auth.model.Usuario;

public record UsuarioDTO(
        Long id, Date cadastro, String nome, String username, String email, String cpf,
        List<String> permissoes) {

    UsuarioDTO(Usuario usuario) {
        this(usuario.getId(),
                usuario.getCadastro(),
                usuario.getNome(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getPermissoes().stream().map(p -> p.getAuthority()).toList());
    }

    @JsonIgnore
    public Usuario getUsuario() {
        List<Permissao> usuarioPermissoes = new ArrayList<Permissao>();
        return Usuario.builder()
                .cadastro(this.cadastro)
                .nome(this.nome)
                .username(this.username)
                .email(this.email)
                .cpf(this.cpf)
                .permissoes(usuarioPermissoes)
                .build();

    }

}
