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

import com.voluntariado.entidades.Trabalho;
import com.voluntariado.service.TrabalhoService;

@RestController
@RequestMapping("/api/v1/trabalhos")
public class TrabalhoController {
	@Autowired
	private TrabalhoService service;

	@GetMapping()
	public ResponseEntity get() {
		List<Trabalho> equipe = service.getTrabalhos();

		return ResponseEntity.ok(equipe);
	}

	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		Trabalho trabalho = service.getTrabalhoId(id);

		return ResponseEntity.ok(trabalho);
	}

	@GetMapping("/equipe/{idEquipe}")
	public ResponseEntity getTrabalhosByEquipe(@PathVariable("idEquipe") Long idEquipe) {
		List<Trabalho> trabalho = service.getTrabalhosByEquipe(idEquipe);

		return ResponseEntity.ok(trabalho);
	}

	@GetMapping("/local/{idLocal}")
	public ResponseEntity getTrabalhosByLocal(@PathVariable("idLocal") Long idLocal) {
		List<Trabalho> trabalhos = service.getTrabalhosByLocal(idLocal);

		return trabalhos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(trabalhos);
	}

	@GetMapping("equipe_voluntario_local/{idEquipe}/{idVoluntario}/{idLocal}")
	public ResponseEntity getTrabalhosByEquipeVoluntarioLocal(@PathVariable("idEquipe") Long idEquipe,
			@PathVariable("idVoluntario") Long idVoluntario, @PathVariable("idLocal") Long idLocal) {

		List<Trabalho> trabalho = service.getTrabalhosByEquipeLocalVoluntario(idEquipe, idVoluntario, idLocal);

		return ResponseEntity.ok(trabalho);
	}

	@PutMapping
	public ResponseEntity put(@RequestBody Trabalho trabalho) {

		Trabalho t = service.update(trabalho);

		return t != null ? ResponseEntity.ok(t) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity post(@RequestBody Trabalho trabalho) {

		Trabalho t = service.insert(trabalho);

		return t != null ? ResponseEntity.ok(t) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		service.delete(id);

		return ResponseEntity.ok().build();
	}

}
