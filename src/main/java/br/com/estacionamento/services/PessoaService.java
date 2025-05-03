package br.com.estacionamento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.auth.exceptions.ItemNotFoundExcepion;
import br.com.estacionamento.models.Pessoa;
import br.com.estacionamento.repositories.PessoaRepository;

@Service
public class PessoaService {
    @Autowired
    PessoaRepository pessoaRepository;

    public Pessoa get(Long id) throws ItemNotFoundExcepion {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.orElseThrow(() -> new ItemNotFoundExcepion(id, Pessoa.class.getSimpleName()));
    }

    public List<Pessoa> all() {
        return pessoaRepository.findAll();
    }

    public Pessoa update(Pessoa pessoa, Long id) throws ItemNotFoundExcepion {
        Pessoa pessoaOld = this.get(id);
        pessoaOld = pessoa;
        return pessoaRepository.save(pessoaOld);
    }

    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public void delete(Pessoa pessoa) {
        pessoaRepository.delete(pessoa);
    }

    public void delete(Long id) throws ItemNotFoundExcepion {
        pessoaRepository.delete(this.get(id));
    }

    public List<Pessoa> findByCargo(String cargo) {
        return pessoaRepository.findByCargo(cargo);
    }

    public List<Pessoa> findByNomeContains(String nome) {
        return pessoaRepository.findByNomeContains(nome);
    }

    public List<Pessoa> findByLotacao(String lotacao) {
        return pessoaRepository.findByLotacaoContains(lotacao);
    }

    public List<Pessoa> findByTipoVinculo(String tipoVinculo) {
        return pessoaRepository.findByTipoVinculo(tipoVinculo);
    }

    public List<Object> getCargos() {
        return pessoaRepository.getCargos();
    }
}
