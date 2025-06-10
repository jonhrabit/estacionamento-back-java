package br.com.estacionamento.controllers;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estacionamento.DTO.CadastroDTO;
import br.com.estacionamento.DTO.ObsDTO;
import br.com.estacionamento.models.Acesso;
import br.com.estacionamento.models.Pessoa;
import br.com.estacionamento.models.Veiculo;
import br.com.estacionamento.services.AcessoService;
import br.com.estacionamento.services.PessoaService;
import br.com.estacionamento.services.VeiculoService;

@RestController
@RequestMapping("/acessos/")
public class AcessoController {
    @Autowired
    AcessoService acessoService;
    @Autowired
    VeiculoService veiculoService;
    @Autowired
    PessoaService pessoaService;

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
    
    @GetMapping("/saida")
    public List<Acesso> getSaidaIsNull() throws Throwable {
        return acessoService.saidaIsNull();
    }

    @PutMapping("/{id}")
    public Acesso put(@RequestBody Acesso novo, @PathVariable Long id) {
        return acessoService.update(novo, id);
    }

    @PostMapping("/entrada/{placa}")
    public Acesso entrada(@RequestBody ObsDTO obsDTO, @PathVariable String placa) throws Exception {
        Veiculo veiculo = veiculoService.getByPlaca(placa);
        return acessoService.registrarEntrada(veiculo, obsDTO.getObs());
    }

    @PostMapping("/entradaporid/{id}")
    public Acesso entradaporid(@RequestBody ObsDTO obsDTO, @PathVariable Long id) throws Exception {
        Veiculo veiculo = veiculoService.get(id);
        return acessoService.registrarEntrada(veiculo, obsDTO.getObs());
    }

    @PostMapping("/cadastro")
    public Acesso cadastro(@RequestBody CadastroDTO cadastroDTO) {

        Pessoa pessoa = new Pessoa(cadastroDTO);
        pessoa = pessoaService.save(pessoa);
        Veiculo veiculo = new Veiculo(cadastroDTO);
        veiculo.setPessoa(pessoa);
        veiculo = veiculoService.save(veiculo);
        Acesso acesso = new Acesso();
        acesso.setVeiculo(veiculo);
        acesso.setEntrada(new Date());
        acesso.setObservacao("Entrada registrada automaticamente no momento do cadastro.");
        return acessoService.save(acesso);
    }

    @PutMapping("/saida/{id}")
    public Acesso saidaById(@PathVariable Long id) throws Exception {
        return acessoService.registrarSaida(id);
    }


    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        acessoService.delete(id);
        return true;
    }
    
    @GetMapping("/exportar")
    public List<Acesso> exportarAcessos() {
        return acessoService.all();
    }

    @GetMapping("/exportar/csv")
    public ResponseEntity<byte[]> exportarAcessosCsv() {
        List<Acesso> acessos = acessoService.all();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {
            writer.write("id;placa;modelo;cor;entrada;saida;observacao\n");
            for (Acesso acesso : acessos) {
                String placa = acesso.getVeiculo() != null ? acesso.getVeiculo().getPlaca() : "";
                String modelo = acesso.getVeiculo() != null ? acesso.getVeiculo().getModelo() : "";
                String cor = acesso.getVeiculo() != null ? acesso.getVeiculo().getCor() : "";
                String entrada = acesso.getEntrada() != null ? acesso.getEntrada().toString() : "";
                String saida = acesso.getSaida() != null ? acesso.getSaida().toString() : "";
                String observacao = acesso.getObservacao() != null ? acesso.getObservacao().replace(";", ",") : "";
                writer.write(String.format("%d;%s;%s;%s;%s;%s;%s\n",
                    acesso.getId(), placa, modelo, cor, entrada, saida, observacao));
            }
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao exportar CSV", e);
        }
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=acessos.csv")
            .contentType(MediaType.parseMediaType("text/csv"))
            .body(out.toByteArray());
    }
}