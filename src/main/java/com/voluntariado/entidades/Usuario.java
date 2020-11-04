package com.voluntariado.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(nullable = false, length = 1)
	private String adm;

	@Column(nullable = false, length = 1)
	private String ativo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_atualizacao", nullable = false)
	private Date dataAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", nullable = false)
	private Date dataCriacao;

	@Lob
	@Column(nullable = false)
	private String email;

	@Column(nullable = false, length = 45)
	private String nome;

	@Lob
	@Column(nullable = false)
	private String senha;

	@Column(nullable = false, length = 15)
	private String telefone;

	@Lob
	@Column(name = "url_img")
	private String urlImg;

	// bi-directional many-to-many association to Equipe
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_equipe", joinColumns = {
			@JoinColumn(name = "id_usuario", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_equipe", nullable = false) })
	private List<Equipe> equipes;

	public Usuario() {
	}

	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date(id);
	}

	@PrePersist
	public void prePersist() {
		final Date atual = new Date();

		dataCriacao = atual;
		dataAtualizacao = atual;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdm() {
		return this.adm;
	}

	public void setAdm(String adm) {
		this.adm = adm;
	}

	public String getAtivo() {
		return this.ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public Date getDataAtualizacao() {
		return this.dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Date getDataCriacao() {
		return this.dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getUrlImg() {
		return this.urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public List<Equipe> getEquipes() {
		return this.equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", adm=" + adm + ", ativo=" + ativo + ", dataAtualizacao=" + dataAtualizacao
				+ ", dataCriacao=" + dataCriacao + ", email=" + email + ", nome=" + nome + ", senha=" + senha
				+ ", telefone=" + telefone + ", urlImg=" + urlImg + "]";
	}

}