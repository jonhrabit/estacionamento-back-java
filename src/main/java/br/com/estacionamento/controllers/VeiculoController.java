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

import br.com.estacionamento.models.Veiculo;
import br.com.estacionamento.services.VeiculoService;

@RestController
@RequestMapping("/api/veiculos/")
public class VeiculoController {
    @Autowired
    VeiculoService veiculoService;

    @GetMapping
    public List<Veiculo> all() {
        return veiculoService.all();
    }

    @PostMapping
    public Veiculo post(@RequestBody Veiculo veiculo) {
        return veiculoService.save(veiculo);
    }

    @GetMapping("/{id}")
    public Veiculo get(@PathVariable Long id) throws Throwable {
        return veiculoService.get(id);
    }

    @PutMapping("/{id}")
    Veiculo put(@RequestBody Veiculo novo, @PathVariable Long id) {
        return veiculoService.update(novo, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        veiculoService.delete(id);
        return true;
    }

    @GetMapping("/search/placa/{placa}")
    public List<Veiculo> findByPlacaContains(@PathVariable String placa) {
        return veiculoService.findByPlacaContains(placa);
    }
}
