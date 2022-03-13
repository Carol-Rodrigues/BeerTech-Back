package soulCode.empresaSpring.models;

public enum StatusBonificacao {
	
	PENDENTE("Pendente"),
	PAGO("Pago"),
	CANCELADO("Cancelado");
	
	private String descricao;
	
	StatusBonificacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
	

}
