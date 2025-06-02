package br.com.auth.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.auth.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
    
}
