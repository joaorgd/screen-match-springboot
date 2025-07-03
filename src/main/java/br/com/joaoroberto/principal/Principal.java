package br.com.joaoroberto.principal;

import br.com.joaoroberto.model.DadosEpisodio;
import br.com.joaoroberto.model.DadosSerie;
import br.com.joaoroberto.model.DadosTemporada;
import br.com.joaoroberto.model.Episodio;
import br.com.joaoroberto.service.ConsumoApi;
import br.com.joaoroberto.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());

        episodios.forEach(System.out::println);

        // Busca de episódio por trecho do título.
        System.out.print("Digite o trecho do título do episódio: ");
        var trechoTitulo = leitura.nextLine();
        // Utiliza um fluxo (stream) para filtrar a lista de episódios.
        // O método findFirst() retorna um Optional, que pode ou não conter um valor.
        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
                .findFirst();
        // Verifica se o Optional contém um valor antes de tentar acessá-lo.
        if(episodioBuscado.isPresent()) {
            System.out.println("Episódio encontrado!");
            System.out.println("(Temporada: " + episodioBuscado.get().getTemporada() + ") " + "(Episódio: " + episodioBuscado.get().getNumeroEpisodio() + ")");
        } else {
            System.out.println("Episódio não encontrado!");
        }

        // Busca de episódios por ano de lançamento.
        System.out.println("A partir de qual ano você deseja visualizar os episódios?");
        var ano = leitura.nextInt();
        leitura.nextLine(); // Consome a quebra de linha pendente.

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        // Define um formatador para exibir a data no padrão brasileiro (dd/MM/yyyy).
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Filtra e exibe os episódios lançados após a data especificada.
        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "(Temporada: " + e.getTemporada() + ") " +
                                "(Episódio: " + e.getTitulo() + ") " +
                                "(Data de Lançamento: " + e.getDataLancamento().format(formatador) + ")"
                ));
    }
}