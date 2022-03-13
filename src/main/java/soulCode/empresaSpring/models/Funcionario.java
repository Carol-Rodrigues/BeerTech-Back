package soulCode.empresaSpring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Funcionario {
	
	// Para indicar a chave primária, usamos o @Id e para auto-incrementação o @GeneratedValue
	// Dizemos a estratégia da incrementação
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_funcionario;
	
	// Para indicar as colunas da tabela, usamos o @Column
	@Column(nullable = false, length = 60)
	private String func_nome;
	
	@Column(nullable = false, length = 100)
	private String func_cidade;

	@Column(nullable = false, length = 11)
	private String func_cpf;
	
	@Column(nullable = true)
	private String func_foto;
	
	/* ANTES DA ALTERAÇÃO -- agora, iremos vincular o cargo à tabela específica */
//	@Column(nullable = false, length = 50)
//	private String func_cargo;
	
	/* Nova alteração após segunda tabela */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_cargo")
	private Cargo cargo;
	
	// Gerar os SETTERS e GETTERS
	public Integer getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(Integer id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public String getFunc_nome() {
		return func_nome;
	}

	public void setFunc_nome(String func_nome) {
		this.func_nome = func_nome;
	}

	public String getFunc_cidade() {
		return func_cidade;
	}

	public void setFunc_cidade(String func_cidade) {
		this.func_cidade = func_cidade;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getFunc_cpf() {
		return func_cpf;
	}

	public void setFunc_cpf(String func_cpf) {
		this.func_cpf = func_cpf;
	}

	public String getFunc_foto() {
		return func_foto;
	}

	public void setFunc_foto(String func_foto) {
		this.func_foto = func_foto;
	}	
	
	

//	public String getFunc_cargo() {
//		return func_cargo;
//	}
//
//	public void setFunc_cargo(String func_cargo) {
//		this.func_cargo = func_cargo;
//	}
	
	
}
