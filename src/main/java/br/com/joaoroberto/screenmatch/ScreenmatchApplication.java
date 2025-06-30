package br.com.joaoroberto.screenmatch;

import br.com.joaoroberto.model.DadosEpisodio;
import br.com.joaoroberto.model.DadosSerie;
import br.com.joaoroberto.model.DadosTemporada;
import br.com.joaoroberto.service.ConsumoApi;
import br.com.joaoroberto.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/*
 * A interface CommandLineRunner faz com que o método "run" seja executado
 * automaticamente logo após a inicialização da aplicação Spring Boot.
 * É aqui que a lógica principal da nossa aplicação de console reside.
 */
@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        // Cria uma instância do nosso serviço de consumo de API.
        var ConsumoApi = new ConsumoApi();
        // Realiza a primeira chamada à API para obter os dados gerais da série.
        var json = ConsumoApi.obterDados("https://www.omdbapi.com/?t=game+of+thrones&apikey=2d9a4818");
        // Imprime o JSON bruto para fins de depuração.
        System.out.println(json);

        // Cria uma instância do nosso serviço de conversão de dados.
        ConverteDados conversor = new ConverteDados();
        // Converte o JSON dos dados da série para um objeto do tipo DadosSerie.
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        // Imprime o objeto DadosSerie, usando o método toString() gerado pelo record.
        System.out.println(dados);

        // Realiza uma segunda chamada à API, desta vez para um episódio específico (demonstração).
        json = ConsumoApi.obterDados("https://www.omdbapi.com/?t=game+of+thrones&season=1&episode=1&apikey=2d9a4818");
        // Converte o JSON do episódio para um objeto do tipo DadosEpisodio.
        DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
        System.out.println(dadosEpisodio);

        // Cria uma lista para armazenar os dados de todas as temporadas.
        List<DadosTemporada> temporadas = new ArrayList<>();

        // Loop 'for' para buscar os dados de cada temporada individualmente.
        // O loop vai de 1 até o número total de temporadas obtido na primeira chamada.
        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            // Para cada iteração, faz uma nova chamada à API, especificando o número da temporada.
            json = ConsumoApi.obterDados("https://www.omdbapi.com/?t=game+of+thrones&season=" + i + "&apikey=2d9a4818");
            // Converte o JSON da temporada para um objeto DadosTemporada.
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            // Adiciona a temporada convertida à nossa lista de temporadas.
            temporadas.add(dadosTemporada);
        }
        // Utiliza um forEach com method reference para imprimir os dados de todas as temporadas
        // de forma concisa e legível.
        temporadas.forEach(System.out::println);
    }
}