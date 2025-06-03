package br.com.estacionamento.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estacionamento.models.Acesso;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long> {
    public List<Acesso> findByEntradaBetween(Date inicio, Date fim);
    public List<Acesso> findBySaidaBetween(Date inicio, Date fim);

    public List<Acesso> findBySaidaIsNull();


}
