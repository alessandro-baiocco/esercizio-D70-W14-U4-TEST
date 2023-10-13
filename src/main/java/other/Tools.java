package other;

import catalog.Material;
import com.github.javafaker.Faker;
import enums.Periodo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Tools {
    public static List<Material> catalogo = new ArrayList<>();
    public static Periodo[] rndPerdiodo = Periodo.values();
    public static Faker faker = new Faker();
    public static Random rnd = new Random();
    public static Scanner input = new Scanner(System.in);
}
