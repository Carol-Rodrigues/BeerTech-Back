package soulCode.empresaSpring.controllers.exceptions;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import soulCode.empresaSpring.services.exceptions.DataIntegrityViolationException;
import soulCode.empresaSpring.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	// o tratamento de erro deve sempre ser realizado tanto na camada do service qnto na camada do controller
	
	// Método que possui retorno do tipo REntity do tipo StandardError (classe que criamos). O nome do método é obj... Os parametros são o "e", da classe Object... e o servlet que fará uma requisição ao servidor, para nos trazer a causa do erro.
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundExcpection(ObjectNotFoundException e, ServletRequest request) {
		
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		String dataFormatada = formatador.format(data);
		
		// Instanciando um obj da classe StandardError, chamado error. Os tres parametros estão relacionados à classe criada.
		StandardError error = new StandardError(dataFormatada,HttpStatus.NOT_FOUND.value(),e.getMessage());
		
		// Queremos apenas o body da entidade, no caso, o tempo, status e msg de erro.
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	

	@org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e, ServletRequest request) {
		
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/mm/yyyy - hh:mm:ss");
		String dataFormatada = formatador.format(data);
		
		// Instanciando um obj da classe StandardError, chamado error. Os tres parametros estão relacionados à classe criada.
		StandardError error = new StandardError(dataFormatada,HttpStatus.BAD_REQUEST.value(),e.getMessage());
		
		// Queremos apenas o body da entidade, no caso, o tempo, status e msg de erro.
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
