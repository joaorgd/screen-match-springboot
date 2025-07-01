package br.com.joaoroberto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//  @JsonIgnoreProperties(ignoreUnknown = true): Esta anotação é muito importante.
//  Ela instrui o Jackson a simplesmente ignorar quaisquer campos do JSON que não
//  estejam explicitamente definidos aqui neste record. Sem ela, se o JSON da API
//  tivesse campos extras (como "Year", "Rated", "Poster", etc.), a conversão
//  falharia com um erro.

@JsonIgnoreProperties(ignoreUnknown = true)
// 'public record' é uma forma moderna e concisa de declarar uma classe que serve
// principalmente para armazenar dados de forma imutável. O Java automaticamente
// cria o construtor, métodos getters, toString(), equals() e hashCode() para você.

public record DadosSerie(

// @JsonAlias("Title"): Usado durante a desserialização (conversão de JSON para Java),
// esta anotação mapeia o campo "Title" do JSON para o atributo "titulo" do nosso record.
// Isso permite que usemos os padrões de nomenclatura do Java (camelCase) na nossa classe,
// mesmo que o JSON use um padrão diferente (PascalCase).

        @JsonAlias("Title") String titulo,
        @JsonAlias("totalSeasons") Integer totalTemporadas,
        @JsonAlias("imdbRating") String avaliacao) {

}