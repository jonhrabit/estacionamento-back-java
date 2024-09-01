package br.com.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.auth.DTO.LoginDTO;
import br.com.auth.repositories.UsuarioRepository;
import br.com.auth.services.AuthenticationService;

@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("login")
    public ResponseEntity<Object> Login(@RequestBody LoginDTO login) {

        var user = usuarioRepository.findByUsername(login.username());
        if (user.isEmpty()) {
            return new ResponseEntity<Object>("Usuário não localizado.", HttpStatus.UNAUTHORIZED);
        }
        if (!user.get().matches(login.password())) {
            return new ResponseEntity<Object>("Senha incorreta.", HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity<Object>(authenticationService.authenticate(user.get()), HttpStatus.OK);
    }
}
