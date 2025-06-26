package br.com.joaoroberto.modelos;

import br.com.joaoroberto.calculo.Classificavel;

public class Filme extends Titulo implements Classificavel { //A palavra reservada "extends" é para ativar a herança.
    private String diretor;

    /* Em Java, um construtor é um método especial usado para criar e inicializar um objeto recém-criado. Quando uma classe é definida, ela pode ter um ou mais construtores, sendo que se nenhum construtor for definido explicitamente, o Java criará um construtor default (padrão) automaticamente. */
    public Filme(String nome, int anoDeLancamento) {
        super(nome, anoDeLancamento);
    }

    public String getDiretor() {

        return diretor;
    }

    public void setDiretor(String diretor) {

        this.diretor = diretor;
    }

    @Override
    public int getClassificacao() {
        return (int) pegaMedia() / 2;
    }

    @Override
    public String toString() {
        return "Filme: " + this.getNome() + " (" + getAnoDeLancamento() + ")";
    }
}
