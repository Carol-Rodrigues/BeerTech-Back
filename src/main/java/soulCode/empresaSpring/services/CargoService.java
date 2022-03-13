package soulCode.empresaSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import soulCode.empresaSpring.models.Cargo;
import soulCode.empresaSpring.models.Mentor;
import soulCode.empresaSpring.repositories.CargoRepository;

@Service
public class CargoService {
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@Lazy	
	@Autowired
	private MentorService mentorService;
	
	/* CREATE */
	public Cargo inserirCargo(Integer id_mentor, Cargo cargo) {
		cargo.setId_cargo(null);
	
		if (id_mentor != null) {
			Mentor mentor = mentorService.mostrarUmMentor(id_mentor);
			cargo.setMentor(mentor);			
		}
		
		return cargoRepository.save(cargo);
	}
	
	/* READ ALL */
	public List<Cargo> printarCargos() {
		return cargoRepository.findAll();
	}
	
	/* READ ID */
	public Cargo filtrarCargo(Integer id_cargo) {
		Optional<Cargo> cargo = cargoRepository.findById(id_cargo);
		return cargo.orElseThrow( () -> new soulCode.empresaSpring.services.exceptions.ObjectNotFoundException("Objeto não cadastrado! O ID procurado foi: " + id_cargo));
	}

	/* READ CARGO */
	public List<Cargo> cargoSemMentor(){
		return cargoRepository.cargoSemMentor();
	}
	
	/* READ CARGO ID MENTOR */
	public Cargo cargoDoMentor(Integer id_mentor) {
		Cargo turma = cargoRepository.cargoDoMentor(id_mentor);
		return turma;
	}	

	/* READ CARGO + MENTOR */
	public List<List> cargoComSeuMentor(){
		return cargoRepository.cargoComSeuMentor();
	}
	
	/* UPDATE ID */
	public Cargo atualizarCargo(Cargo cargo) {
		filtrarCargo(cargo.getId_cargo());
		return cargoRepository.save(cargo);
	}
	
	/* DELETE ID */
	public void deletarCargo(Integer id_cargo) {		
		filtrarCargo(id_cargo);
		try {
			cargoRepository.deleteById(id_cargo);
		} catch (org.springframework.dao.DataIntegrityViolationException e) { //chamando exception do tipo Data do Spring.. com nome e
			throw new soulCode.empresaSpring.services.exceptions.DataIntegrityViolationException("Cargo não pode ser deletado, porque possui funcionários relacionados"); //dando erro, lançamos a exceção do tipo que criamos
		}
	}

	/* PUT MENTOR AO CARGO */
	public Cargo atribuirMentor(Integer id_cargo, Integer id_mentor) {
		Cargo cargo = filtrarCargo(id_cargo);
		Mentor mentorAnterior = mentorService.buscarMentorDoCargo(id_cargo);
		Mentor mentor = mentorService.mostrarUmMentor(id_mentor);
		
		if(cargo.getMentor()!=null) {
			cargo.setMentor(null);
			mentorAnterior.setCargo(null);
		}
		
		cargo.setMentor(mentor);
		mentor.setCargo(cargo);
		return cargoRepository.save(cargo);
	}
	
	/* PUT PARA TIRAR MENTOR DO CARGO */
	public Cargo deixarCargoSemMentor(Integer id_cargo, Integer id_mentor) {
		
		Cargo cargo = filtrarCargo(id_cargo);
		cargo.setMentor(null);
		Mentor mentor = mentorService.mostrarUmMentor(id_mentor);
		mentor.setCargo(null);
		return cargoRepository.save(cargo);
	}

}
