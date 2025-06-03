package br.com.estacionamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estacionamento.models.Acesso;
import br.com.estacionamento.services.AcessoService;

@RestController
@RequestMapping("/acessos/")
public class AcessoController {
    @Autowired
    AcessoService acessoService;

    @GetMapping
    public List<Acesso> all() {
        return acessoService.all();
    }

    @PostMapping
    public Acesso post(@RequestBody Acesso acesso) {
        return acessoService.save(acesso);
    }

    @GetMapping("/{id}")
    public Acesso get(@PathVariable Long id) throws Throwable {
        return acessoService.get(id);
    }
    @GetMapping("/{dia}/{mes}/{ano}")
    public List<Acesso> getByDate(@PathVariable Integer dia,@PathVariable Integer mes,@PathVariable Integer ano) throws Throwable {
        return acessoService.porDataEntrada(dia, mes, ano);
    }
    
    @GetMapping("/e")
    public List<Acesso> getSaidaIsNull() throws Throwable {
        return acessoService.saidaIsNull();
    }

    @PutMapping("/{id}")
    Acesso put(@RequestBody Acesso novo, @PathVariable Long id) {
        return acessoService.update(novo, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        acessoService.delete(id);
        return true;
    }
}