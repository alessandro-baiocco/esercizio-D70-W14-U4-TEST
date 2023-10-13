package coso;

import catalog.Book;
import catalog.Magazine;
import catalog.Material;
import com.github.javafaker.Faker;
import enums.Periodo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {
        Faker faker = new Faker();
        Random rnd = new Random();


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


    }
}
