package br.com.estacionamento.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.auth.exceptions.ItemNotFoundExcepion;
import br.com.estacionamento.models.Acesso;
import br.com.estacionamento.models.Veiculo;
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

    public List<Acesso> porDataEntrada(Integer dia, Integer mes, Integer ano) {
        Calendar c = Calendar.getInstance();
        c.set(mes, ano, dia,00,00);
        Date inicio = c.getTime();
        c.set(ano, mes, dia, 23, 59);
        Date fim = c.getTime();
        return acessoRepository.findByEntradaBetween(inicio, fim);
    }

    public List<Acesso> porDataSaida(Integer dia, Integer mes, Integer ano) {
        Calendar c = Calendar.getInstance();
        c.set(mes, ano, dia,00,00);
        Date inicio = c.getTime();
        c.set(ano, mes, dia, 23, 59);
        Date fim = c.getTime();
        return acessoRepository.findBySaidaBetween(inicio, fim);
    }
    public List<Acesso> saidaIsNull() {
        return acessoRepository.findBySaidaIsNull();
    }

    public Acesso update(Acesso acesso, Long id) throws ItemNotFoundExcepion {
        Acesso acessoOld = this.get(id);
        acessoOld = acesso;
        return acessoRepository.save(acessoOld);
    }
    public Acesso registrarSaida(Long id) throws ItemNotFoundExcepion {
        Acesso acessoOld = this.get(id);
        acessoOld.setSaida(new Date());
        return acessoRepository.save(acessoOld);
    }

    
    public Acesso registrarEntrada(Veiculo veiculo, String observacao) {
        Acesso acesso = new Acesso();
        acesso.setVeiculo(veiculo);
        acesso.setEntrada(new Date());
        acesso.setObservacao(observacao);
        return acessoRepository.save(acesso);
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
