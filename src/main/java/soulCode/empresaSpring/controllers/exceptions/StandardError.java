package soulCode.empresaSpring.controllers.exceptions;

public class StandardError {

	/* ATRIBUTOS */
	
	private String timestamp;
	private Integer status;
	private String msgErro;
	
	public StandardError(String timestamp, Integer status, String msgErro) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.msgErro = msgErro;
	}
	
	/* METODOS ACESSORES E MODIFICADORES */

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsgErro() {
		return msgErro;
	}

	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}
	
}
