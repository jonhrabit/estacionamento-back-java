package br.com.estacionamento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.auth.exceptions.ItemNotFoundExcepion;
import br.com.estacionamento.models.Acesso;
import br.com.estacionamento.repositories.AcessoRepository;

@Service
public class AcessoService {
    @Autowired
    AcessoRepository acessoRepository;

    public Acesso get(Long id) throws ItemNotFoundExcepion {
        Optional<Acesso> acesso = acessoRepository.findById(id);
        return acesso.orElseThrow(() -> new ItemNotFoundExcepion(id, Acesso.class.getSimpleName()));
    }

    public List<Acesso> all() {
        return acessoRepository.findAll();
    }

    public Acesso update(Acesso acesso, Long id) throws ItemNotFoundExcepion {
        Acesso acessoOld = this.get(id);
        acessoOld = acesso;
        return acessoRepository.save(acessoOld);
    }

    public Acesso save(Acesso acesso) {
        return acessoRepository.save(acesso);
    }

    public void delete(Acesso acesso) {
        acessoRepository.delete(acesso);
    }

    public void delete(Long id) throws ItemNotFoundExcepion {
        acessoRepository.delete(this.get(id));
    }

}
