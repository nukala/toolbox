package org.ravi.udemy.dsa;

import org.junit.Test;

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
    private static int naiveKnapsack(Item[] items, int maxWeight, int currIndex) {
        // Return when we reach the end of the list
        if (currIndex == items.length)
            return 0;

        // If item is heavier than remaining weight, skip item
        if (maxWeight - items[currIndex].weight < 0) {
            // exceeded
            return naiveKnapsack(items, maxWeight, currIndex + 1);
        }

        // Try both including and excluding the current item
        int withCurrentItem = naiveKnapsack(items, maxWeight - items[currIndex].weight, currIndex + 1) + items[currIndex].value;
        int skipThisItem = naiveKnapsack(items, maxWeight, currIndex + 1);
        return Math.max(withCurrentItem, skipThisItem);
    }

    @Test
    public void problemInPdf() {
        Item[] items = new Item[]{
                new Item(1, 6),
                new Item(2, 10),
                new Item(3, 12),
        };

        int maxWeight = 5;
        int expectedValue = 22;
        System.out.printf("maxWeight=%d, expectedValue = %d %n", maxWeight, expectedValue);
        assertThat(knapsack(items, maxWeight))
                .isEqualTo(expectedValue);
    }

    // Item class
    static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
