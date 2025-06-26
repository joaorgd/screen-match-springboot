package br.com.joaoroberto.screenmatch;

import br.com.joaoroberto.exception.ErrorDeConversaoDeAnoException;
import br.com.joaoroberto.modelos.Titulo;
import br.com.joaoroberto.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		// Toda a lógica da aplicação agora está dentro do método 'run'.
		Scanner leitura = new Scanner(System.in);
		Gson gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
				.setPrettyPrinting()
				.create();

		String busca = "";
		List<Titulo> titulos = new ArrayList<>();

		while (!busca.equalsIgnoreCase("sair")) {
			System.out.print("Digite um filme para busca (ou 'sair' para finalizar): ");
			busca = leitura.nextLine();

			if (busca.equalsIgnoreCase("sair")) {
				break;
			}

			String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=2d9a4818";

			try {
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder()
						.uri(URI.create(endereco))
						.build();
				HttpResponse<String> response = client
						.send(request, HttpResponse.BodyHandlers.ofString());

				String json = response.body();
				System.out.println("JSON recebido: " + json);

				TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
				System.out.println("Objeto TituloOmdb: " + meuTituloOmdb);

				Titulo meuTitulo = new Titulo(meuTituloOmdb);
				System.out.println("Título já convertido: " + meuTitulo);

				titulos.add(meuTitulo);

			} catch (NumberFormatException e) {
				System.out.println("Aconteceu um erro de formato de número: ");
				System.out.println(e.getMessage());
			} catch (IllegalArgumentException e) {
				System.out.println("Argumento inválido na busca, verifique o endereço.");
				System.out.println(e.getMessage());
			} catch (ErrorDeConversaoDeAnoException e) {
				System.out.println("Erro de conversão customizado: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
			}
		}

		System.out.println("Títulos buscados:");
		System.out.println(titulos);

		// A gravação do arquivo também faz parte da lógica de execução.
		FileWriter escrita = new FileWriter("filmes.json");
		escrita.write(gson.toJson(titulos));
		escrita.close();

		System.out.println("Programa concluído com sucesso e arquivo 'filmes.json' foi salvo!");
	}
}