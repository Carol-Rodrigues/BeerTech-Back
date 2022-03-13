package soulCode.empresaSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulCode.empresaSpring.models.Cargo;
import soulCode.empresaSpring.models.Funcionario;
import soulCode.empresaSpring.models.Mentor;
import soulCode.empresaSpring.repositories.FuncionarioRepository;
import soulCode.empresaSpring.services.exceptions.ObjectNotFoundException;

@Service
public class FuncionarioService {
	
	@Autowired	
	// instanciando o repositório
	private FuncionarioRepository funcRepository;
	
	@Autowired
	private CargoService cargoService;

	/* ALTERADO PARA VINCULAR AS DUAS TABELAS */
	/* MÉTODO CREATE */
	public Funcionario inserirFunc(Funcionario func) {
		func.setId_funcionario(null);
		return funcRepository.save(func);
	}
	
	/* MÉTODO READ ALL */
	public List<Funcionario> printarFuncionarios() {
		return funcRepository.findAll();
	}
	
	/* MÉTODO READ ID */
	public Funcionario filtrarFunc(Integer id_funcionario) {
		Optional<Funcionario> funcionario = funcRepository.findById(id_funcionario);
		return funcionario.orElseThrow(() -> new ObjectNotFoundException("Objeto não cadastrado! O ID procurado foi: " + id_funcionario));
	}

	/* MÉTODO READ (FUNC -> CARGO) */
	public List<Funcionario> buscarFuncCargo(Integer id_cargo){
		List<Funcionario> func = funcRepository.fetchByCargo(id_cargo);
		return func;
	}

	/* MÉTODO READ (FUNC -> ID_CARGO) */
	public String buscarIdCargo(String id_funcionario) {
		String id_cargo = funcRepository.fetchByIdCargo(id_funcionario);
		return id_cargo;
	}
	
	/* MÉTODO UPDATE ID */
	public Funcionario atualizarFunc(Funcionario func) { //atualizar func do objeto tipo Funcionario
		filtrarFunc(func.getId_funcionario()); //Já utiliza o filtro criado no método READ by ID
		return funcRepository.save(func);
	}
	
	/* MÉTODO DELETE ID */
	public void deletarFunc(Integer id_funcionario) {
		funcRepository.deleteById(id_funcionario);
	}
	
	/* MÉTODO READ FUNCS + CARGO */	
	public List<List> funcsComCargo() {
		return funcRepository.funcsComCargo();
	}

	/* PUT ALUNO -> TURMA */
	public Funcionario inserirFuncNoCargo(Integer id_funcionario, Cargo cargo) {
		Funcionario func = filtrarFunc(id_funcionario);
		func.setCargo(cargo);
		return funcRepository.save(func);
	}

	/* PUT TIRAR ALUNO DA TURMA */
	public Funcionario deixarFuncSemCargo(Integer id_funcionario) {
		Funcionario func = filtrarFunc(id_funcionario);
		func.setCargo(null);
		return funcRepository.save(func);
	}
	
	/* POST */
	/* Método para pegar o caminho da foto e atribuir para o atributo foto do obj professor */
	public Funcionario salvarFoto(Integer id_funcionario, String caminhoFoto) {
		Funcionario func = filtrarFunc(id_funcionario);
		func.setFunc_foto(caminhoFoto);		
		return funcRepository.save(func);
	}
	
	/* GET PARA UPLOAD FILE */
	/* Buscar func pelo cpf */
	public Funcionario buscarFuncPeloCpf(String func_cpf) {
		Funcionario func = funcRepository.fetchByCpf(func_cpf);
	    return func;
	}
}
