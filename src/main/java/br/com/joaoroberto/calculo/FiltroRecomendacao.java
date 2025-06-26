package br.com.joaoroberto.calculo;

public class FiltroRecomendacao {
    public void filtra(Classificavel classificavel) {
        if (classificavel.getClassificacao() > 4) {
            System.out.println("Está entre os favoritos do momento.");
        } else if (classificavel.getClassificacao() > 2) {
            System.out.println("Está bem classificado no momento.");
        } else {
            System.out.println("Adicionar em sua lista.");
        }
    }
}
