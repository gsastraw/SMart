package se.gu.smart.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import se.gu.smart.model.account.Account;
import se.gu.smart.model.project.Project;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.SelectedProject;

import java.util.HashSet;
import java.util.Set;

public class EditProjectController extends BaseUserController {

    private SelectedProject selectedProject = Repositories.getSelectedProject();
    private final AccountRepository accountRepository = Repositories.getAccountRepository();

    private final Set<Account> membersAdded = new HashSet<>();

    @FXML
    private ListView<Account> membersToAddView;

    @FXML
    private ListView<Account> membersAddedView;

    @FXML
    private TextField projectNameField;

    @FXML
    private TextField projectDescriptionField;

    @FXML
    private DatePicker projectStartDate;

    @FXML
    private DatePicker projectEndDate;

    @FXML
    private Button addMemberButton;

    @FXML
    private Button doneButton;

    @FXML
    private Project desiredProject;

    @FXML
    public void initialize(){

        this.desiredProject = selectedProject.getProject().get();

           projectNameField.setText(desiredProject.getTitle());
           projectDescriptionField.setText(desiredProject.getDescription());
           projectStartDate.setValue(desiredProject.getStartDate());
           projectEndDate.setValue(desiredProject.getDeadline());

        ObservableSet<Account> accounts = FXCollections.observableSet(accountRepository.getAccounts());
        membersToAddView.setItems(FXCollections.observableArrayList(accounts));
        membersToAddView.setEditable(true);
    }

    @FXML
    void onDoneClicked(MouseEvent event){
        desiredProject.setTitle(projectNameField.getText().toString());
        desiredProject.setDescription(projectDescriptionField.getText().toString());
        desiredProject.setDeadline(projectEndDate.getValue());
        desiredProject.setStartDate(projectStartDate.getValue());

        for (Account user:membersAdded){
            desiredProject.addMember(user);
        }

        redirect(event, "user_view_project");
    }

    @FXML
    void onAddClicked(MouseEvent event) {

        Account selectedAccount = membersToAddView.getSelectionModel().getSelectedItem();
        membersAdded.add(selectedAccount);
        membersAddedView.setItems(FXCollections.observableArrayList(membersAdded));
        membersAddedView.refresh();
    }

    @FXML
    void onRemoveClicked(MouseEvent event) {

        Account selectedAccount = membersAddedView.getSelectionModel().getSelectedItem();
        membersAdded.remove(selectedAccount);
        membersAddedView.setItems(FXCollections.observableArrayList(membersAdded));

        membersAddedView.refresh();
    }
}
