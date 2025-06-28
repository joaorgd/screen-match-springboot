package br.com.joaoroberto.model;

import com.fasterxml.jackson.annotation.JsonAlias;

// A anotação @JsonAlias define um nome alternativo para o campo durante a
// desserialização (conversão de JSON para Objeto Java). Assim, o Jackson
// sabe que o campo "Title" do JSON deve ser colocado no campo "titulo" do record.
public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("totalSeasons") Integer totalTemporadas,
                         @JsonAlias("imdbRating") String avaliacao) {

}
