package org.ravi.udemy.dsa;

import lombok.Getter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//https://www.byte-by-byte.com/01knapsack
// 50-Coding-Interview-Questions-bytebybyte.pdf
public class Knapsack {
    private static boolean useNaive = true;

    public static int knapsack(Item[] items, int maxWeight) {
        if (useNaive)
            return naiveKnapsack(items, maxWeight);

        throw new IllegalStateException("TODO implement non-naive knapsack");
    }

    // Recursively check every combination of items by traversing list of items
    // and either including or excluding each item
    public static int naiveKnapsack(Item[] items, int maxWeight) {
        return naiveKnapsack(items, maxWeight, 0);
    }

    // Overloaded recursive function for naiveKnapsack
    private static int naiveKnapsack(Item[] items, int maxWeight, int curr) {
        // Return when we reach the end of the list
        if (curr == items.length)
            return 0;

        // If item is heavier than remaining weight, skip item
        if (maxWeight - items[curr].weight < 0) { // exceeded, cant include item so no max business
            return naiveKnapsack(items, maxWeight, curr + 1);
        }

        // Try both including and excluding the current item
        int withItem = naiveKnapsack(items, maxWeight - items[curr].weight, curr + 1)
                + items[curr].value;
        int ignoreItem = naiveKnapsack(items, maxWeight, curr + 1);
        return Math.max(withItem, ignoreItem);
    }

    @WorthLooking("knapsackHelper: with/out item, pay attention to array size and loop-indices (includes all/none elements)")
    private static int[][] knapsackHelper(Item[] items, int capacity) {
        int[][] matrix = new int[items.length + 1][capacity + 1];

        for (int i = 1; i <= items.length; i++) { // for all items
            for (int j = 0; j <= capacity; j++) { // for all capacities
                Item lastItem = items[i - 1]; // last item checked
                if (lastItem.weight > j) { // exceeded, cant include item[i]
                    matrix[i][j] = matrix[i - 1][j];
                } else {
                    int withItem = matrix[i - 1][j - lastItem.weight] + lastItem.value;
                    int skipped = matrix[i - 1][j];
                    matrix[i][j] = Math.max(withItem, skipped);
                }
            }
        }

        return matrix;
    }

    public static int knapsackDp(Item[] items, int capacity) {
        int[][] matrix = knapsackHelper(items, capacity);

        return matrix[items.length][capacity];
    }

    // medium.com/@ssaurel/solving-the-knapsack-problem-in-java-c985c71a7e64
    @WorthLooking("knapsackItemsDp: start at last value and go back-ward")
    public static List<Item> knapsackItemsDp(Item[] items, int capacity) {
        int[][] matrix = knapsackHelper(items, capacity);

        int remainingValue = matrix[items.length][capacity];
        int remainingCapacity = capacity;
        List<Item> solution = new ArrayList<>();
        for (int i = items.length; i > 0 && remainingCapacity > 0; i--) { // starting with most value!
            if (remainingValue != matrix[i - 1][remainingCapacity]) { // carrying item
                Item item = items[i - 1];
                solution.add(item);
                remainingValue -= item.getValue();
                remainingCapacity -= item.getWeight();
            }
        }

        return solution;
    }

    @Test
    public void problemInPdf() {
        Item[] items = new Item[]{
                new Item("medium", 2, 10),
                new Item("large", 3, 12),
                new Item("small", 1, 6),
        };

        int maxWeight = 5;
        int expectedValue = 22;
        System.out.printf("maxWeight=%d, expectedValue = %d %n", maxWeight, expectedValue);
        assertThat(knapsack(items, maxWeight))
                .as("naive recursive solution")
                .isEqualTo(expectedValue);
        assertThat(knapsackDp(items, maxWeight))
                .as("dynamic programming")
                .isEqualTo(expectedValue);
        List<Item> carryingItems = knapsackItemsDp(items, maxWeight);
        for (Item item : carryingItems) {
            System.out.printf("Item[%s] weight=%d value=%d %n", item.getName(), item.weight, item.getValue());
        }
    }

    // Item class
    @Getter
    static class Item {
        private int weight;
        private int value;
        private String name;

        public Item(String name, int weight, int value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }
    }
}
