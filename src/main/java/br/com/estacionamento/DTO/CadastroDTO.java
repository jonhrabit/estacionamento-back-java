package br.com.estacionamento.DTO;

import java.util.Date;

public record CadastroDTO(
    // Pessoa
    Long idPessoa,
    String nome,
    String numFunc,
    String tipoVinculo,
    String cargo,
    String fgOuCc,
    String lotacao,
    String ramal,
    String email,
    
    String placa,
    String modelo,
    String cor,
    String foto,
    String observacao,
    boolean ativo,
    Date dataLimite,
    Date horario
) {}
