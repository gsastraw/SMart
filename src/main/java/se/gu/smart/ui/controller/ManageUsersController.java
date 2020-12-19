package se.gu.smart.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import se.gu.smart.model.account.Account;

import java.time.LocalDate;
import java.util.UUID;

public class ManageUsersController extends BaseAdminController{

    @FXML
    private TableView<Account> accountTableView;
    @FXML
    private TableColumn<Account, UUID> idColumn;
    @FXML
    private TableColumn<Account, String> displayNameColumn;
    @FXML
    private TableColumn<Account, String> usernameColumn;
    @FXML
    private TableColumn<Account, LocalDate> lastLoggedColumn;

    @FXML
    public void initialize(){
        super.initialize();

        idColumn.setCellValueFactory(new PropertyValueFactory<Account, UUID>("accountId"));
        displayNameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("displayName"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
        //lastLoggedColumn.setCellValueFactory(new PropertyValueFactory<Account, LocalDate>("Last Logged In"));

        accountTableView.setItems(getAccounts());
        accountTableView.setEditable(true);
        displayNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    //this just creates a bunch of dummy accounts for the time being, for testing purposes.
    public ObservableList<Account> getAccounts(){
        ObservableList<Account> accounts = FXCollections.observableArrayList();
        accounts.add(new Account(UUID.randomUUID(), Account.AccountType.USER, "jeremy", "Jeremy Jeremysson"));
        accounts.add(new Account(UUID.randomUUID(), Account.AccountType.USER, "jimothy", "Jimothy Farforos III"));
        accounts.add(new Account(UUID.randomUUID(), Account.AccountType.USER, "ed", "ed ed"));

        return accounts;
    }

    public void changeDisplayNameCellEvent(TableColumn.CellEditEvent edittedCell){
        Account accountSelected = accountTableView.getSelectionModel().getSelectedItem();
        accountSelected.setDisplayName(edittedCell.getNewValue().toString());
    }

    public void deleteButtonPushed(){
        ObservableList<Account> selectedRows, allAccounts;
        allAccounts = accountTableView.getItems();

        selectedRows = accountTableView.getSelectionModel().getSelectedItems();

        for (Account account : selectedRows){
            allAccounts.remove(account);
        }
    }
}
