package br.com.joaoroberto.principal;

import br.com.joaoroberto.model.DadosSerie;
import br.com.joaoroberto.service.ConsumoApi;
import br.com.joaoroberto.service.ConverteDados;

import java.util.Scanner;

// A classe Principal agora gerencia a interação com o usuário e o fluxo da aplicação.
public class Principal {
    private Scanner leitura = new Scanner(System.in);

    // Instância do serviço para consumir a API. A lógica de fazer a requisição HTTP
    // fica encapsulada nesta classe.
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    // Constantes para evitar a repetição de "strings mágicas" no código.
    // Usar constantes torna o código mais limpo e fácil de manter.
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=2d9a4818";

    // Método principal que exibe o menu e orquestra as chamadas.
    public void exibeMenu() {
        System.out.println("Digite uma série para busca: ");
        var nomeSerie = leitura.nextLine();
        // Concatena o endereço, o nome da série (com espaços substituídos por '+') e a chave da API
        // para formar a URL completa da requisição.
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);
    }
}