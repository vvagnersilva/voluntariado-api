package com.voluntariado.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.voluntariado.entidades.Local;
import com.voluntariado.repository.LocalRepository;

@Service
public class LocalService {

	@Autowired
	private LocalRepository rep;

	public List<Local> getLocais() {
		List<Local> list = rep.findAll().stream().collect(Collectors.toList());

		return list;
	}

	public Optional<Local> getLocalById(Long id) {
		Optional<Local> local = rep.findById(id);
		return local;
	}

	public Local insert(Local Local) {
		return rep.save(Local);
	}

	public Local update(Local local) {
		Assert.notNull(local.getId(), "Não foi possível atualizar o registro");

		// Busca o Local no banco de dados
		Optional<Local> optional = rep.findById(local.getId());

		if (optional.isPresent()) {
			Local db = optional.get();

			// Copiar as propriedades
			db.setNome(local.getNome());
			db.setEndereco(local.getEndereco());
			db.setAtivo(local.getAtivo());

			// Atualiza o Local
			rep.save(db);

			return db;
		} else {
			// return null;
			throw new RuntimeException("Não foi possível atualizar o registro");
		}
	}

	public void delete(Long cod) {

		Optional<Local> optional = rep.findById(cod);

		if (optional.isPresent()) {
			rep.deleteById(cod);
		}
	}
}
