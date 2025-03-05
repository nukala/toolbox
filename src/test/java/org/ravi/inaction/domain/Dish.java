package org.ravi.inaction.domain;

import java.util.Arrays;
import java.util.List;

// from Chapter4 of Java8 in action book.
public class Dish {
    public static final Dish EMPTY = new Dish("EMPTY", false, -1, DishType.OTHER);
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final DishType type;

    public Dish(String name, boolean vegetarian, int calories, DishType type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public static List<Dish> menu() {
        return Arrays.asList(
                new Dish("nuggets", false, 110, DishType.MEAT),
                new Dish("biriyani", true, 280, DishType.OTHER),
                new Dish("roti", true, 80, DishType.OTHER),

                new Dish("pork", false, 800, DishType.MEAT),
                new Dish("beef", false, 700, DishType.MEAT),
                new Dish("chicken", false, 400, DishType.MEAT),
                new Dish("french fries", true, 530, DishType.OTHER),
                new Dish("rice", true, 150, DishType.OTHER),
                new Dish("season fruit", true, 120, DishType.OTHER),
                new Dish("pizza", true, 550, DishType.OTHER),
                new Dish("prawns", false, 300, DishType.FISH),
                new Dish("salmon", false, 450, DishType.FISH));
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public DishType getDishType() {
        return type;
    }

    @Override
    public String toString() {
        return name + "." + getCalories();
    }

    public enum DishType {MEAT, FISH, OTHER, UNKNOWN;}
}
