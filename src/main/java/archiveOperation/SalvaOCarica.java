package archiveOperation;

import catalog.Book;
import catalog.Magazine;
import catalog.Material;
import enums.Periodo;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import static other.Tools.catalogo;
import static other.Tools.input;

public class SalvaOCarica {
    public static void salvaOcarica() {
        try {
            System.out.println("vuoi salvare o ricaricare ? salva o carica");
            String saveOreload = input.nextLine();
            switch (saveOreload) {
                case "salva": {
                    File file = new File("src/output.txt");
                    String catalogoItemToStr = "";
                    for (int i = 0; i < catalogo.size(); i++) {
                        catalogoItemToStr += catalogo.get(i).save();
                    }
                    FileUtils.writeStringToFile(file, catalogoItemToStr, StandardCharsets.UTF_8);
                    System.out.println("salvataggio avvenuto con successo");
                    break;
                }
                case "carica": {
                    File file = new File("src/output.txt");
                    String fileString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

                    List<String> splitItemsString = Arrays.asList(fileString.split("#"));
                    List<Material> newList = new ArrayList<>();
                    List<String> itemInfos = new ArrayList<>(splitItemsString);
                    catalogo.clear();

                    for (String itemInfo : itemInfos) {
                        String[] splittedItem = itemInfo.split("@");

                        if (splittedItem.length == 6)
                            catalogo.add(new Book(splittedItem[0], splittedItem[1], splittedItem[2],
                                    Integer.parseInt(splittedItem[3]), Integer.parseInt(splittedItem[4]),
                                    Integer.parseInt(splittedItem[5])));
                        else
                            catalogo.add(new Magazine(splittedItem[0], Integer.parseInt(splittedItem[1]),
                                    Integer.parseInt(splittedItem[2]), Periodo.valueOf(splittedItem[3]),
                                    Integer.parseInt(splittedItem[4])));
                    }
                    System.out.println("caricatmento eseguito con successo");
                    System.out.println(catalogo);
                    break;
                }
                default: {
                    System.out.println("eh ? cosa ? ");
                }
            }
        } catch (NumberFormatException | InputMismatchException ex) {
            System.err.println("input non valido");
        } catch (IOException ex) {
            System.err.println("errore in I/O");
        } catch (Exception ex) {
            System.err.println("errore generico");
        }

    }

    public static void primoCaricamento() {
        try {
            File file = new File("src/output.txt");
            String fileString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

            List<String> splitItemsString = Arrays.asList(fileString.split("#"));
            List<Material> newList = new ArrayList<>();
            List<String> itemInfos = new ArrayList<>(splitItemsString);
            catalogo.clear();

            for (String itemInfo : itemInfos) {
                String[] splittedItem = itemInfo.split("@");

                if (splittedItem.length == 6)
                    catalogo.add(new Book(splittedItem[0], splittedItem[1], splittedItem[2],
                            Integer.parseInt(splittedItem[3]), Integer.parseInt(splittedItem[4]),
                            Integer.parseInt(splittedItem[5])));
                else
                    catalogo.add(new Magazine(splittedItem[0], Integer.parseInt(splittedItem[1]),
                            Integer.parseInt(splittedItem[2]), Periodo.valueOf(splittedItem[3]),
                            Integer.parseInt(splittedItem[4])));
            }
            System.out.println("caricamento eseguito con successo");
        } catch (IOException ex) {
            System.err.println("errore in I/O");
        }
    }


}






