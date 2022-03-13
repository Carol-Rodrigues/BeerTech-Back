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
import soulCode.empresaSpring.services.MentorService;


@CrossOrigin //colocamos no controoler, pq é onde chamamos o endpoint
@RestController
@RequestMapping("empresa")
public class MentorController {

	@Autowired
	private MentorService mentorService;
	
	//
	@GetMapping("/mentor")
	public List<Mentor> mostrarTodosMentores() {
		List<Mentor> mentor = mentorService.mostrarTodosMentores();
		return mentor;
	}
	
	//
	@GetMapping("/mentor/{id_mentor}")
	public ResponseEntity mostrarUmMentor(@PathVariable Integer id_mentor) {
		Mentor mentor = mentorService.mostrarUmMentor(id_mentor);
		return ResponseEntity.ok().body(mentor);
	}	

	//
	@PostMapping("/mentor")
	public ResponseEntity<Mentor> cadastrarMentor(@RequestParam(value = "cargo", required = false) Integer id_cargo, @RequestBody Mentor mentor) {
		mentor = mentorService.cadastrarMentor(id_cargo, mentor);
		// o path indica que a criação dessa URI é através do id que será passado pelo path
		// criamos a URI e passamos o path do ID pq estamos criando o ID pra ele
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mentor.getId_mentor()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	// MODULO ESTÁ DELETANDO O CARGO QD EDITA O MENTOR
//	@PutMapping("mentor/{id_mentor}")
//	public ResponseEntity<Void> editarUmMentor(@PathVariable Integer id_mentor, @RequestBody Mentor mentor){
//		mentor.setId_mentor(id_mentor);
//		mentor = mentorService.atualizarMentor(mentor);
//		return ResponseEntity.noContent().build();
//	}
	
	@PutMapping("mentor/{id_mentor}")
	public ResponseEntity<Void> mentorService(@RequestParam(value = "cargo")Cargo cargo, @PathVariable Integer id_mentor, @RequestBody Mentor mentor){
		mentor.setId_mentor(id_mentor);
		mentor.setCargo(cargo);
		mentor = mentorService.atualizarMentor(mentor);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/mentorSemCargo/{id_mentor}")
	public ResponseEntity<Void> atualizarMentor(@PathVariable Integer id_mentor, @RequestBody Mentor mentor) {
		mentor.setId_mentor(id_mentor);
		mentor = mentorService.atualizarMentor(mentor);
		return ResponseEntity.noContent().build();
	}	
	
	//
	@GetMapping("/mentor-cargo/{id_cargo}")
	public ResponseEntity<Mentor> buscarMentorDoCargo(@PathVariable Integer id_cargo){
		Mentor mentor = mentorService.buscarMentorDoCargo(id_cargo);
		return ResponseEntity.ok().body(mentor);
	}

	//
	@GetMapping("/mentorSemCargo")
	public List<Mentor> mentorSemCargo() {
		List<Mentor> mentor = mentorService.mentorSemCargo();
		return mentor;
	}

	//
	@GetMapping("/mentor/mentor-cargo")
	public List<List> funcsComCargo(){
		List<List> mentorCargo = mentorService.mentorComCargo();
		return mentorCargo;
	}
	
	
	@GetMapping("/mentor-cpf/{mentor_cpf}")
	public ResponseEntity<Mentor> buscarMentorPeloCpf(@PathVariable String mentor_cpf){
		Mentor mentor = mentorService.buscarMentorPeloCpf(mentor_cpf);
		return ResponseEntity.ok().body(mentor);
	}
	
	// 5. Mapear URL para DELETE por ID xx
	@DeleteMapping("/mentor/{id_mentor}")
	// MÉTODO DELETE by ID
	public ResponseEntity<Void> deletarMentor(@PathVariable Integer id_mentor) {
		mentorService.deletarMentor(id_mentor);
		return ResponseEntity.noContent().build(); // PQ TEM RETORNO SE É VOID??
	}
}
