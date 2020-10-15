package br.com.springboot.app.support;

public enum UserStatus {
	
	ACTIVE("A", "ACTIVE"), 
	INACTIVE("I", "INACTIVE"), 
	ERROR("E", "ERROR");

	private String codigo;
	private String descricao;

	private UserStatus(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
