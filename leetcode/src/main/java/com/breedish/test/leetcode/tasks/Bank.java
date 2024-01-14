package com.breedish.test.leetcode.tasks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.SECONDS;

class Bank {

    private final Account[] accounts;

    public Bank(long[] balance) {
        this.accounts = new Account[balance.length];
        for (int i = 0; i < balance.length; i++) {
            accounts[i] = new Account(balance[i]);
        }
    }

    public boolean transfer(int account1, int account2, long money) {
        if (!validateAccount(account1) || !(validateAccount(account2)) || money < 0) return false;

        var acc1 = getAccount(account1);
        var acc2 = getAccount(account2);

        try {
            if (acc1.lock.tryLock(1, SECONDS)) {
                try {
                    if (acc2.lock.tryLock(1, SECONDS)) {
                        try {
                            if (acc1.withdraw(money)) {
                                acc2.deposit(money);
                            } else {
                                return false;
                            }
                        } finally {
                            acc2.lock.unlock();
                        }
                    }
                } finally {
                    acc1.lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            return false;
        }

        return true;
    }

    public boolean deposit(int account, long money) {
        if (!validateAccount(account)) return false;
        return getAccount(account).deposit(money);
    }


    public boolean withdraw(int account, long money) {
        if (!validateAccount(account)) return false;
        return getAccount(account).withdraw(money);
    }

    private boolean validateAccount(int account) {
        return account >= 0 && account <= accounts.length;
    }

    private Account getAccount(int account) {
        var acc = accounts[account - 1];
        if (acc == null)
            throw new IllegalStateException("invalid account definition " + account + " " + accounts.length);

        return acc;
    }

    private static final class Account {
        private Long balance;
        private final Lock lock = new ReentrantLock(true);

        public Account(Long balance) {
            this.balance = balance;
        }

        public boolean deposit(long amount) {
            try {
                if (lock.tryLock(1, SECONDS)) {
                    try {
                        balance += amount;
                    } finally {
                        lock.unlock();
                    }
                    return true;
                } else {
                    return false;
                }
            } catch (InterruptedException e) {
                return false;
            }
        }

        public boolean withdraw(long amount) {
            try {
                if (lock.tryLock(1, SECONDS)) {
                    try {
                        if (balance < amount) return false;
                        balance -= amount;
                    } finally {
                        lock.unlock();
                    }
                    return true;
                } else {
                    return false;
                }
            } catch (InterruptedException e) {
                return false;
            }
        }
    }

}

/**
 * Your Bank object will be instantiated and called as such:
 * Bank obj = new Bank(balance);
 * boolean param_1 = obj.transfer(account1,account2,money);
 * boolean param_2 = obj.deposit(account,money);
 * boolean param_3 = obj.withdraw(account,money);
 */
