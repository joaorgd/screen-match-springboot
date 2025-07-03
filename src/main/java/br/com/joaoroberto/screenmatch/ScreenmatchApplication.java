package br.com.joaoroberto.screenmatch;

import br.com.joaoroberto.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Spring Boot.
 * A responsabilidade dela agora é apenas iniciar o Spring e delegar a execução
 * para a classe Principal, que contém a lógica de negócio.
 * Isso torna a classe de inicialização muito mais limpa e focada,
 * seguindo o princípio de responsabilidade única.
 */
@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    // Método padrão para iniciar uma aplicação Spring.
    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);
    }

    /**
     * Este método é executado automaticamente pelo Spring Boot assim que a aplicação inicia,
     * graças à interface CommandLineRunner.
     * @param args Argumentos de linha de comando (não utilizados neste caso).
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.exibeMenu();
    }
}