package main;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public boolean attack(Monster target) {
        System.out.println(name + " hyökkää " + target.getType() + " hirviöön!");
        return target.takeDamage(10);
    }

    public String getName() {
        return name;
    }
}
