package com.voluntariado.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.voluntariado.entidades.Usuario;
import com.voluntariado.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	// Alocacao dinamica pelo spring
	private UsuarioRepository rep;

	public List<Usuario> getUsuarios() {
		List<Usuario> list = rep.findAll().stream().collect(Collectors.toList());

		return list;
	}

	public Usuario getUsuarioByEmail(String email) {
		Usuario usuario = rep.findByEmail(email);

		return usuario;
	}

	public Usuario getUsuarioByEmailSenha(String email, String senha) {
		Usuario usuario = rep.findByEmailSenha(email, senha);

		return usuario;
	}

	public Usuario insert(Usuario Usuario) {
		return rep.save(Usuario);
	}

	public Usuario update(Usuario usuario) {
		Assert.notNull(usuario.getEmail(), "Não foi possível atualizar o registro");

		// Busca o Usuario no banco de dados
		Optional<Usuario> optional = rep.findById(usuario.getId());

		if (optional.isPresent()) {
			Usuario db = optional.get();

			// Copiar as propriedades
			db.setAdm(usuario.getAdm());
			db.setAtivo(usuario.getAtivo());
			db.setEmail(usuario.getEmail());
			db.setNome(usuario.getNome());
			db.setSenha(usuario.getSenha());
			db.setTelefone(usuario.getTelefone());
			db.setUrlImg(usuario.getUrlImg());
			db.setEquipes(usuario.getEquipes());

			// Atualiza o Usuario
			rep.save(db);

			return db;
		} else {
			// return null;
			throw new RuntimeException("Não foi possível atualizar o registro");
		}
	}

	public void delete(Long id) {

		Optional<Usuario> optional = rep.findById(id);

		if (optional.isPresent()) {
			rep.deleteById(id);
		}
	}
}
