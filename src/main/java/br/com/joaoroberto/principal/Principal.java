package br.com.joaoroberto.principal;

import br.com.joaoroberto.model.DadosEpisodio;
import br.com.joaoroberto.model.DadosSerie;
import br.com.joaoroberto.model.DadosTemporada;
import br.com.joaoroberto.service.ConsumoApi;
import br.com.joaoroberto.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=2d9a4818";

    public void exibeMenu() {
        System.out.println("Digite uma série para busca: ");
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

        // O bloco de código abaixo, que usava dois laços 'for' aninhados,
        // foi substituído por uma forma mais funcional e concisa utilizando lambdas.
        // for(int i = 0; i < dados.totalTemporadas(); i++) {
        //     List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
        //     for(int j = 0; j < episodiosTemporada.size(); j++) {
        //         System.out.println(episodiosTemporada.get(j).titulo());
        //     }
        // }


//          O código abaixo utiliza expressões Lambda para realizar a mesma tarefa
//          que os dois laços 'for' comentados acima, mas de forma mais enxuta.
//
//          O que é uma Lambda?
//          De forma resumida, uma Lambda é uma função anônima, curta e sem nome,
//          que pode ser passada como um argumento para um método.
//          A estrutura é: (argumentos) -> { corpo da função }
//
//          Como funciona aqui:
//          1. temporadas.forEach(...): Para cada temporada na lista...
//          2. t -> ... : 't' é o nosso argumento, representando um objeto DadosTemporada.
//          3. t.episodios().forEach(...): Para a lista de episódios da temporada 't',
//          execute outro forEach...
//          4. e -> System.out.println(e.titulo()): 'e' é o argumento que representa
//          um DadosEpisodio. A ação é imprimir o seu título.

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}