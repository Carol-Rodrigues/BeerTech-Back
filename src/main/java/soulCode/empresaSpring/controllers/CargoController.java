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
import soulCode.empresaSpring.models.Mentor;
import soulCode.empresaSpring.services.CargoService;

@CrossOrigin
@RestController
@RequestMapping("empresa")
public class CargoController {
	
	@Autowired
	private CargoService cargoService;
		
	/* CREATE */
	@PostMapping("/cargo")
	public ResponseEntity<Cargo> cadastrarCargo(@RequestParam(value="cargo", required = false)Integer id_mentor,@RequestBody Cargo cargo){
		cargo = cargoService.inserirCargo(id_mentor,cargo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cargo.getId_cargo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}	
	
	/* READ */
	@GetMapping("/cargo")
	public List<Cargo> mostrarCargos() {
		List<Cargo> cargo = cargoService.printarCargos();
		return cargo;
	}
	
	/* READ ID */
	@GetMapping("/cargo/{id_cargo}")
	public ResponseEntity<?> filtrarCargo(@PathVariable Integer id_cargo) {
		Cargo cargo = cargoService.filtrarCargo(id_cargo);
		return ResponseEntity.ok().body(cargo);
	}
	
	/* UPDATE ID */
	@PutMapping("/cargo/{id_cargo}")
	public ResponseEntity<Void> atualizar(@PathVariable Integer id_cargo, @RequestBody Cargo cargo) {
		cargo.setId_cargo(id_cargo);
		cargo = cargoService.atualizarCargo(cargo);
		return ResponseEntity.noContent().build();
	}

	//
	@PutMapping("/cargo/definirMentor/{id_cargo}/{id_mentor}")
	public ResponseEntity<Mentor> atribuirMentor(@PathVariable Integer id_cargo, @PathVariable Integer id_mentor) {
		cargoService.atribuirMentor(id_cargo, id_mentor);
		return ResponseEntity.noContent().build();
	}
	
	//
	@PutMapping("/cargo/tirarMentor/{id_cargo}/{id_mentor}")
	public ResponseEntity<Mentor> deixarCargoSemMentor(@PathVariable Integer id_cargo, @PathVariable Integer id_mentor){
		cargoService.deixarCargoSemMentor(id_cargo, id_mentor);
		return ResponseEntity.noContent().build();
	}	
	
	//
	@GetMapping("/cargoSemMentor")
	public List<Cargo> mentorSemCargo(){
		List<Cargo> cargo = cargoService.cargoSemMentor();
		return cargo;
	}
	
	//
	@GetMapping("/cargo/cargo-mentor/{id_mentor}")
	public Cargo cargoDoMentor(@PathVariable Integer id_mentor){		
		return cargoService.cargoDoMentor(id_mentor);
	}

	//
	@GetMapping("/cargo/cargo-mentor")
	public List<List> cargosComMentor(){
		List<List> cargoMentor = cargoService.cargoComSeuMentor();
		return cargoMentor;
	}
	
	/* DELETE ID */
	@DeleteMapping("/cargo/{id_cargo}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id_cargo) {
		cargoService.deletarCargo(id_cargo);
		return ResponseEntity.noContent().build(); //no Content significa que o retorno do corpo do Entity é vazio
	}

}
