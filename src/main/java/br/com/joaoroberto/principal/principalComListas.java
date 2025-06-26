package br.com.joaoroberto.principal;

import br.com.joaoroberto.modelos.Filme;
import br.com.joaoroberto.modelos.Serie;
import br.com.joaoroberto.modelos.Titulo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class principalComListas {
    public static void main(String[] args) {
        Filme meuFilme = new Filme("Star Wars Episode IV: A New Hope", 1977);
        meuFilme.avalia(9);
        Filme outroFilme = new Filme("Star Wars Episode V: The Empire Strikes Back", 1980);
        outroFilme.avalia(9.5);
        var filmeDoJoao = new Filme("Star Wars Episode VI: The Return Of The Jedi", 1983);
        filmeDoJoao.avalia(8);
        Serie lost = new Serie("Lost", 2000);
        lost.avalia(8.5);

        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(filmeDoJoao);
        lista.add(meuFilme);
        lista.add(outroFilme);
        lista.add(lost);

        /* A forma mais comum de percorrer uma lista no Java é utilizando o laço foreach tradicional (for ( : ) {}), também conhecido como for-each. Esse laço permite que se percorra todos os elementos de uma lista, sem a necessidade de se preocupar com índices ou o tamanho dela, tornando o código mais simples e legível. */
        for (Titulo item : lista) {
            System.out.println(item.getNome());
            if  (item instanceof Filme filme && filme.getClassificacao() > 2) {
                System.out.println("Classificação: " + filme.getClassificacao());
            }
        }

        ArrayList<String> buscaPorArtista= new ArrayList<>();
        buscaPorArtista.add("Timothée Chalamet");
        buscaPorArtista.add("Brad Pitt");
        buscaPorArtista.add("Anne Hathaway");
        System.out.println(buscaPorArtista);

        Collections.sort(buscaPorArtista);
        System.out.println("Lista ordendada: " + buscaPorArtista);

        System.out.println("Lista ordenada de títulos decrescente: " + lista);
        Collections.sort(lista);

        lista.sort(Comparator.comparing(Titulo::getAnoDeLancamento));
        System.out.println("Lista ordenada de títulos crescente: " + lista);
    }
}
