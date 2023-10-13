package catalog;

import enums.Periodo;

import java.util.Random;


public class Magazine extends Material {

    Random rnd = new Random();
    private int ISBN;
    private String titolo;
    private int annoDiPubblicazione;
    private int numeroPagine;
    private Periodo periodo;


    //costruttore
    public Magazine(String titolo, int annoDiPubblicazione, int numeroPagine, Periodo periodo) {
        this.ISBN = rnd.nextInt(1001, 2000);
        this.titolo = titolo;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.numeroPagine = numeroPagine;
        this.periodo = periodo;
    }

    public Magazine(String titolo, int annoDiPubblicazione, int numeroPagine, Periodo periodo, int ISBN) {
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.numeroPagine = numeroPagine;
        this.periodo = periodo;
    }


    // getters
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }
    //setters

    public void setAnnoDiPubblicazione(int annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }


    @Override
    public String toString() {
        return "rivista" +
                " periodo='" + periodo +
                " ISBN=" + ISBN +
                " titolo='" + titolo +
                " annoDiPubblicazione=" + annoDiPubblicazione +
                " numeroPagine=" + numeroPagine +
                "\n";
    }

    @Override
    public String save() {
        return titolo + "@" + annoDiPubblicazione + "@" + numeroPagine + "@" + periodo + "@" + ISBN + "#";
    }


}
