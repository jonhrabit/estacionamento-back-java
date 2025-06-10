package br.com.estacionamento.models;

import java.io.Serializable;
import java.util.List;

import br.com.estacionamento.DTO.CadastroDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Pessoa")
public class Pessoa implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome, numFunc, tipoVinculo, cargo, fgOuCc, lotacao, ramal, email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Veiculo> veiculos;

    public Pessoa(CadastroDTO cadastro) {
        this.nome = cadastro.nome();
        this.numFunc = cadastro.numFunc();
        this.tipoVinculo = cadastro.tipoVinculo();
        this.cargo = cadastro.cargo();
        this.fgOuCc = cadastro.fgOuCc();
        this.lotacao = cadastro.lotacao();
        this.ramal = cadastro.ramal();
        this.email = cadastro.email();
    }

}
