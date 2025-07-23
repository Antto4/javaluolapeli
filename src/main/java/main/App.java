package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        Cave cave = null;

        System.out.println("Syötä pelaajan nimi: ");
        String name = scanner.nextLine();
        Player player = new Player(name);
        cave = new Cave(player);
        
        while (true) {
            System.out.println("1) Lisää luolaan hirviö");
            System.out.println("2) Listaa hirviöt");
            System.out.println("3) Hyökkää hirviöön");
            System.out.println("4) Tallenna peli");
            System.out.println("5) Lataa peli");
            System.out.println("0) Lopeta peli");

            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                 System.out.println("Peli päättyy. Kiitos pelaamisesta!");
                 break;
            }

            switch (choice) {

                case 1: 
                    System.out.println("Anna hirviön tyyppi: ");
                    String type = scanner.nextLine();
                    System.out.println("Anna hirviön elämän määrä numerona: ");
                    Integer health = Integer.parseInt(scanner.nextLine());
                    cave.addMonster(new Monster(type, health));
                    break;

                case 2:
                    cave.listMonsters();
                    break;

                case 3:
                   if (!cave.getMonsters().isEmpty()) {
                        System.out.println("Valitse hirviö, johon hyökätä:");
                        for (int i = 0; i < cave.getMonsters().size(); i++) {
                            Monster m = cave.getMonsters().get(i);
                            System.out.println((i + 1) + ": " + m.getType() + " / " + m.getHealth() + "HP");
                       }
                        int index = Integer.parseInt(scanner.nextLine()) - 1;
                        if (index >= 0 && index < cave.getMonsters().size()) {
                            Monster m = cave.getMonsters().get(index);
                            if (!cave.player.attack(m)) {
                                cave.getMonsters().remove(index);
                            }
                        } else {
                            System.out.println("Virheellinen valinta.");
                        }
                    } else {
                        System.out.println("Luola on tyhjä.");
                    }
                    break;

                case 4:
                    System.out.println("Anna tiedoston nimi, johon peli tallentaa: ");
                    String saveFile = scanner.nextLine();
                    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveFile))) {
                        out.writeObject(cave);
                        System.out.println("Peli tallennettiin tiedostoon " + saveFile + ".");
                    } catch (IOException e) {
                        System.out.println("Virhe tallennuksessa: " + e.getMessage());
                    }
                    break;


                case 5:
                    System.out.println("Anna tiedoston nimi, josta peli ladataan:");
                    String loadFile = scanner.nextLine();
                    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(loadFile))) {
                        cave = (Cave) in.readObject();
                        System.out.println("Peli ladattu tiedostosta " + loadFile + ". Tervetuloa takaisin, " + cave.player.getName() + ".");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Virhe latauksessa: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Tuntematon valinta.");
            }
        }
        scanner.close();
    }

}