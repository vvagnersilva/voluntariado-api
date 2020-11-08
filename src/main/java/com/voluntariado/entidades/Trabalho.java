package com.voluntariado.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the trabalho database table.
 * 
 */
@Entity
@Table(name = "trabalho")
public class Trabalho implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(nullable = false, length = 10)
	private String data;

	@Column(nullable = false, length = 150)
	private String descricao;

	@Column(name = "hora_fim", nullable = false, length = 5)
	private String horaFim;

	@Column(name = "hora_inicio", nullable = false, length = 5)
	private String horaInicio;

	@Column(name = "num_atendimento", nullable = false)
	private int numAtendimento;

	@Column(name="tp_atendimento", nullable=false, length=1)
	private String tpAtendimento;

	// bi-directional many-to-one association to Equipe
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_equipe", nullable = false)
	private Equipe equipe;

	// bi-directional many-to-one association to Local
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_local", nullable = false)
	private Local local;

	// bi-directional many-to-one association to Usuario
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	public Trabalho() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getHoraFim() {
		return this.horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public String getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public int getNumAtendimento() {
		return this.numAtendimento;
	}

	public void setNumAtendimento(int numAtendimento) {
		this.numAtendimento = numAtendimento;
	}

	public String getTpAtendimento() {
		return this.tpAtendimento;
	}

	public void setTpAtendimento(String tpAtendimento) {
		this.tpAtendimento = tpAtendimento;
	}
	
	public Equipe getEquipe() {
		return this.equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Local getLocal() {
		return this.local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}