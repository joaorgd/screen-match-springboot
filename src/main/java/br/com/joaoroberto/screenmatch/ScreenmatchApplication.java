package br.com.joaoroberto.screenmatch;

import br.com.joaoroberto.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//  A classe principal da aplicação Spring Boot.
//  A responsabilidade dela agora é apenas iniciar o Spring e delegar a execução
//  para a classe Principal, que contém a lógica de negócio.
//  Isso torna a classe de inicialização muito mais limpa e focada.

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