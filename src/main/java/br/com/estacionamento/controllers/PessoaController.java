package br.com.estacionamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estacionamento.models.Pessoa;
import br.com.estacionamento.services.PessoaService;

@RestController
@RequestMapping("/pessoas/")
public class PessoaController {
    @Autowired
    PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> all() {
        return pessoaService.all();
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody List<Pessoa> pessoas) {
        pessoas.forEach(pessoa -> {
            pessoaService.save(pessoa);
        });
        return ResponseEntity.ok("Lista importada com sucesso.");
    }

    @GetMapping("/{id}")
    public Pessoa get(@PathVariable Long id) throws Throwable {
        return pessoaService.get(id);
    }

    @PutMapping("/{id}")
    Pessoa put(@RequestBody Pessoa novo, @PathVariable Long id) {
        return pessoaService.update(novo, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        pessoaService.delete(id);
        return true;
    }

    @GetMapping("/search/cargo/{cargo}")
    List<Pessoa> listByCargo(@PathVariable String cargo) {
        return pessoaService.findByCargo(cargo);
    }

    @GetMapping("/search/nome/{nome}")
    List<Pessoa> listByNomeContains(@PathVariable String nome) {
        return pessoaService.findByNomeContains(nome);
    }

    @GetMapping("/search/lotacao/{lotacao}")
    List<Pessoa> listByLotacao(@PathVariable String lotacao) {
        return pessoaService.findByLotacao(lotacao);
    }

    @GetMapping("/search/tipo/{tipoVinculo}")
    List<Pessoa> listByTipoVinculo(@PathVariable String tipoVinculo) {
        return pessoaService.findByTipoVinculo(tipoVinculo);
    }

    @GetMapping("/list/cargos")
    List<Object> cargos() {
        return pessoaService.getCargos();
    }

}
