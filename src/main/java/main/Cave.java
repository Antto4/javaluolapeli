package main;

import java.io.Serializable;
import java.util.ArrayList;

public class Cave implements Serializable {
    public Player player;
    private ArrayList<Monster> monsters;

    public Cave(Player player) {
        this.player = player;
        this.monsters = new ArrayList<>();
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);

    }

    public void listMonsters() {
        if (monsters.isEmpty()) {
            System.out.println("Luola on tyhjä.");
        } else {
            System.out.println("Luolan hirviöt:");
            for (int i = 0; i < monsters.size(); i++) {
                monsters.get(i).printInfo(i + 1);
            }
        }
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

}