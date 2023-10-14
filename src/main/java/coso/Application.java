package coso;

import archiveOperation.AggiungiOrimuovi;
import archiveOperation.Ricerca;
import archiveOperation.SalvaOCarica;

import java.util.InputMismatchException;

import static other.Tools.catalogo;
import static other.Tools.input;

public class Application {

    public static void main(String[] args) {


        try {
            SalvaOCarica.primoCaricamento();
            esterno:
            while (true) {
                System.out.println("1 : per aggiungere o rimuovere");
                System.out.println("2 : per ricercare");
                System.out.println("3 : per salvare o ricaricare");
                System.out.println("4 : per vedere l'archivio");
                System.out.println("5 : per uscire");
                String userInputStr = input.nextLine();
                switch (userInputStr) {
                    case "1": {
                        AggiungiOrimuovi.operazione();
                        break;
                    }
                    case "2": {
                        Ricerca.ricerca();
                        break;
                    }
                    case "3": {
                        SalvaOCarica.salvaOcarica();
                        break;
                    }
                    case "4": {
                        System.out.println(catalogo);
                        break;
                    }
                    case "5": {
                        System.out.println("uscita");
                        break esterno;
                    }
                    default: {
                        System.out.println("eh? cosa ?");
                    }

                }
            }
        } catch (InputMismatchException | NumberFormatException ex) {
            System.err.println("input non valido");
        } catch (Exception ex) {
            System.err.println("errore generico");
            System.out.println(ex.getMessage());
        } finally {
            input.close();
        }
    }


}

