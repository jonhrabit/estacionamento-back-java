package br.com.estacionamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.estacionamento.models.Pessoa;
import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    public List<Pessoa> findByCargo(String cargo);

    public List<Pessoa> findByLotacaoContains(String lotacao);

    public List<Pessoa> findByTipoVinculo(String tipoVinculo);

    public List<Pessoa> findByNomeContains(String nome);

    Optional<Pessoa> findByNome(String nome);

    @Query("SELECT p.cargo FROM Pessoa AS p GROUP BY p.cargo")
    public List<Object> getCargos();

    
}
