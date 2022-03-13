package soulCode.empresaSpring.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import soulCode.empresaSpring.models.Cargo;
import soulCode.empresaSpring.models.Funcionario;
import soulCode.empresaSpring.models.Mentor;
import soulCode.empresaSpring.repositories.FuncionarioRepository;
import soulCode.empresaSpring.services.FuncionarioService;

@CrossOrigin // para evitar problema de cores
@RestController // anotação de que é um controle
@RequestMapping("empresa") // indicando URL
public class FuncionarioController {

	// 1. Importar repositório
	@Autowired
	private FuncionarioRepository funcRepository;

	// 2. Importar service
	@Autowired
	private FuncionarioService funcService;

	// 3. Mapear URL para o READ xx
	@GetMapping("/funcionario")
	// MÉTODO GET ALL
	public List<Funcionario> printarFuncionarios() {
		List<Funcionario> funcionario = funcService.printarFuncionarios();
		return funcionario;
	}

	// 4. Mapear URL para READ por ID xx
	@GetMapping("/funcionario/{id_funcionario}")
	// MÉTODO GET by ID
	public ResponseEntity<?> filtrarFunc(@PathVariable Integer id_funcionario) {
		Funcionario funcionario = funcService.filtrarFunc(id_funcionario);
		return ResponseEntity.ok().body(funcionario);
	}
	
	// 8. Buscar ID do cargo xx
	@GetMapping("/funcionario/busca-id-cargo/{id_funcionario}")
	public String buscaIdCargo(@PathVariable String id_funcionario) {
		String id_cargo = funcService.buscarIdCargo(id_funcionario);
		return id_cargo;
	}
	
	// 9. Buscar func no cargo xx
	@GetMapping("/funcionario/busca-cargo/{id_cargo}")
	public List<Funcionario> buscarFuncCargo(@PathVariable Integer id_cargo) {
		List<Funcionario> func = funcService.buscarFuncCargo(id_cargo);
		return func;
	}

	// 10. Buscar func + cargo xx
	@GetMapping("/funcionario-cargo")
	public List<List> funcsComCargo() {
		List<List> funcCargo = funcService.funcsComCargo();
		return funcCargo;
	}

	// 5. Mapear URL para DELETE por ID xx
	@DeleteMapping("/funcionario/{id_funcionario}")
	// MÉTODO DELETE by ID
	public ResponseEntity<Void> deletarFunc(@PathVariable Integer id_funcionario) {
		funcService.deletarFunc(id_funcionario);
		return ResponseEntity.noContent().build(); // PQ TEM RETORNO SE É VOID??
	}

	// 6. Mapear URL para UPDATE por ID xx
	@PutMapping("/funcionario/{id_funcionario}")
	
	/* MÉTODO PARA UMA TABELA */
	// MÉTODO UPDATE by ID
//	public ResponseEntity<Void> atualizarFunc(@PathVariable Integer id_funcionario, @RequestBody Funcionario func) {
//		func.setId_funcionario(id_funcionario);
//		func = funcService.atualizarFunc(func);
//		return ResponseEntity.noContent().build();
	
	/* NOVO MÉTODO, CONSIDERANDO VÍNCULO ENTRE AS TABELAS */
	public ResponseEntity<Void> editarUmFunc(@RequestParam(value = "cargo")Cargo cargo, @PathVariable Integer id_funcionario, @RequestBody Funcionario func){
		func.setId_funcionario(id_funcionario);
		func.setCargo(cargo);
		func = funcService.atualizarFunc(func);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/funcionarioSemCargo/{id_funcionario}")
	public ResponseEntity<Void> atualizarFunc(@PathVariable Integer id_funcionario, @RequestBody Funcionario func) {
		func.setId_funcionario(id_funcionario);
		func = funcService.atualizarFunc(func);
		return ResponseEntity.noContent().build();
	}
	
	// 7. Mapear URL para CREATE xx
	@PostMapping("/funcionario")
	// MÉTODO CREATE
	
	/* MÉTODO PARA UMA TABELA */
//	public ResponseEntity<Void> inserirFunc(@RequestBody Funcionario func) {
//		
//		func = funcService.inserirFunc(func);
//		
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(func.getId_funcionario()).toUri();
//		return ResponseEntity.created(uri).build();
//	}
	
	/* NOVO MÉTODO, CONSIDERANDO VÍNCULO ENTRE AS TABELAS */
	// O que indicarmos como Param é o que deverá ser digitado no postman xx
	public ResponseEntity<Funcionario> inserirFunc(@RequestBody Funcionario func) {
		func = funcService.inserirFunc(func);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(func.getId_funcionario()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//
	@PutMapping("/funcionario/inserirCargo/{id_funcionario}")
	public ResponseEntity<Funcionario> inserirFuncNoCargo(@PathVariable Integer id_funcionario, @RequestBody Cargo cargo){
		Funcionario func = funcService.inserirFuncNoCargo(id_funcionario, cargo);
		return ResponseEntity.noContent().build();
	}
	
	//
	@PutMapping("/funcionario/deixarSemCargo/{id_funcionario}")
	public ResponseEntity<Funcionario> deixarFuncSemCargo(@PathVariable Integer id_funcionario){
		Funcionario func = funcService.deixarFuncSemCargo(id_funcionario);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/funcionario-cpf/{func_cpf}")
	public ResponseEntity<Funcionario> buscarFuncPeloCpf(@PathVariable String func_cpf){
		Funcionario func = funcService.buscarFuncPeloCpf(func_cpf);
		return ResponseEntity.ok().body(func);
	}
}
