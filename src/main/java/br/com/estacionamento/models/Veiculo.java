package br.com.estacionamento.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.estacionamento.DTO.CadastroDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
    private boolean ativo;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")

    private Date dataLimite;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(style = "HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "America/Sao_Paulo")
    private Date horario;
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Acesso> acessos;

    public Veiculo(CadastroDTO cadastroDTO) {
        this.id = null;
        this.placa = cadastroDTO.placa();
        this.modelo = cadastroDTO.modelo();
        this.cor = cadastroDTO.cor();
        this.foto = cadastroDTO.foto();
        this.observacao = cadastroDTO.observacao();
        this.ativo = cadastroDTO.ativo();
        this.dataLimite = cadastroDTO.dataLimite();
        this.horario = cadastroDTO.horario();
    }
}
