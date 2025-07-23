package main;

import java.io.Serializable;

public class Monster implements Serializable {
    private String type;
    private int health;


    public Monster(String type, int health) {
        this.type = type;
        this.health = health;
    }

    public void printInfo(int number) {
         System.out.println(number + ": " + type + " / " + health + "HP");
    }

    public int getHealth() {
        return health;
    }

    public boolean takeDamage(int dmg) {
        health -= dmg;
        if (health <= 0) {
            System.out.println(type +" on kuollut!");
            return false;
        } else {
            System.out.println("Hirviöllä on " + health + " elämää jäljellä.");
            return true;
        }
    }

    public String getType() {
        return type;
    }

}