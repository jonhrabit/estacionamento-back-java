package br.com.auth.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.auth.DTO.LoginDTO;
import br.com.auth.DTO.LoginResponseDTO;
import br.com.auth.model.Permissao;
import br.com.auth.model.PermissoesTipo;
import br.com.auth.model.Usuario;
import br.com.auth.repositories.PermissaoRepository;
import br.com.auth.repositories.UsuarioRepository;
import br.com.auth.services.AuthenticationService;

@Controller
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UsuarioRepository usuarioRepository;;
    @Autowired
    PermissaoRepository permissaoRepository;

    @PostMapping("/api/login")
    public ResponseEntity<Object> Login(@RequestBody LoginDTO login) {
        Optional<Usuario> user = usuarioRepository.findByUsername(login.username());
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(new LoginResponseDTO(authenticationService.authenticate(this.primeiroAcesso())),
                    HttpStatus.OK);

        }

        if (user.isEmpty()) {
            return new ResponseEntity<>("Usuário não localizado.", HttpStatus.UNAUTHORIZED);
        }
        if (!user.get().matches(login.password())) {
            return new ResponseEntity<>("Senha incorreta.", HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity<>(new LoginResponseDTO(authenticationService.authenticate(user.get())),
                HttpStatus.OK);
    }

    public Usuario primeiroAcesso() {

        String senha = "adm";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(senha);

        Usuario usuario = new Usuario(null, new Date(), "Administrador", "adm", encodedPassword, "", "",
                new ArrayList<>());

        List<Permissao> lista = new ArrayList<>();
        Permissao basic = permissaoRepository.save(new Permissao(null, PermissoesTipo.BASIC));
        Permissao admin = permissaoRepository.save(new Permissao(null, PermissoesTipo.ADMIN));
        lista.add(basic);
        lista.add(admin);
        usuario.setPermissoes(lista);
        usuarioRepository.save(usuario);
        Logger.getLogger(AuthenticationController.class.getName())
                .info("Primeiro usuario criado com sucesso:");
        Logger.getLogger(AuthenticationController.class.getName())
                .info("user: adm");
        Logger.getLogger(AuthenticationController.class.getName())
                .info("password: adm");
        return usuario;
    }

    @GetMapping("/api/olamundo")
    public String home() {
        return "olamundo.html";
    }

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping(value = { "/{path:[^\\.]*}" })
    public String redirect() {
        return "forward:/index.html";
    }
}
