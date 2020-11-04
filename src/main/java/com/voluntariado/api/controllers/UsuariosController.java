package com.voluntariado.api.controllers;

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

import com.voluntariado.entidades.Usuario;
import com.voluntariado.service.UsuarioService;
import com.voluntariado.utils.SenhaUtils;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuariosController {

	@Autowired
	private UsuarioService service;

	@GetMapping()
	public ResponseEntity get() {
		List<Usuario> usuario = service.getUsuarios();
		return ResponseEntity.ok(usuario);
	}

	@GetMapping("/{email}/{senha}")
	public ResponseEntity get(@PathVariable("email") String email, @PathVariable("senha") String senha) {

		Usuario usuario = service.getUsuarioByEmail(email);

		boolean senhaValida = SenhaUtils.senhaValida(senha, usuario.getSenha());

		if (!senhaValida) {
			usuario = null;
		}

		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	public ResponseEntity post(@RequestBody Usuario usuario) {

		String senhaEncoded = SenhaUtils.gerarBCrypt(usuario.getSenha());

		usuario.setSenha(senhaEncoded);

		Usuario e = service.insert(usuario);

		return ResponseEntity.ok().build();
	}

	@PutMapping
	public ResponseEntity put(@RequestBody Usuario usuario) {

		Usuario e = service.update(usuario);

		return e != null ? ResponseEntity.ok(e) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {

		service.delete(id);

		return ResponseEntity.ok().build();
	}
}
