package catalog;

import java.util.Random;

public class Book extends Material {

    Random rnd = new Random();

    private String autore;
    private String genere;
    private int ISBN;
    private String titolo;
    private int annoDiPubblicazione;
    private int numeroPagine;


    //costruttore
    public Book(String autore, String genere, String titolo, int annoDiPubblicazione, int numeroPagine) {
        this.autore = autore;
        this.genere = genere;
        this.ISBN = rnd.nextInt(1, 1000);
        this.titolo = titolo;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public Book(String autore, String genere, String titolo, int annoDiPubblicazione, int ISBN, int numeroPagine) {
        this.autore = autore;
        this.genere = genere;
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.numeroPagine = numeroPagine;
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

    public void setAnnoDiPubblicazione(int annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    //setters

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public String getAutore() {
        return autore.toLowerCase().trim();
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }


    @Override
    public String toString() {
        return "Libro " +
                "autore=" + autore +
                ", genere=" + genere +
                ", ISBN=" + ISBN +
                ", titolo=" + titolo +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroPagine=" + numeroPagine
                + "\n";
    }

    @Override
    public String save() {
        return autore + "@" + genere + "@" + titolo + "@" + annoDiPubblicazione + "@" + ISBN + "@" + numeroPagine + "#";
    }


}
