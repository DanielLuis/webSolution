package br.com.springboot.app.support;

import java.io.Serializable;
import java.util.Objects;

public class MensagemDTO implements Serializable{
	private static final long serialVersionUID = 7587947750567867752L;

	private TipoMensagem tipo;

    private int codigo;
	 
    private String mensagem;
 
    public MensagemDTO(TipoMensagem tipo,int codigo, String mensagem) {
    	this.tipo = tipo;
    	this.codigo = codigo;
        this.mensagem = mensagem;
    }

    
    public static MensagemDTO sucesso(int codigo, String mensagem) {
		return new MensagemDTO(TipoMensagem.SUCESSO,codigo,  mensagem);
	}

    public static MensagemDTO erro(int codigo, String mensagem) {
		return new MensagemDTO(TipoMensagem.ERRO,codigo,  mensagem);
	}

    public static MensagemDTO alerta(int codigo, String mensagem) {
		return new MensagemDTO(TipoMensagem.ALERTA,codigo,  mensagem);
	}
    

	public TipoMensagem getTipo() {
		return tipo;
	}


	public void setTipo(TipoMensagem tipo) {
		this.tipo = tipo;
	}


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
    
    @Override
    public int hashCode() {
        return Objects.hashCode(tipo)+Objects.hashCode(mensagem);
    }

    @Override
    public String toString() {
        return "ErroDTO{" + "Tipo" + tipo.name() + ", mensagem='" + mensagem + "'}'";
    }

}
