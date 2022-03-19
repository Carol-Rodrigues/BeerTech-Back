//package soulCode.empresaSpring.controllers;
//
//import java.util.Random;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@CrossOrigin
//@RestController
//@RequestMapping("empresa")
//public class EmailController {
//	
//	@Autowired
//	private JavaMailSender mailSender;
//	
//	@GetMapping("/email-send/{contato_email}")
//	public String enviarEmail(@PathVariable String contato_email) {
//		
//		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//		Random random = new Random();
//		
//		String senhaGerada = "";
//		
//		int index = 0;
//		
//		// Para sortear 10 letras
//		for(int i = 0; i < 9; i++) {
//			
//			// o nextInt irá sortear um nº de acordo com o tamanho da array de letras
//			
//			index = random.nextInt(letras.length()); //ex.: sorteia nº 10
//			
//			//Returns a string that is a substring of this string. Thesubstring begins at the specified beginIndex andextends to the character at index endIndex - 1.Thus the length of the substring is endIndex-beginIndex.
//			
//			senhaGerada += letras.substring(index, index + 1); //ex.: vai pegar da 10 a 11
//		}
//		
//		SimpleMailMessage mensagem = new SimpleMailMessage();
//		
//		mensagem.setText("Sua nova senha é: " + senhaGerada);
//		mensagem.setTo(contato_email);
//		mensagem.setFrom("projetosoulclass@gmail.com");
//		
//		try {
//			mailSender.send(mensagem);
//			return "E-mail enviado com sucesso.";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "Erro ao enviar e-mail.";
//		}
//	}
//
//}
