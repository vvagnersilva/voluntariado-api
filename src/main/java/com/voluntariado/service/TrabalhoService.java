package com.voluntariado.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.voluntariado.dtos.CertificadoDTO;
import com.voluntariado.dtos.ICertificado;
import com.voluntariado.entidades.Equipe;
import com.voluntariado.entidades.Local;
import com.voluntariado.entidades.Trabalho;
import com.voluntariado.repository.TrabalhoRepository;

@Service
public class TrabalhoService {

	@Autowired
	private TrabalhoRepository rep;

	private Equipe equipe = new Equipe();

	private Local local = new Local();

	public List<Trabalho> getTrabalhos() {
		List<Trabalho> list = rep.findAll().stream().collect(Collectors.toList());

		return list;
	}

	public Trabalho getTrabalhoId(Long cod) {
		Optional<Trabalho> trabalho = rep.findById(cod);

		return trabalho.get();
	}

	public List<Trabalho> getTrabalhosByEquipe(Long id) {
		equipe.setId(id);

		List<Trabalho> list = rep.findByEquipe(equipe);

		return list;
	}

	public List<Trabalho> getTrabalhosByLocal(Long id) {

		local.setId(id);

		return rep.findByLocal(local).stream().collect(Collectors.toList());
	}

	public List<Trabalho> getTrabalhosByEquipeLocalVoluntario(Long idEquipe, Long idVoluntario, Long idLocal) {

		return rep.findByEquipeLocalVoluntario(idEquipe, idVoluntario, idLocal).stream().collect(Collectors.toList());
	}

	public Trabalho insert(Trabalho trabalho) {
		return rep.save(trabalho);
	}

	public Trabalho update(Trabalho trabalho) {
		Assert.notNull(trabalho.getId(), "Não foi possível atualizar o registro");

		// Busca o trabalho no banco de dados
		Optional<Trabalho> optional = rep.findById(trabalho.getId());

		if (optional.isPresent()) {
			Trabalho db = optional.get();

			// Copiar as propriedades db.setData(trabalho.getData());
			db.setEquipe(trabalho.getEquipe());
			db.setUsuario(trabalho.getUsuario());
			db.setLocal(trabalho.getLocal());
			db.setData(trabalho.getData());
			db.setHoraInicio(trabalho.getHoraInicio());
			db.setHoraFim(trabalho.getHoraFim());
			db.setDescricao(trabalho.getDescricao());
			db.setNumAtendimento(trabalho.getNumAtendimento());

			// Atualiza o Equipe
			rep.save(db);

			return db;
		} else {
			throw new RuntimeException("Não foi possível atualizar o registro");
		}
	}

	public void delete(Long cod) {

		Optional<Trabalho> optional = rep.findById(cod);

		if (optional.isPresent()) {
			rep.deleteById(cod);
		}
	}

	public ICertificado getCertificado(Long id) {
		return rep.findByCertificado(id);
	}
}
