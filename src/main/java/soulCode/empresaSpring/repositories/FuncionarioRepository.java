package soulCode.empresaSpring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.empresaSpring.models.Funcionario;
import soulCode.empresaSpring.models.Mentor;

// Importando o JPA. Indicamos o Modelo e o tipo da chave primária.
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
	
	// Consulta a ser realizada no SQL
	// O segundo parametro informa se a query se refere à uma query nativa do SQL
	@Query(value = "SELECT * FROM funcionario WHERE id_cargo = :id_cargo", nativeQuery = true)
	List<Funcionario> fetchByCargo(Integer id_cargo);
	
	@Query(value = "SELECT id_cargo FROM funcionario WHERE id_funcionario = :id_funcionario", nativeQuery = true)
	String fetchByIdCargo(String id_funcionario);	

	@Query(value = "SELECT id_funcionario, func_nome, func_cidade, func_cpf, func_foto, cargo.id_cargo, car_nome, car_atribuicao FROM cargo right JOIN funcionario ON funcionario.id_cargo = cargo.id_cargo order by func_nome",nativeQuery = true)
	List<List> funcsComCargo();
	
	//buscar o cpf do func para pegar o id_mentor para atribuir a foto para ao mentor certo
	@Query(value = "SELECT * FROM funcionario WHERE func_cpf =:func_cpf", nativeQuery = true)
	Funcionario fetchByCpf(String func_cpf);
}
