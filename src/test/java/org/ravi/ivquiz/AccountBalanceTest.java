package org.ravi.ivquiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;

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
        List<AccountBalance> accountBalances = new ArrayList<>();
        List<Bill> bills = DataService.getBills();

        Map<String, Integer> balanceMap = mapOfBills(bills);

        Predicate<String> hasBalance = (id) -> balanceMap.containsKey(id) && balanceMap.get(id) != 0;

        for (Account account : DataService.getAccounts()) {
            if (hasBalance.test(account.getId())) {
                accountBalances.add(new AccountBalance(account.getId(), balanceMap.get(account.getId())));
            }
        }

        return accountBalances;
    }

    public static List<Bill> getDueBills(String accountId) {
        // list all bills still due for an account
        return Collections.emptyList();
    }

    @Test
    public void usingDataService() {
        for (Bill b : DataService.getBills()) {
            System.out.println(b);
        }
        for (Payment p : DataService.getPayments()) {
            System.out.println(p);
        }
        for (Account a : DataService.getAccounts()) {
            System.out.println(a);
        }

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

    @Data
    static class Account {
        private final String id;

        public Account(String id) {
            this.id = id;
        }
    }

    @Data
    @AllArgsConstructor
    static class Bill {
        private String id;
        private int amount;
        private String accountId;
    }

    @Data
    @AllArgsConstructor
    static class Payment {
        private String id;
        private int amount;
        private String accountId;
    }

    @Data
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

    @Data
    @AllArgsConstructor
    static class AccountBalance {
        private String accountId;
        private int balance;
    }
}
