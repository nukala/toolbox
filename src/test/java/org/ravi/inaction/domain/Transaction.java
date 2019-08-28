package org.ravi.inaction.domain;

import java.util.Arrays;
import java.util.List;

public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public static List<Transaction> transactions() {
        return Arrays.asList(
                new Transaction(Trader.brian, 2011, 300),
                new Transaction(Trader.raoul, 2012, 1000),
                new Transaction(Trader.raoul, 2011, 400),
                new Transaction(Trader.mario, 2012, 710),
                new Transaction(Trader.mario, 2012, 700),
                new Transaction(Trader.alan, 2012, 950),
                new Transaction(Trader.brown, 2018, 1250)
        );
    }

    public Trader getTrader() {
        return this.trader;
    }

    public int getYear() {
        return this.year;
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return "{" + this.trader + ", " +
                "year: " + this.year + ", " +
                "value:" + this.value + "}";
    }
}
