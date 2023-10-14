package archiveOperation;

import catalog.Book;
import catalog.Magazine;
import catalog.Material;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.function.Supplier;

import static other.Tools.*;

public class AggiungiOrimuovi {
    public static void operazione() {


        Supplier<Book> bookSupplier = () -> {
            return new Book(faker.book().author(), faker.book().genre(), faker.book().title(), rnd.nextInt(1950, 2023), rnd.nextInt(50, 500));
        };
        Supplier<Magazine> magazineSupplier = () -> {
            return new Magazine(faker.book().title(), rnd.nextInt(1950, 2023), rnd.nextInt(50, 500), rndPerdiodo[rnd.nextInt(0, 2)]);
        };


        try {
            System.out.println("vuoi aggiungere  un libro o una rivista o rimuovere qualcosa ? +book, +mag, -item  , random book o random mag ");
            String plusOminus = input.nextLine().trim().toLowerCase();
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
                    Book libroGenerato = new Book(autor, title, gener, year, pageNumber);
                    catalogo.add(libroGenerato);
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
                case "random book": {
                    catalogo.add(bookSupplier.get());
                    break;
                }
                case "random mag": {
                    catalogo.add(magazineSupplier.get());
                    break;
                }
                default: {
                    System.out.println("eh ? cosa ? ");
                }
            }

        } catch (InputMismatchException | NumberFormatException ex) {
            System.err.println("input non valido");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("periodo non valido");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}

