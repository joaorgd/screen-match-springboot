package br.com.joaoroberto.screenmatch;

import br.com.joaoroberto.model.DadosSerie;
import br.com.joaoroberto.service.ConsumoApi;
import br.com.joaoroberto.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * A interface CommandLineRunner é um recurso poderoso dentro do universo do Spring Framework,
 * amplamente utilizado no desenvolvimento de aplicações Java. Ela permite que executemos
 * alguma ação logo após a inicialização de nossa aplicação. Pode ser muito útil, por exemplo,
 * se quisermos carregar alguns dados em nosso banco de dados logo na inicialização de
 * nossa aplicação.
 */
@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		var ConsumoApi = new ConsumoApi();
		var json = ConsumoApi.obterDados("https://www.omdbapi.com/?t=game+of+thrones&apikey=2d9a4818");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
	}
}