package org.ravi.inaction.domain;

import java.util.Arrays;
import java.util.List;

public class Trader {
    public static Trader raoul = new Trader("Raoul", "Cambridge");
    public static Trader mario = new Trader("Mario", "Milan");
    public static Trader alan = new Trader("Alan", "Cambridge");
    public static Trader brian = new Trader("Brian", "Cambridge");
    public static Trader brown = new Trader("Jerry", "Sacramento");

    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public static List<Trader> traders() {
        return Arrays.asList(raoul, mario, alan, brian, brown);
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}
