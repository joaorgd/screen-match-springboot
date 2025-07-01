package br.com.joaoroberto.screenmatch;

import br.com.joaoroberto.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// A interface CommandLineRunner faz com que o método "run" seja executado
// automaticamente logo após a inicialização da aplicação Spring Boot.
// É aqui que a lógica principal da nossa aplicação de console reside.

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.exibeMenu();
    }
}