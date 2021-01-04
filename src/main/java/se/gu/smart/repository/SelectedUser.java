package se.gu.smart.repository;

import se.gu.smart.model.account.Account;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SelectedUser {
    private Set<Account> account = new HashSet<>();

    public void setAccount(Account user){
        account.add(user);
    }

    public void eraseAccount(){
        account.clear();
    }

    public Account getAccount(){
        return account.stream().findAny().get();
    }
}
