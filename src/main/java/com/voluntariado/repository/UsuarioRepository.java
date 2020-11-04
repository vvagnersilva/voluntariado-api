package com.voluntariado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.voluntariado.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);

	@Query(value = "SELECT u FROM Usuario u where u.email = :email and u.senha = :senha")
	Usuario findByEmailSenha(String email, String senha);

}
