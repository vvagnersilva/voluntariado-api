package com.voluntariado.api.controllers;

import java.util.List;
import java.util.Optional;

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

import com.voluntariado.entidades.Equipe;
import com.voluntariado.service.EquipeService;

@RestController
@RequestMapping("/api/v1/equipes")
public class EquipesController {
	@Autowired
	private EquipeService service;

	@GetMapping()
	public ResponseEntity get() {
		List<Equipe> equipe = service.getEquipes();
		return ResponseEntity.ok(equipe);
	}

	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		Optional<Equipe> equipes = service.getEquipeById(id);

		return ResponseEntity.ok(equipes);
	}

	@PostMapping
	public ResponseEntity post(@RequestBody Equipe equipe) {

		Equipe e = service.insert(equipe);

		return ResponseEntity.ok().build();
	}

	@PutMapping
	public ResponseEntity put(@RequestBody Equipe equipe) {

		Equipe e = service.update(equipe);

		return e != null ? ResponseEntity.ok(e) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		service.delete(id);

		return ResponseEntity.ok().build();
	}
}
