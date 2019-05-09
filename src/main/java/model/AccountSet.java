package model;

import java.util.Set;

public class AccountSet {
    private Set<Account> accounts;

    public AccountSet() {}

    public AccountSet(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
