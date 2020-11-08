package com.voluntariado.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voluntariado.api.responses.Response;
import com.voluntariado.dtos.UsuarioDto;
import com.voluntariado.entidades.Usuario;
import com.voluntariado.service.UsuarioService;
import com.voluntariado.utils.SenhaUtils;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuariosController {

	@Autowired
	private UsuarioService service;

	// http://localhost:8080/api/v1/usuarios
	@GetMapping()
	public ResponseEntity<List<Usuario>> get() {
		List<Usuario> listUsuarios = service.getUsuarios();

		return ResponseEntity.ok(listUsuarios);
	}

	// http://localhost:8080/api/v1/usuarios/teste/1111
	@GetMapping("/{email}/{senha}")
	public ResponseEntity<UsuarioDto> get(@PathVariable("email") String email, @PathVariable("senha") String senha) {

		Usuario usuario = service.getUsuarioByEmail(email);

		boolean senhaValida = SenhaUtils.senhaValida(senha, usuario.getSenha());

		if (!senhaValida) {
			usuario = null;
		}

		UsuarioDto usuarioDto = new UsuarioDto();

		usuarioDto.setNome(usuario.getNome());
		usuarioDto.setEmail(usuario.getEmail());
		usuarioDto.setSenha(usuario.getSenha());
		usuarioDto.setTelefone(usuario.getTelefone());
		usuarioDto.setAdm(usuario.getAdm());
		usuarioDto.setUrlImg(usuario.getUrlImg());
		usuarioDto.setAtivo(usuario.getAtivo());
		usuarioDto.setEquipes(usuario.getEquipes());

		return ResponseEntity.ok(usuarioDto);
	}

	@PostMapping
	public ResponseEntity<Response<UsuarioDto>> post(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result) {

		Response<UsuarioDto> response = new Response<UsuarioDto>();

		Usuario usuario = new Usuario();

		usuario.setNome(usuarioDto.getNome());
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setSenha(SenhaUtils.gerarBCrypt(usuarioDto.getSenha()));
		usuario.setTelefone(usuarioDto.getTelefone());
		usuario.setAdm(usuarioDto.getAdm());
		usuario.setUrlImg(usuarioDto.getUrlImg());
		usuario.setAtivo(usuarioDto.getAtivo());
		usuario.setEquipes(usuarioDto.getEquipes());

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		service.insert(usuario);

		response.setData(usuarioDto);

		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<Response<UsuarioDto>> put(@Valid @RequestBody UsuarioDto usuarioDto) {

		Response<UsuarioDto> response = new Response<UsuarioDto>();

		Usuario usuario = new Usuario();

		usuario.setId(usuarioDto.getId());
		usuario.setNome(usuarioDto.getNome());
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setSenha(SenhaUtils.gerarBCrypt(usuarioDto.getSenha()));
		usuario.setTelefone(usuarioDto.getTelefone());
		usuario.setAdm(usuarioDto.getAdm());
		usuario.setUrlImg(usuarioDto.getUrlImg());
		usuario.setAtivo(usuarioDto.getAtivo());
		usuario.setEquipes(usuarioDto.getEquipes());

		service.update(usuario);

		response.setData(usuarioDto);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") Long id) {

		service.delete(id);

		return ResponseEntity.ok().build();
	}
}
