package br.com.auth.model;

import java.util.List;

public enum PermissoesTipo {
    BASIC("Edições Básicas"),
    ADMIN("Criação de Usuários e Edições Avançadas"),;


    private String tipo;

    PermissoesTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
    @Override
    public String toString() {
        return name();
    }
    public static PermissoesTipo fromString(String tipo) {
        for (PermissoesTipo permissao : PermissoesTipo.values()) {
            if (permissao.tipo.equalsIgnoreCase(tipo)) {
                return permissao;
            }
        }
        throw new IllegalArgumentException("Permissão inválida: " + tipo);
    }
    public static List<PermissoesTipo> all() {
        return List.of(PermissoesTipo.values());
    }

}
