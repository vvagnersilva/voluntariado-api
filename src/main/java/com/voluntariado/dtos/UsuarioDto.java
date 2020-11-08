package com.voluntariado.dtos;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.voluntariado.entidades.Equipe;

@SuppressWarnings("deprecation")
public class UsuarioDto {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private String telefone;
	private String adm;
	private String urlImg;
	private String ativo;

	private List<Equipe> equipes;

	public UsuarioDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "Nome não pode ser vazio.")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotEmpty(message = "E-mail não pode ser vazio.")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty(message = "Senha não pode ser vazia.")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@NotEmpty(message = "Telefone não pode ser vazio.")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@NotEmpty(message = "Status administrador não pode ser vazio.")
	public String getAdm() {
		return adm;
	}

	public void setAdm(String adm) {
		this.adm = adm;
	}

	@NotEmpty(message = "Url não pode ser vazio.")
	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	@NotEmpty(message = "Status ativo não pode ser vazio.")
	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	@Override
	public String toString() {
		return "UsuarioDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", telefone="
				+ telefone + ", adm=" + adm + ", urlImg=" + urlImg + ", ativo=" + ativo + "]";
	}

}