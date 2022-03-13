package soulCode.empresaSpring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.empresaSpring.models.Bonificacao;

public interface BonificacaoRepository extends JpaRepository<Bonificacao, Integer> {

	@Query(value = "SELECT * FROM bonificacao WHERE id_mentor = :id_mentor", nativeQuery = true)
	List<Bonificacao> buscarBonificacaoMentor(Integer id_mentor);	
	
}
