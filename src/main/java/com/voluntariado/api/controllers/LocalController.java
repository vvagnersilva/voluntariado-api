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

import com.voluntariado.entidades.Local;
import com.voluntariado.service.LocalService;

@RestController
@RequestMapping("/api/v1/locais")
public class LocalController {
	@Autowired
	private LocalService service;

	@GetMapping()
	public ResponseEntity get() {
		List<Local> locais = service.getLocais();
		return ResponseEntity.ok(locais);
	}

	@GetMapping("/{cod}")
	public ResponseEntity get(@PathVariable("cod") Long cod) {
		Optional<Local> locals = service.getLocalById(cod);

		return ResponseEntity.ok(locals);
	}

	@PostMapping
	public ResponseEntity post(@RequestBody Local local) {

		Local e = service.insert(local);

		return ResponseEntity.ok().build();
	}

	@PutMapping
	public ResponseEntity put(@RequestBody Local local) {

		Local e = service.update(local);

		return e != null ? ResponseEntity.ok(e) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{cod}")
	public ResponseEntity delete(@PathVariable("cod") Long cod) {
		service.delete(cod);

		return ResponseEntity.ok().build();
	}
}
