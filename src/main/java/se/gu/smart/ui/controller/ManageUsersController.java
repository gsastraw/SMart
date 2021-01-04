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
import se.gu.smart.repository.SelectedUser;

public class ManageUsersController extends BaseAdminController{

    private AccountRepository accountRepository = Repositories.getUserAccountRepository();
    private SelectedUser selectedUser = Repositories.getSelectedUSer();

    @FXML
    private ListView<Account> accountTableView;

    @FXML
    public void initialize(){
        super.initialize();

        ObservableSet<Account> accounts = FXCollections.observableSet(accountRepository.getAccounts());
        accountTableView.setItems(FXCollections.observableArrayList(accounts));
        accountTableView.setEditable(true);
    }

    public void setUser(){
        selectedUser.eraseAccount();
        ObservableList<Account> selectedRows;

        selectedRows = accountTableView.getSelectionModel().getSelectedItems();
        for (Account account : selectedRows){
            selectedUser.setAccount(accountRepository.getAccount(account.getAccountId()).get());
        }
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
        setUser();
        redirect(event, "admin_view_profile");
        System.out.println(selectedUser.getAccount());
    }

}
