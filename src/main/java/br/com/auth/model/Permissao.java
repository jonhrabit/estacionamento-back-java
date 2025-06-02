package br.com.auth.model;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Permissao implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private PermissoesTipo nome;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return nome.name();
    }

    public static Permissao fromString(String nome) {
        return new Permissao(null, PermissoesTipo.fromString(nome));
    }

}
