package org.ts.spring21.model;


import java.io.Serializable;

public class SpaceShip implements Serializable {

    private String name;

    public SpaceShip() {
    }

    public SpaceShip(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SpaceShip{" +
                "name='" + name + '\'' +
                '}';
    }
}
