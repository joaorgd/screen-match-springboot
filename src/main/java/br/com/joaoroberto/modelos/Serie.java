package br.com.joaoroberto.modelos;

public class Serie extends Titulo {
    private int temporadas;
    private boolean ativa;
    private int episodiosPorTemporada;
    private int minutosPorEpisodio;

    public Serie(String nome, int anoDeLancamento) {
        super(nome, anoDeLancamento);
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public boolean isAtiva() {

        return ativa;
    }

    public void setAtiva(boolean ativa) {

        this.ativa = ativa;
    }

    public int getEpisodiosPorTemporada() {

        return episodiosPorTemporada;
    }

    public void setEpisodiosPorTemporada(int episodiosPorTemporada) {
        this.episodiosPorTemporada = episodiosPorTemporada;
    }

    public int getMinutosPorEpisodio() {

        return minutosPorEpisodio;
    }

    public void setMinutosPorEpisodio(int minutosPorEpisodio) {

        this.minutosPorEpisodio = minutosPorEpisodio;
    }

    @Override //A notação serve para informar sobre qualquer modificação no metodo.
    public int getDuracaoEmMinutos() {

        return temporadas * episodiosPorTemporada * minutosPorEpisodio;
    }

    @Override
    public void exibeFichaTecnica() {
        System.out.println("Nome: " + getNome());
        System.out.println("Ano de Lançamento: " + getAnoDeLancamento());
        System.out.println("Temporadas: " + getTemporadas());
        System.out.println("Episódios por temporada: " + getEpisodiosPorTemporada());
        System.out.println("Minutos por epispódio: " + getMinutosPorEpisodio());
    }

    @Override
    public String toString() {
        return "Série: " + this.getNome() + " (" + this.getAnoDeLancamento() + ")";
    }
}
