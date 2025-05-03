package br.com.estacionamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estacionamento.models.Acesso;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long> {

}
