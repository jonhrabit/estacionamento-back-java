package br.com.estacionamento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.auth.exceptions.ItemNotFoundExcepion;
import br.com.estacionamento.models.Veiculo;
import br.com.estacionamento.repositories.VeiculoRepository;

@Service
public class VeiculoService {
    @Autowired
    VeiculoRepository veiculoRepository;

    public Veiculo get(Long id) throws ItemNotFoundExcepion {
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);
        return veiculo.orElseThrow(() -> new ItemNotFoundExcepion(id, Veiculo.class.getSimpleName()));
    }

    public List<Veiculo> all() {
        return veiculoRepository.findAll();
    }

    public Veiculo update(Veiculo veiculo, Long id) throws ItemNotFoundExcepion {
        Veiculo veiculoOld = this.get(id);
        veiculoOld = veiculo;
        return veiculoRepository.save(veiculoOld);
    }

    public Veiculo save(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public void delete(Veiculo veiculo) {
        veiculoRepository.delete(veiculo);
    }

    public void delete(Long id) throws ItemNotFoundExcepion {
        veiculoRepository.delete(this.get(id));
    }

    public List<Veiculo> findByPlacaContains(String placa) {
        return veiculoRepository.findByPlacaContains(placa);
    }

}
