package br.com.auth.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.auth.exceptions.ItemNotFoundExcepion;
import br.com.auth.model.PermissoesTipo;
import br.com.auth.model.Usuario;
import br.com.auth.repositories.PermissaoRepository;
import br.com.auth.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PermissaoRepository permissaoRepository;

    public boolean usuarioExist(Usuario usuario) {
        List<Usuario> lista = this.all();
        if (lista.stream().filter(u -> u.getUsername().equals(usuario.getUsername()) ||
                u.getEmail().equals(usuario.getEmail()) ||
                u.getNome().equals(usuario.getNome())).count() > 0) {
            return true;
        }
        return false;
    }

    public Usuario create(Usuario usuario) throws Exception {
        if (this.usuarioExist(usuario)) {
            throw new Exception("Já Existe um usuário com nome, username ou email cadastrado.");
        }
        usuario.setPassword(Usuario.encode(usuario.getUsername()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> all() {
        return usuarioRepository.findAll().stream().map(usuario -> {
            usuario.setPassword("");
            return usuario;
        }).toList();
    }

    public Usuario get(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundExcepion(id, Usuario.class.getSimpleName()));
        usuario.setPassword("");
        return usuario;
    }

    public Usuario update(Long id, Usuario usuarioDetails) throws ItemNotFoundExcepion {
        Usuario usuario = this.get(id);
        usuario.setNome(usuarioDetails.getNome());
        usuario.setUsername(usuarioDetails.getUsername());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setCpf(usuarioDetails.getCpf());
        usuario.setPermissoes(usuarioDetails.getPermissoes());

        return usuarioRepository.save(usuario);
    }

    public boolean alterarPassword(Long id, String password, String novoPassword) throws RuntimeException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundExcepion(id, Usuario.class.getSimpleName()));

        if (!usuario.matches(password)) {
            throw new RuntimeException("Senha do usuário incorreta.");
        }

        usuario.setPassword(
                new BCryptPasswordEncoder().encode(novoPassword));
        usuarioRepository.save(usuario);

        return true;
    }

    public boolean resetPassword(Long id) throws ItemNotFoundExcepion {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundExcepion(id, Usuario.class.getSimpleName()));
        usuario.setPassword(Usuario.encode(usuario.getUsername()));
        usuarioRepository.save(usuario);
        return true;
    }

    public void deleteById(Long id) throws ItemNotFoundExcepion {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundExcepion(id, Usuario.class.getSimpleName()));
        usuarioRepository.delete(usuario);
    }

    public List<PermissoesTipo> allPermissoesTipos() {
        return PermissoesTipo.all();
    }

}
