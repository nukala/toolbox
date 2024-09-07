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
 * Shows that many times we cannot predict when a thread would run, hence balance is not
 * always close to ZERO
 */
// DZone article.
public class ThreadedAccountTest {
    private static final ExecutorService SERVICE = Executors.newFixedThreadPool(25);

    @Test
    public void testBankAccountWithoutSynchronization() {
        BankAccount bankAccount = new BankAccount();

        final int nanos = 2;
        IntStream.rangeClosed(1, 100).forEach(i -> {
            SERVICE.submit(() -> bankAccount.debit(100));
            // seems to help!
            if (nanos > 0) {
                nanoSleep(5);
            }
            SERVICE.submit(() -> bankAccount.credit(100));
        });

        SERVICE.shutdown();
        System.out.println("Final Balance: " + bankAccount.getBalance());
	// assert Double comparison with offset
        assertThat(bankAccount.getBalance())
                .as("After all those loops inside an executor")
                .as("sometimes -- running many tests or mvn in verbose mode also fails. ")
                .as("\\mvn -V -fae -T1 install -- may work")
                .isEqualTo(0, Offset.offset(1.5));
    }

    private static void nanoSleep(int nanos) {
        if (nanos <= 0) {
            return;
        }
        try {
            Thread.sleep(0, nanos);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class BankAccount {
        double balance;

        // RNTODO: boolean controlled lock
        public void credit(double amount) {
            balance = balance + amount;
        }

        public void debit(double amount) {
            balance = balance - amount;
        }
    }
}
