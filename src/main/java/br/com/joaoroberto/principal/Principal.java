package br.com.joaoroberto.principal;

import br.com.joaoroberto.model.DadosEpisodio;
import br.com.joaoroberto.model.DadosSerie;
import br.com.joaoroberto.model.DadosTemporada;
import br.com.joaoroberto.service.ConsumoApi;
import br.com.joaoroberto.service.ConverteDados;

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

        /**
         * O que é uma Stream (Fluxo)?
         * De forma resumida, uma Stream é uma sequência de elementos de uma fonte de dados
         * (como uma lista) que suporta operações de processamento em cadeia.
         * Ela não armazena os dados, apenas define as operações a serem feitas (filtrar, ordenar, etc).
         */

        // Cria uma lista única com todos os episódios de todas as temporadas.
        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()) // "Achata" as listas de episódios num único fluxo.
                .collect(Collectors.toList()); // Coleta o resultado do fluxo numa nova lista.

        System.out.println("\nTop 5 episódios: ");
        // Processa a lista de episódios num fluxo para realizar as operações.
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A")) // Filtra os que não têm avaliação.
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed()) // Ordena pela avaliação, da maior para a menor.
                .limit(5) // Limita a seleção aos 5 primeiros.
                .forEach(System.out::println); // Imprime o resultado.
    }
}