package coso;

import catalog.Book;
import catalog.Magazine;
import catalog.Material;
import com.github.javafaker.Faker;
import enums.Periodo;

import java.util.*;
import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {
        Faker faker = new Faker();
        Random rnd = new Random();
        Scanner input = new Scanner(System.in);


        Periodo[] rndPerdiodo = Periodo.values();


        Supplier<Book> bookSupplier = () -> {
            return new Book(faker.book().author(), faker.book().genre(), faker.book().title(), rnd.nextInt(1950, 2023), rnd.nextInt(50, 500));
        };
        Supplier<Magazine> magazineSupplier = () -> {
            return new Magazine(faker.book().title(), rnd.nextInt(1950, 2023), rnd.nextInt(50, 500), rndPerdiodo[rnd.nextInt(0, 2)]);
        };

        List<Material> catalogo = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            catalogo.add(bookSupplier.get());
        }
        for (int i = 0; i < 10; i++) {
            catalogo.add(magazineSupplier.get());
        }
        System.out.println(catalogo);


        while (true) {
            try {
                System.out.println("1 : per aggiungere o rimuovere");
                System.out.println("2 : per ricercare");
                System.out.println("3 : per salvare o ricaricare");
                System.out.println("4 : per vedere l'archivio");
                int userInput = Integer.parseInt(input.nextLine());
                switch (userInput) {
                    case 1: {
                        System.out.println("vuoi aggiungere  un libro o una rivista o rimuovere qualcosa ? +book, +mag o -item ");
                        String plusOminus = input.nextLine();
                        switch (plusOminus) {
                            case "+book": {
                                System.out.println("autore ?");
                                String autor = input.nextLine();
                                System.out.println("titoto ?");
                                String title = input.nextLine();
                                System.out.println("genere ?");
                                String gener = input.nextLine();
                                System.out.println("anno ?");
                                int year = Integer.parseInt(input.nextLine());
                                System.out.println("num pagine ?");
                                int pageNumber = Integer.parseInt(input.nextLine());
                                catalogo.add(new Book(autor, title, gener, year, pageNumber));
                                System.out.println("aggiunta di " + title + " avvenuta con successo");
                                break;
                            }
                            case "+mag": {
                                System.out.println("autore ?");
                                String autor = input.nextLine();
                                System.out.println("anno ?");
                                int year = Integer.parseInt(input.nextLine());
                                System.out.println("num pagine ?");
                                int pageNumber = Integer.parseInt(input.nextLine());
                                System.out.println("periodo ? 1 : settimanale , 2 : mensile , 3 : sequestrale , altro : sconosciuto ");
                                int perSel = Integer.parseInt(input.nextLine());
                                if (perSel > 3 || perSel < 1) perSel = 4;
                                catalogo.add(new Magazine(autor, year, pageNumber, rndPerdiodo[perSel]));
                                System.out.println("aggiunta della rivista avvenuta con successo");
                                break;
                            }
                            case "-item": {
                                System.out.println("numero ISMB ?");
                                int itemToRemove = Integer.parseInt(input.nextLine());
                                Iterator<Material> i = catalogo.iterator();
                                while (i.hasNext()) {
                                    Material current = i.next();
                                    if (current.getISBN() == itemToRemove) {
                                        System.out.println("rimozione dell'oggetto " + current.getISBN() + " avvenuta con successo");
                                        i.remove();
                                        break;
                                    }
                                }
                                break;
                            }
                            default: {
                                System.out.println("eh ? cosa ? ");
                            }
                        }
                    }
                    case 4: {
                        System.out.println(catalogo);
                    }
                    default: {
                        System.out.println("eh? cosa ?");
                    }

                }


            } catch (InputMismatchException ex) {
                System.err.println("input non valido");
            }
        }
    }
}
