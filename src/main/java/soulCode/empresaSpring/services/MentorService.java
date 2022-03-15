package soulCode.empresaSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulCode.empresaSpring.models.Cargo;
import soulCode.empresaSpring.models.Funcionario;
import soulCode.empresaSpring.models.Mentor;
import soulCode.empresaSpring.repositories.CargoRepository;
import soulCode.empresaSpring.repositories.MentorRepository;

@Service
public class MentorService {

	@Autowired
	private MentorRepository mentorRepository;

	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private CargoRepository cargoRepository;
	
	/* READ ALL */
	public List<Mentor> mostrarTodosMentores() {
		return mentorRepository.findAll();
	}

	/* READ ID */
	public Mentor mostrarUmMentor(Integer id_mentor) {
		Optional<Mentor> mentor = mentorRepository.findById(id_mentor);
		return mentor.orElseThrow();
	}

	/* CREATE */
	public Mentor cadastrarMentor(Integer id_cargo, Mentor mentor ) {
		mentor.setId_mentor(null);
		if(id_cargo != null) {
			Cargo cargo = cargoService.filtrarCargo(id_cargo);
			mentor.setCargo(cargo);	
			cargo.setMentor(mentor); //também irá cadastrar o professor dentro da turma
		}
		return mentorRepository.save(mentor);
	}
	
	/* PUT */
//	public Mentor editarMentor(Mentor mentor) {
//		mostrarUmMentor(mentor.getId_mentor());
//		return mentorRepository.save(mentor);
//	}
	
//	public Mentor editarMentor(Integer id_cargo, Mentor mentor) {
//			
//		if (id_cargo != null) { //com cargo
//			Mentor dadosMentorAntesDaMudanca = mostrarUmMentor(mentor.getId_mentor());
//			Cargo cargoAnterior = dadosMentorAntesDaMudanca.getCargo();
//			
//				if(cargoAnterior != null) {
//					cargoAnterior.setMentor(null);
//					cargoRepository.save(cargoAnterior);
//				}
//					
//					
//			Cargo cargo = cargoService.filtrarCargo(id_cargo);
//			mentor.setCargo(cargo);
//			cargo.setMentor(mentor);
//		}
//		
//			return mentorRepository.save(mentor);	
//	}
	
	/* GET MENTOR PELO ID CARGO */
	public Mentor buscarMentorDoCargo(Integer id_cargo){
		Mentor mentor = mentorRepository.fetchByCargo(id_cargo);
		return mentor;
	}

	/* GET CARGO */
	public List<Mentor> mentorSemCargo() {
		return mentorRepository.mentorSemCargo();
	}
	
	/* GET CARGO C/ MENTOR */
	public List<List> mentorComCargo() {
		return mentorRepository.mentorComSeuCargo();
	}

	/* POST */
	/* Método para pegar o caminho da foto e atribuir para o atributo foto do obj professor */
	public Mentor salvarFoto(Integer id_mentor, String caminhoFoto) {
		Mentor mentor = mostrarUmMentor(id_mentor);
		mentor.setMentor_foto(caminhoFoto);		
		return mentorRepository.save(mentor);
	}
	
	/* GET PARA UPLOAD FILE */
	/* Buscar emtnor pelo cpf */
	public Mentor buscarMentorPeloCpf(String mentor_cpf) {
		Mentor mentor = mentorRepository.fetchByCpf(mentor_cpf);
	    return mentor;
	}
	
	/* MÉTODO DELETE ID */
	public void deletarMentor(Integer id_mentor) {
		mentorRepository.deleteById(id_mentor);
	}
	
	/* MÉTODO UPDATE ID */
	public Mentor atualizarMentor(Mentor mentor) { //atualizar func do objeto tipo Mentor
		mostrarUmMentor(mentor.getId_mentor()); //Já utiliza o filtro criado no método READ by ID
		return mentorRepository.save(mentor);
	}
}
