package br.com.joaoroberto.principal;

import br.com.joaoroberto.model.DadosEpisodio;
import br.com.joaoroberto.model.DadosSerie;
import br.com.joaoroberto.model.DadosTemporada;
import br.com.joaoroberto.model.Episodio;
import br.com.joaoroberto.service.ConsumoApi;
import br.com.joaoroberto.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=2d9a4818";

    public void exibeMenu() {
        System.out.print("Digite uma série para busca: ");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\nTop 5 episódios: ");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        /**
         * O código abaixo transforma os dados brutos dos episódios (DadosEpisodio)
         * em objetos mais robustos e completos da nossa classe de domínio (Episodio).
         *
         * 1. .stream(): Cria um fluxo a partir da lista de temporadas.
         * 2. .flatMap(): "Achata" o fluxo, pegando a lista de episódios de cada temporada.
         * 3. .map(): Para cada dado de episódio (d), cria um objeto 'Episodio',
         * passando o número da temporada (t.numero()) e os dados do episódio (d).
         * 4. .collect(): Coleta todos os novos objetos 'Episodio' numa lista.
         */
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());

        // Imprime a lista de objetos Episodio recém-criada, agora com dados tratados.
        episodios.forEach(System.out::println);

        System.out.println("A partir de qual ano você deseja visualizar os episódios?");
        var ano = leitura.nextInt();
        leitura.nextLine();

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "(Temporada: " + e.getTemporada() + ") " +
                                "(Episódio: " + e.getTitulo() + ") " +
                                "(Data de Lançamento: " + e.getDataLancamento().format(formatador) + ")"
                ));
    }
}