package com.voluntariado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.voluntariado.dtos.ICertificado;
import com.voluntariado.entidades.Equipe;
import com.voluntariado.entidades.Local;
import com.voluntariado.entidades.Trabalho;

public interface TrabalhoRepository extends JpaRepository<Trabalho, Long> {
	List<Trabalho> findByEquipe(Equipe equipe);

	List<Trabalho> findByLocal(Local local);

	@Query(value = "SELECT t FROM Trabalho t where t.equipe.id = :equipe and t.local.id = :local and t.usuario.id = :usuario")
	List<Trabalho> findByEquipeLocalVoluntario(Long equipe, Long local, Long usuario);

	@Query(value = "SELECT t.usuario.nome as nome_usuario, sum(t.horaFim - t.horaInicio) as total_horas FROM Trabalho t where t.usuario.id = :idUsuario")
	ICertificado findByCertificado(Long idUsuario);

}
