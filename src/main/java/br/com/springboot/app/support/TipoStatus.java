package br.com.springboot.app.support;

public enum TipoStatus {
	
	CRIADO("C", "CRIADO"), 
	ENVIADO("E", "ENVIADO"), 
	ERRO("E", "ERRO");

	private String codigo;
	private String descricao;

	private TipoStatus(String codigo, String descricao) {
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
