package com.voluntariado.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.voluntariado.entidades.Equipe;
import com.voluntariado.repository.EquipeRepository;

@Service
public class EquipeService {

	@Autowired
	private EquipeRepository rep;

	public List<Equipe> getEquipes() {
		List<Equipe> list = rep.findAll().stream().collect(Collectors.toList());

		return list;
	}

	public Optional<Equipe> getEquipeById(Long id) {
		Optional<Equipe> equipe = rep.findById(id);
		return equipe;
	}

	public Equipe insert(Equipe Equipe) {
		return rep.save(Equipe);
	}

	public Equipe update(Equipe equipe) {
		Assert.notNull(equipe.getId(), "Não foi possível atualizar o registro");

		// Busca o Equipe no banco de dados
		Optional<Equipe> optional = rep.findById(equipe.getId());

		if (optional.isPresent()) {
			Equipe db = optional.get();

			// Copiar as propriedades
			db.setId(equipe.getId());
			db.setAtivo(equipe.getAtivo());
			db.setNome(equipe.getNome());

			// Atualiza o Equipe
			rep.save(db);

			return db;
		} else {
			// return null;
			throw new RuntimeException("Não foi possível atualizar o registro");
		}
	}

	public void delete(Long id) {

		Optional<Equipe> optional = rep.findById(id);

		if (optional.isPresent()) {
			rep.deleteById(id);
		}
	}
}
