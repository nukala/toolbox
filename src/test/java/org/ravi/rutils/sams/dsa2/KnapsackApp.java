package org.ravi.rutils.sams.dsa2;

import java.util.ArrayList;
import java.util.List;


// https://medium.com/@ssaurel/solving-the-knapsack-problem-in-java-c985c71a7e64
class Item {
    public String name;
    public int value;
    public int weight;

    public Item(String name, int value, int weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    public String str() {
        return name + " [value = " + value + ", weight = " + weight + "]";
    }
}

class Solution {
    // list of items to put in the bag to have the maximal value
    public List<Item> items;
    // maximal value possible
    public int value;

    public Solution(List<Item> items, int value) {
        this.items = items;
        this.value = value;
    }

    public void display() {
        if (items != null && !items.isEmpty()) {
            System.out.println("\nKnapsack solution");
            System.out.println("Value = " + value);
            System.out.println("Items to pick :");

            for (Item item : items) {
                System.out.println("- " + item.str());
            }
        }
    }

}

class Knapsack {

    // items of our problem
    private Item[] items;
    // capacity of the bag
    private int capacity;

    public Knapsack(Item[] items, int capacity) {
        this.items = items;
        this.capacity = capacity;
    }

    public void display() {
        if (items != null && items.length > 0) {
            System.out.println("Knapsack problem");
            System.out.println("Capacity : " + capacity);
            System.out.println("Items :");

            for (Item item : items) {
                System.out.println("- " + item.str());
            }
        }
    }

    // we write the solve algorithm
    Solution solve() {
        int NB_ITEMS = items.length;
        // we use a matrix to store the max value at each n-th item
        int[][] matrix = new int[NB_ITEMS + 1][capacity + 1];

        // first line is initialized to 0
        for (int i = 0; i <= capacity; i++)
            matrix[0][i] = 0;

        // we iterate on items
        for (int i = 1; i <= NB_ITEMS; i++) {
            // we iterate on each capacity
            for (int j = 0; j <= capacity; j++) {
                if (items[i - 1].weight > j)
                    matrix[i][j] = matrix[i - 1][j];
                else
                    // we maximize value at this rank in the matrix
                    matrix[i][j] = Math.max(matrix[i - 1][j],
                            matrix[i - 1][j - items[i - 1].weight] + items[i - 1].value
                    );
            }
        }

        int res = matrix[NB_ITEMS][capacity];
        int w = capacity;
        List<Item> itemsSolution = new ArrayList<>();

        for (int i = NB_ITEMS; i > 0 && res > 0; i--) {
            if (res != matrix[i - 1][w]) {
                itemsSolution.add(items[i - 1]);
                // we remove items value and weight
                res -= items[i - 1].value;
                w -= items[i - 1].weight;
            }
        }

        return new Solution(itemsSolution, matrix[NB_ITEMS][capacity]);
    }
}

public class KnapsackApp {
    public static void main(String[] args) {
        // we take the same instance of the problem displayed in the image
        Item[] items = {
                new Item("Elt1", 4, 12),
                new Item("Elt2", 2, 1),
                new Item("Elt3", 2, 2),
                new Item("Elt4", 1, 1),
                new Item("Elt5", 10, 4),

                new Item("Car", 10, 20),
                new Item("Anvil", 20, 30),
                new Item("bar", 51, 10),
        };

        int maxCapacity = 8;
        Knapsack knapsack = new Knapsack(items, maxCapacity);
        knapsack.display();
        Solution solution = knapsack.solve();
        solution.display();
    }
}

