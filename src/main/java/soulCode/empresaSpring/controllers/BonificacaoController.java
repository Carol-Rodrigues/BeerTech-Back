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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import soulCode.empresaSpring.models.Bonificacao;
import soulCode.empresaSpring.services.BonificacaoService;

@RestController
@CrossOrigin
@RequestMapping("empresa")
public class BonificacaoController {
	
	@Autowired
	private BonificacaoService bonificacaoService;
	
	@GetMapping("/mentor/bonificacao")
	public List<Bonificacao> mostrarTodosBonificacao() {
		List<Bonificacao> bonificacao = bonificacaoService.buscarTodasBonificacao();
		return bonificacao;
	}
	
	//
	@GetMapping("/mentor/bonificacao/{codigo}")
	public ResponseEntity<Bonificacao> buscarUmBoleto(@PathVariable Integer codigo) {
		Bonificacao bonificacao = bonificacaoService.buscarUmaBonificacao(codigo);
		return ResponseEntity.ok().body(bonificacao);
	}
	
	//
	@GetMapping("/mentor/bonificacao-mentor/{id_mentor}")
	public List<Bonificacao> buscarBoletosAluno(@PathVariable Integer id_mentor) {
		List<Bonificacao> bonificacao = bonificacaoService.buscarBonificacaoMentor(id_mentor);
		return bonificacao;
	}
	
	//
	@PostMapping("mentor/bonificacao/{id_mentor}")
	public ResponseEntity<Bonificacao> criarBoleto(@RequestBody Bonificacao bonificacao, @PathVariable Integer id_mentor){
		bonificacao = bonificacaoService.criarBonificacao(bonificacao, id_mentor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(bonificacao.getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//
	@DeleteMapping("/mentor/bonificacao-excluir/{codigo}/{id_mentor}")
	public ResponseEntity<Void> deletarBoleto(@PathVariable Integer codigo, @PathVariable Integer id_mentor){
		bonificacaoService.deletarBonificacao(codigo, id_mentor);
		return ResponseEntity.noContent().build();
	}
	
	//
	@PutMapping("/mentor/bonificacao-editar/{codigo}/{id_mentor}")
	public ResponseEntity<Void> editarBoleto(@RequestBody Bonificacao bonificacao, @PathVariable Integer codigo, @PathVariable Integer id_mentor){
		bonificacao = bonificacaoService.editarBonificacao(bonificacao, codigo, id_mentor);
		return ResponseEntity.noContent().build();
	}
	
	//
	@PutMapping("/mentor/pagarBonificacao/{codigo}")
	public ResponseEntity<Bonificacao> pagarBoleto(@PathVariable Integer codigo) {
		bonificacaoService.pagarBonificacao(codigo);
		return ResponseEntity.noContent().build();
	}
	
	//
	@PutMapping("/mentor/cancelarBonificacao/{codigo}")
	public ResponseEntity<Bonificacao> cancelarBoleto(@PathVariable Integer codigo) {
		bonificacaoService.cancelarBonificacao(codigo);
		return ResponseEntity.noContent().build();
	}	

}
