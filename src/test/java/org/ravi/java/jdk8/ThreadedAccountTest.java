package org.ravi.java.jdk8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.data.Offset;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * To test some multi-threaded synchronization code
 * <br/>
 * Shows that manytimes we cannot predict when a thread would run, hence balance is not 
 * always close to ZERO
 */
// dzone article.
public class ThreadedAccountTest {
    private static final ExecutorService SERVICE = Executors.newFixedThreadPool(25);

    @Test
    public void testBankAccountWithoutSynchronization() {
        BankAccount bankAccount = new BankAccount();

        IntStream.rangeClosed(1, 100).forEach(i -> {
            SERVICE.submit(() -> bankAccount.debit(100));

            SERVICE.submit(() -> bankAccount.credit(100));
        });

        SERVICE.shutdown();
        System.out.println("Final Balance: " + bankAccount.getBalance());
        assertThat(bankAccount.getBalance())
                .as("After all those loops inside an executor")
                .isEqualTo(0, Offset.offset(0.5));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class BankAccount {
        double balance;

        public void credit(double amount) {
            balance = balance + amount;
        }

        public void debit(double amount) {
            balance = balance - amount;
        }
    }
}
