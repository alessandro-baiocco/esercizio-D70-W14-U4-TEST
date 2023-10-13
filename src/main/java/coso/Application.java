package coso;

import catalog.Book;
import catalog.Magazine;
import catalog.Material;
import com.github.javafaker.Faker;
import enums.Periodo;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Supplier;

public class Application {
    static List<Material> catalogo = new ArrayList<>();
    static Periodo[] rndPerdiodo = Periodo.values();
    static Faker faker = new Faker();
    static Random rnd = new Random();
    static Scanner input = new Scanner(System.in);
    static List<Book> libri = new ArrayList<>();

    public static void main(String[] args) {

        Supplier<Book> bookSupplier = () -> {
            return new Book(faker.book().author(), faker.book().genre(), faker.book().title(), rnd.nextInt(1950, 2023), rnd.nextInt(50, 500));
        };
        Supplier<Magazine> magazineSupplier = () -> {
            return new Magazine(faker.book().title(), rnd.nextInt(1950, 2023), rnd.nextInt(50, 500), rndPerdiodo[rnd.nextInt(0, 2)]);
        };


        for (int i = 0; i < 10; i++) {
            Book libroGenerato = bookSupplier.get();
            catalogo.add(libroGenerato);
            libri.add(libroGenerato);
        }
        for (int i = 0; i < 10; i++) {
            catalogo.add(magazineSupplier.get());
        }
        System.out.println(catalogo);

        try {
            while (true) {
                System.out.println("1 : per aggiungere o rimuovere");
                System.out.println("2 : per ricercare");
                System.out.println("3 : per salvare o ricaricare");
                System.out.println("4 : per vedere l'archivio");
                String userInputStr = input.nextLine();
                switch (userInputStr) {
                    case "1": {
                        aggiungiOrimuovi();
                        break;
                    }
                    case "2": {
                        ricerca();
                        break;
                    }
                    case "3": {
                        System.out.println("vuoi salvare o ricaricare ? salva o carica");
                        String saveOreload = input.nextLine();
                        switch (saveOreload) {
                            case "salva": {
                                File file = new File("src/output.txt");
                                StringBuilder catalogoCompleto = new StringBuilder();
                                for (int i = 0; i < catalogo.size(); i++) {
                                    catalogoCompleto.append(catalogo.get(i).toString()).append(System.getProperty("line.separator"));
                                }
                                try {
                                    FileUtils.writeStringToFile(file, catalogoCompleto + System.lineSeparator(), StandardCharsets.UTF_8);
                                } catch (IOException e) {
                                    System.err.println(e.getMessage());
                                }
                                break;
                            }


                            default: {
                                System.out.println("eh ? cosa ? ");
                            }
                        }
                        break;
                    }
                    case "4": {
                        System.out.println(catalogo);
                        break;
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
            System.out.println(ex);
        } finally {
            input.close();
        }
    }


    public static void aggiungiOrimuovi() {
        try {
            System.out.println("vuoi aggiungere  un libro o una rivista o rimuovere qualcosa ? +book, +mag o -item ");
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
                    libri.add(libroGenerato);
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
        } catch (InputMismatchException | NumberFormatException ex) {
            System.err.println("input non valido");
        } catch (Exception ex) {
            System.err.println("errore");
        }
    }

    public static void ricerca() {
        try {

            System.out.println("come vuoi ricercare ? isbm , anno o autore ");
            String researchFromUser = input.nextLine().toLowerCase().trim();
            switch (researchFromUser) {
                case "isbm": {
                    System.out.println("inserire isbm da ricercare ");
                    int researchInputInt = Integer.parseInt(input.nextLine());
                    catalogo.stream().filter(Material -> Material.getISBN() == researchInputInt).forEach(System.out::println);
                    break;
                }
                case "anno": {
                    System.out.println("inserire anno da ricercare ");
                    int researchInputInt = Integer.parseInt(input.nextLine());
                    catalogo.stream().filter(Material -> Material.getAnnoDiPubblicazione() == researchInputInt).forEach(System.out::println);
                    break;
                }
                case "autore": {
                    System.out.println("inserire autore da ricercare ");
                    String researchInputStr = input.nextLine().toLowerCase().trim();
                    System.out.println(researchInputStr);
                    libri.stream().filter(Book -> Book.getAutore().equals(researchInputStr)).forEach(System.out::println);
                    break;
                }

                default: {
                    System.out.println("eh? cosa ?");
                }
            }
        } catch (InputMismatchException | NumberFormatException ex) {
            System.err.println("input non valido");
        } catch (Exception ex) {
            System.err.println("errore");
        }

    }


}

