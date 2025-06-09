package br.com.estacionamento.models;

import java.io.Serializable;
import java.util.Date;

import br.com.estacionamento.DTO.CadastroDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String placa;
    private String modelo;
    private String cor;
    private String foto;
    private boolean temporario;
    private Date dataLimite;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Veiculo(CadastroDTO cadastroDTO) {
        this.id = cadastroDTO.idPessoa();
        this.placa = cadastroDTO.placa();
        this.modelo = cadastroDTO.modelo();
        this.cor = cadastroDTO.cor();
        this.foto = cadastroDTO.foto();
        this.temporario = cadastroDTO.temporario();
        this.dataLimite = cadastroDTO.dataLimite();
    }
}
