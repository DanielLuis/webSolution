package br.com.springboot.app.support;

public class ResponseDTO {

	private int codigo;
	private String mensagem;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	public boolean isSucesso() {
		if (this.codigo==201){
			return true;
		}
		return false;
		
	}
	
	
	
}
