package soulCode.empresaSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import soulCode.empresaSpring.models.Funcionario;
import soulCode.empresaSpring.models.Mentor;
import soulCode.empresaSpring.services.FuncionarioService;
import soulCode.empresaSpring.services.MentorService;
import soulCode.empresaSpring.utils.UploadFileUtil;


@RestController
@RequestMapping("empresa")
@CrossOrigin
public class UploadFileController {
	
	@Autowired
	private MentorService mentorService;

	@Autowired
	private FuncionarioService funcService;
	
	@PostMapping("/envio/{id_mentor}")
	public ResponseEntity<String> enviarDados(@PathVariable Integer id_mentor, MultipartFile foto, @RequestParam(value = "cpf") String cpf) {
		
		String fileName = cpf;
		String uploadDir = "/Users/clair/OneDrive/Área de Trabalho/Estudos/Programação/SoulCode/Aula Java/Spring/empresa-front/src/assets/img";
		
		String nomeMaisCaminho = "assets/img/" + cpf;
	
		Mentor mentor = mentorService.salvarFoto(id_mentor, nomeMaisCaminho);
		
		try {
			UploadFileUtil.salvarArquivo(uploadDir, fileName, foto);
		} catch(Exception e) {
			System.out.println("O arquivo não foi enviado " + e);
		}
		
		System.out.println("Deu certo: " + nomeMaisCaminho);
		return ResponseEntity.ok("Arquivo enviado");
	}
	
	@PostMapping("/enviar/{id_funcionario}")
	public ResponseEntity<String> enviarInfo(@PathVariable Integer id_funcionario, MultipartFile foto, @RequestParam(value = "cpf") String cpf) {
		
		String fileName = cpf;
		String uploadDir = "/Users/clair/OneDrive/Área de Trabalho/Estudos/Programação/SoulCode/Aula Java/Spring/empresa-front/src/assets/img";
		
		String nomeMaisCaminho = "assets/img/" + cpf;
	
		Funcionario func = funcService.salvarFoto(id_funcionario, nomeMaisCaminho);
		
		try {
			UploadFileUtil.salvarArquivo(uploadDir, fileName, foto);
		} catch(Exception e) {
			System.out.println("O arquivo não foi enviado " + e);
		}
		
		System.out.println("Deu certo: " + nomeMaisCaminho);
		return ResponseEntity.ok("Arquivo enviado");
	}
}
