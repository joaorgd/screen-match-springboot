package br.com.joaoroberto.principal;

import br.com.joaoroberto.exception.ErrorDeConversaoDeAnoException;
import br.com.joaoroberto.modelos.Titulo;
import br.com.joaoroberto.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura =  new Scanner(System.in);

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();

        String busca = "";
        List<Titulo> titulos = new ArrayList<>();

        while(!busca.equalsIgnoreCase("sair")) {

            System.out.print("Digite um filme: ");
            busca = leitura.nextLine();

            if (busca.equalsIgnoreCase("sair")){
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
                System.out.println(json);

                TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                System.out.println(meuTituloOmdb);
                //try {
                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println("Título convertido: ");
                System.out.println(meuTitulo);

               titulos.add(meuTitulo);

            } catch (NumberFormatException e) {
                System.out.println("Ocorreu um erro: ");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Argumento inválido, verifique a busca.");
            } catch (ErrorDeConversaoDeAnoException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(titulos);

        FileWriter escrita = new FileWriter("filmes.json");
        escrita.write(gson.toJson(titulos));
        escrita.close();
        System.out.println("Programa concluído com sucesso!");
    }
}
//Ctrl + Alt + i (atalho para formatar a indentação do código)