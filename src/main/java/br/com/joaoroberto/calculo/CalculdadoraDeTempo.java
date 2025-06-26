package br.com.joaoroberto.calculo;

import br.com.joaoroberto.modelos.Titulo;

public class CalculdadoraDeTempo {
    private int tempoTotal;

    public int getTempoTotal() {
        return tempoTotal;
    }

//    public void inclui(Filme f) {
//        tempoTotal += f.getDuracaoEmMinutos();
//    }
//
//    public void inclui(Serie s) {
//        tempoTotal += s.getDuracaoEmMinutos();
//    } //Overload de método. Aqui, usei o mesmo método, mas com uma funcionalidade diferente.
//}

    public void inclui(Titulo titulo) {
        this.tempoTotal += titulo.getDuracaoEmMinutos();
    } //Polimorfismo é o nome dado as várias de se "chamar" um método.
}
