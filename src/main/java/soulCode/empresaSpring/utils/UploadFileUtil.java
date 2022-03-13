package soulCode.empresaSpring.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtil {
	
	//MultipartFile é o tipo de variavel para importação de arquivo
	//Throws para lançar exceção caso o diretorio nao possa ser criado
	public static void salvarArquivo(String uploadDir, String fileName, MultipartFile file) throws IOException {
		
		Path uploadPath = Paths.get(uploadDir);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		//InputScream é responsavel por fazer um GET no arquivo selecionado
		try(InputStream inputStream = file.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream,  filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch(IOException e) {
			throw new IOException("Não foi possível enviar o seu arquivo" + fileName, e);
		}
		
	}

}
