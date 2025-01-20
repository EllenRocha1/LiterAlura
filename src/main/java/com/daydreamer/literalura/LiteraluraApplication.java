package com.daydreamer.literalura;

import com.daydreamer.literalura.principal.Principal;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private Principal principal; // Injeção gerenciada pelo Spring

	public static void main(String[] args) {
		// Inicializa o contexto da aplicação
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Dotenv dotenv = Dotenv.configure()
				.directory("D://literalura//src//.env")
				.load();

		String dbHost = dotenv.get("DB_HOST");
		String dbUser = dotenv.get("DB_USER");
		String dbPassword = dotenv.get("DB_PASSWORD");

		if (dbHost == null || dbUser == null || dbPassword == null) {
			throw new IllegalArgumentException("Variáveis de ambiente não configuradas corretamente no .env");
		}

		System.setProperty("DB_HOST", dbHost);
		System.setProperty("DB_USER", dbUser);
		System.setProperty("DB_PASSWORD", dbPassword);

		// Exibir o menu principal
		principal.exibeMenu();
	}
}