package archiveOperation;

import catalog.Book;

import java.util.InputMismatchException;
import java.util.Objects;

import static other.Tools.catalogo;
import static other.Tools.input;

public class Ricerca {
    public static void ricerca() {
        try {
            System.out.println("come vuoi ricercare ? isbn , anno o autore ");
            String researchFromUser = input.nextLine().toLowerCase().trim();
            switch (researchFromUser) {
                case "isbn": {
                    System.out.println("inserire isbm da ricercare ");
                    int researchInputInt = Integer.parseInt(input.nextLine());
                    catalogo.stream()
                            .filter(Material -> Material.getISBN() == researchInputInt)
                            .forEach(System.out::println);
                    break;
                }
                case "anno": {
                    System.out.println("inserire anno da ricercare ");
                    int researchInputInt = Integer.parseInt(input.nextLine());
                    catalogo.stream()
                            .filter(Material -> Material.getAnnoDiPubblicazione() == researchInputInt)
                            .forEach(System.out::println);
                    break;
                }
                case "autore": {
                    System.out.println("inserire autore da ricercare ");
                    String researchInputStr = input.nextLine().toLowerCase().trim();
                    System.out.println(researchInputStr);
                    catalogo.stream()
                            .filter(Material -> Material.getClass() == Book.class && Objects.equals(((Book) Material)
                                    .getAutore(), researchInputStr)).forEach(System.out::println);
                    break;
                }
                default: {
                    System.out.println("eh? cosa ?");
                }
            }
        } catch (InputMismatchException | NumberFormatException ex) {
            System.err.println("input non valido");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

    }

}
