package se.gu.smart.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import se.gu.smart.model.account.Account;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.Repositories;

import java.util.Optional;

public class ManageUsersController extends BaseAdminController{

    private AccountRepository accountRepository = Repositories.getUserAccountRepository();

    @FXML
    private ListView<Account> accountTableView;

    @FXML
    public void initialize(){
        super.initialize();

        ObservableSet<Account> accounts = FXCollections.observableSet(accountRepository.getAccounts());
        accountTableView.setItems(FXCollections.observableArrayList(accounts));
        accountTableView.setEditable(true);
    }

    public Optional<Account> getUser(){
        ObservableList<Account> selectedRows;

        selectedRows = accountTableView.getSelectionModel().getSelectedItems();
        for (Account account : selectedRows){
            return accountRepository.getAccount(account.getAccountId());
        }
        return Optional.empty();
    }

    public void deleteButtonPushed(){
        ObservableList<Account> selectedRows, allAccounts;
        allAccounts = accountTableView.getItems();

        selectedRows = accountTableView.getSelectionModel().getSelectedItems();

        for (Account account : selectedRows){
            allAccounts.remove(account);
            accountRepository.removeAccount(account.getAccountId());
        }
    }

    @FXML
    void redirectUseProfileButton(MouseEvent event) {
        redirect(event, "admin_view_profile");
    }

}
