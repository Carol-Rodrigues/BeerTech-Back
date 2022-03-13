package soulCode.empresaSpring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.empresaSpring.models.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {

	@Query(value="SELECT * FROM cargo WHERE id_mentor is null", nativeQuery = true)
	List<Cargo> cargoSemMentor();
	
	@Query(value="SELECT * FROM cargo where id_mentor =:id_mentor",nativeQuery = true)
	Cargo cargoDoMentor(Integer id_mentor);
	
	@Query(value="SELECT cargo.id_cargo, cargo.car_nome, cargo.car_atribuicao, mentor.id_mentor, mentor.mentor_nome, mentor.mentor_cpf, mentor.mentor_cargo, mentor.mentor_foto FROM cargo left JOIN mentor ON mentor.id_cargo = cargo.id_cargo",nativeQuery = true)
	List<List> cargoComSeuMentor();
}
