package soulCode.empresaSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulCode.empresaSpring.models.Bonificacao;
import soulCode.empresaSpring.models.Mentor;
import soulCode.empresaSpring.models.StatusBonificacao;
import soulCode.empresaSpring.repositories.BonificacaoRepository;
import soulCode.empresaSpring.repositories.MentorRepository;

@Service
public class BonificacaoService {

	@Autowired
	private BonificacaoRepository bonificacaoRepository;
	
	@Autowired
	private MentorRepository mentorRepository;
	
	@Autowired
	private MentorService mentorService;
	
	public List<Bonificacao> buscarTodasBonificacao() {
		return bonificacaoRepository.findAll();
	}
	
	public Bonificacao buscarUmaBonificacao(Integer codigo) {
		Optional<Bonificacao> bonificacao = bonificacaoRepository.findById(codigo);
		return bonificacao.orElseThrow();
	}
	
	public List<Bonificacao> buscarBonificacaoMentor(Integer id_mentor) {
		List<Bonificacao> bonificacao = bonificacaoRepository.buscarBonificacaoMentor(id_mentor);
		return bonificacao;
	}
	
	public Bonificacao criarBonificacao(Bonificacao bonificacao, Integer id_mentor) {
		bonificacao.setCodigo(null);
		Mentor mentor = mentorService.mostrarUmMentor(id_mentor);
		bonificacao.setMentor(mentor);
		return bonificacaoRepository.save(bonificacao);
	}
	
	public Bonificacao pagarBonificacao(Integer codigo) {
		Bonificacao bonificacao = buscarUmaBonificacao(codigo);
		StatusBonificacao status = StatusBonificacao.PAGO;
		bonificacao.setBo_status(status);
		return bonificacaoRepository.save(bonificacao);
	}

	public Bonificacao cancelarBonificacao(Integer codigo) {
		Bonificacao bonificacao = buscarUmaBonificacao(codigo);
		StatusBonificacao status = StatusBonificacao.CANCELADO;
		bonificacao.setBo_status(status);
		return bonificacaoRepository.save(bonificacao);
	}
	
	public void deletarBonificacao(Integer codigo, Integer id_mentor) {
		bonificacaoRepository.deleteById(codigo);
	}
		
	public Bonificacao editarBonificacao(Bonificacao bonificacao, Integer codigo, Integer id_mentor) {
		buscarUmaBonificacao(bonificacao.getCodigo());
		Mentor mentor = mentorService.mostrarUmMentor(id_mentor);
		bonificacao.setMentor(mentor);
		return bonificacaoRepository.save(bonificacao);
	}
}
