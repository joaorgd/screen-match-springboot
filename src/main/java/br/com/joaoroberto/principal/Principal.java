package br.com.joaoroberto.principal;

import br.com.joaoroberto.service.ConsumoApi;

import java.util.Scanner;

// A classe Principal agora gerencia a interação com o usuário e o fluxo da aplicação.
public class Principal {
    private Scanner leitura = new Scanner(System.in);

    // Instância do serviço para consumir a API. A lógica de fazer a requisição HTTP
    // fica encapsulada nesta classe.
    private ConsumoApi consumo = new ConsumoApi();

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
    }
}

// Declaração de constantes com 'static final':
//
// 'final': Garante que o valor da variável não pode ser alterado após sua inicialização.
// 'static': Faz com que a variável pertença à classe, e não a cada objeto individual (instância).
//
//  Isso significa que:
//  1. Instância Única: Existirá apenas UMA cópia destas constantes na memória, compartilhada
//  por todos os objetos da classe Principal. Economiza memória.
//  2. Acesso Direto: Podem ser acessadas diretamente através da classe (ex: Principal.ENDERECO),
//  sem a necessidade de criar um objeto.
//
//  É uma prática padrão e recomendada em Java para declarar constantes.