package br.com.joaoroberto.principal;

import br.com.joaoroberto.calculo.CalculdadoraDeTempo;
import br.com.joaoroberto.calculo.FiltroRecomendacao;
import br.com.joaoroberto.modelos.Episodio;
import br.com.joaoroberto.modelos.Filme;
import br.com.joaoroberto.modelos.Serie;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {

        Filme meuFilme = new Filme("Star Wars Episode IV: A New Hope", 1977);
        meuFilme.setDuracaoEmMinutos(121);
        System.out.println("Duração do filme: " + meuFilme.getDuracaoEmMinutos());

        meuFilme.exibeFichaTecnica();
        meuFilme.avalia(10);
        meuFilme.avalia(8);
        meuFilme.avalia(7);
        //System.out.println("Soma das Avaliações: " + meuFilme.somaDasAvalicoes);
        System.out.println("Total das Avaliações: " + meuFilme.getTotalDeAvaliacoes());
        System.out.println("Média das Avaliações: " + meuFilme.pegaMedia());

        Serie lost = new Serie("Lost", 2000);
        lost.setAnoDeLancamento(2004);
        lost.setTemporadas(6);
        lost.setEpisodiosPorTemporada(18);
        lost.setMinutosPorEpisodio(50);
        lost.exibeFichaTecnica();
        System.out.println("Duração da maratona Lost: " + lost.getDuracaoEmMinutos());

        Filme outroFilme = new Filme("Star Wars Episode V: The Empire Strikes Back", 1980);
        outroFilme.setDuracaoEmMinutos(124);

        CalculdadoraDeTempo calculadora = new CalculdadoraDeTempo();
        calculadora.inclui(meuFilme);
        calculadora.inclui(outroFilme);
        calculadora.inclui(lost);
        System.out.println("A duração do filme é de: " + calculadora.getTempoTotal());

        FiltroRecomendacao filtro = new FiltroRecomendacao();
        filtro.filtra(meuFilme);

        Episodio episodio = new Episodio();
        episodio.setNumero(1);
        episodio.setSerie(lost);
        episodio.setTotalVisualizacao(300);
        filtro.filtra(episodio);

        /* A palavra-chave 'var' permite que o compilador infira automaticamente o tipo da variável com
        base no valor lhe atribuído.
        1. O tipo da variável deve ser inferido automaticamente pelo compilador. Isso significa que não é possível
           utilizar var em variáveis cujo tipo não possa ser inferido automaticamente.
        2. Não é possível usar var em variáveis sem valor inicial. É necessário atribuir um valor à variável na mesma
           linha em que ela é declarada. */
        var filmeDoJoao = new Filme("Star Wars Episode VI: The Return Of The Jedi", 1983);
        filmeDoJoao.setDuracaoEmMinutos(137);
        filmeDoJoao.avalia(9);

        /* Em Java, arrays são estruturas de dados que permitem armazenar uma coleção de elementos do mesmo tipo. Eles são muito utilizados para manipulação de dados em projetos de programação. */
        ArrayList<Filme> listaDeFilmes = new ArrayList<>();
        listaDeFilmes.add(filmeDoJoao);
        listaDeFilmes.add(meuFilme);
        listaDeFilmes.add(outroFilme);
        System.out.println("Tamanho da lista: " + listaDeFilmes.size());
        System.out.println("Primeiro filme: " + listaDeFilmes.getFirst().getNome());
        System.out.println(listaDeFilmes);
        System.out.println("ToString do "  + listaDeFilmes.getFirst().toString());
    }
}
