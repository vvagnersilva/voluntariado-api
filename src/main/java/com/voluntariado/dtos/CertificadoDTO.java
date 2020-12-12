package com.voluntariado.dtos;

public class CertificadoDTO {
	private String nomeUsuario;
	private Long totalHoras;
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public Long getTotalHoras() {
		return totalHoras;
	}
	
	public void setTotalHoras(Long totalHoras) {
		this.totalHoras = totalHoras;
	}
	
}