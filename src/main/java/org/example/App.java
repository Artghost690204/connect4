package     org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // Játékos nevének bekérése
        Scanner scanner = new Scanner(System.in);
        System.out.print("Adja meg a játékos nevét: ");
        String playerName = scanner.nextLine();

        // Player objektum létrehozása
        Player player = new Player(playerName);
        System.out.println("Játékos: " + player);

        // Játékállás betöltése egy fájlból (kezdetleges példa)
        File file = new File("game_state.txt");
        if (file.exists()) {
            System.out.println("Játékállás betöltése...");
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    System.out.println(fileScanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                System.err.println("Nem található a fájl: " + file.getAbsolutePath());
            }
        } else {
            System.out.println("Üres pályáról indul a játék.");
        }

        // Egyszerű interakciók a játék során
        System.out.println("Játék elindult!");
    }
}