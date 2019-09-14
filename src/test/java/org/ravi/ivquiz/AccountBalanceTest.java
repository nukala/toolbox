package org.ravi.ivquiz;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

// I was asked this in an interview at a company. coding-test in 20mins or less
//
// Given a bunch of account/bill objects. (account to many bills)
// compute the accountbalance for each of them
//
// interview question did not use Lombok and was very voluminous
@SuppressWarnings({"unused"})
public class AccountBalanceTest {
    private static Map<String, Integer> mapOfBills(List<Bill> bills) {
        Map<String, Integer> map = new HashMap<>();

        for (Bill bill : bills) {
            map.compute(bill.getAccountId(),
                    (key, oldValue) -> (oldValue == null) ? 0 : oldValue
                            + bill.getAmount());
        }

        return map;
    }

//    private static List<Bill> getBillsOfAccount(List<Bill> bills, String accountId) {
//        List<Bill> billsOfAccount = new ArrayList<>();
//        for (Bill bill : bills) {
//            if (bill.getAccountId().equals(accountId)) {
//                billsOfAccount.add(bill);
//            }
//        }
//
//        return billsOfAccount;
//    }

    public static List<AccountBalance> getAccountBalances() {
        List<AccountBalance> accountBalances = new ArrayList<AccountBalance>();
        List<Bill> bills = DataService.getBills();

        Map<String, Integer> balanceMap = mapOfBills(bills);

        for (Account account : DataService.getAccounts()) {
            if (balanceMap.containsKey(account.getId())
                    && balanceMap.get(account.getId()) != 0) {
                accountBalances.add(new AccountBalance(account.getId(), balanceMap.get(account.getId())));
            }
        }


        return accountBalances;
    }

    public static List<Bill> getDueBills(String accountId) {
        // list all bills still due for an account
        return Arrays.asList();
    }

    @Test
    public void usingDataService() {
//        for (Bill b : DataService.getBills()) {
//            System.out.println(b);
//        }
//        for (Payment p : DataService.getPayments()) {
//            System.out.println(p);
//        }
//        for (Account a : DataService.getAccounts()) {
//            System.out.println(a);
//        }

        List<AccountBalance> balances = getAccountBalances();
        for (AccountBalance ab : balances) {
            System.out.println(ab);
            //Expected: AccountBalance{accountId='100', balance=??}
            //Expected: AccountBalance{accountId='200', balance=??}
            //Expected: AccountBalance{accountId='300', balance=??}
        }
        // only 1 element of id=100, balance=275
        assertThat(balances)
                .hasSize(1)
                .contains(new AccountBalance("100", 175))
                .doesNotContain(new AccountBalance("200", 0));

        List<Bill> dueBills = getDueBills("100");
        for (Bill b : dueBills) {
            System.out.println(b);
        }
    }

    @Getter
    @EqualsAndHashCode
    @ToString
    static class Account {
        private String id;

        public Account(String id) {
            this.id = id;
        }
    }

    @Getter
    @EqualsAndHashCode
    @ToString
    static class Bill {
        private String id;
        private int amount;
        private String accountId;

        public Bill(String id, int amount, String accountId) {
            this.id = id;
            this.amount = amount;
            this.accountId = accountId;
        }
    }

    @Getter
    @EqualsAndHashCode
    @ToString
    static class Payment {
        private String id;
        private int amount;
        private String accountId;

        public Payment(String id, int amount, String accountId) {
            this.id = id;
            this.amount = amount;
            this.accountId = accountId;
        }
    }

    static class DataService {
        private static List<Bill> bills = Arrays.asList(
                new Bill("1", 100, "100"),
                new Bill("2", 150, "100"),
                new Bill("3", 50, "200"),
                new Bill("5", 25, "100")
        );

        private static List<Payment> payments = Arrays.asList(
                new Payment("a", 105, "100"),
                new Payment("b", 150, "100"),
                new Payment("c", 60, "200")
        );

        private static List<Account> accounts = Arrays.asList(
                new Account("100"),
                new Account("200"),
                new Account("300")
        );

        public static List<Bill> getBills() {
            return bills;
        }

        public static List<Payment> getPayments() {
            return payments;
        }

        public static List<Account> getAccounts() {
            return accounts;
        }
    }

    @Getter
    @EqualsAndHashCode
    @ToString
    static class AccountBalance {
        String accountId;
        int balance;

        public AccountBalance(String accountId, int balance) {
            this.accountId = accountId;
            this.balance = balance;
        }
    }
}
