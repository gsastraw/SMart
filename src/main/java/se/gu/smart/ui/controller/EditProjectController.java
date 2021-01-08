package se.gu.smart.ui.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import se.gu.smart.model.account.Account;
import se.gu.smart.model.account.Account.AccountType;
import se.gu.smart.model.project.Project;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.SelectedProject;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EditProjectController extends BaseUserController {

    private SelectedProject selectedProject = Repositories.getSelectedProject();
    private final AccountRepository accountRepository = Repositories.getAccountRepository();

    private Account owner;

    private final Set<Account> membersAdded = new HashSet<>();
    private final Set<Account> membersRemoved = new HashSet<>();

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
    private Project desiredProject;

    @FXML
    public void initialize(){
        super.initialize();

        this.desiredProject = selectedProject.getProject().orElseThrow();

        this.owner = accountRepository.getAccount(desiredProject.getOwnerId()).orElseThrow();

        projectNameField.setText(desiredProject.getTitle());
        projectDescriptionField.setText(desiredProject.getDescription());
        projectStartDate.setValue(desiredProject.getStartDate());
        projectEndDate.setValue(desiredProject.getDeadline());

        final var projectMembers = desiredProject.getMembers().stream()
                .map(projectMember -> accountRepository.getAccount(projectMember.getAccountId()).orElseThrow())
                .filter(account -> !account.getAccountId().equals(desiredProject.getOwnerId()))
                .collect(Collectors.toList());

        membersAdded.addAll(projectMembers);

        final var availableAccounts = getAvailableAccounts(desiredProject);
        availableAccounts.removeAll(projectMembers);

        final var accounts = FXCollections.observableSet(availableAccounts);
        membersToAddView.setItems(FXCollections.observableArrayList(accounts));
        membersToAddView.setEditable(true);

        membersAddedView.setItems(FXCollections.observableArrayList(projectMembers));
    }

    @FXML
    void onDoneClicked(MouseEvent event){
        desiredProject.setTitle(projectNameField.getText());
        desiredProject.setDescription(projectDescriptionField.getText());
        desiredProject.setDeadline(projectEndDate.getValue());
        desiredProject.setStartDate(projectStartDate.getValue());

        membersRemoved.forEach(account -> desiredProject.removeMember(account.getAccountId()));
        membersAdded.forEach(account -> desiredProject.addMember(account));

        redirect(event, "user_view_project");
    }

    @FXML
    void onAddClicked(MouseEvent event) {
        final var selectedAccount = membersToAddView.getSelectionModel().getSelectedItem();
        membersAdded.add(selectedAccount);
        membersRemoved.remove(selectedAccount);
        membersAddedView.setItems(FXCollections.observableArrayList(membersAdded));

        final var availableAccounts = getAvailableAccounts(desiredProject);
        availableAccounts.removeAll(membersAdded);

        final var accounts = FXCollections.observableSet(availableAccounts);
        membersToAddView.setItems(FXCollections.observableArrayList(accounts));

        membersToAddView.refresh();
        membersAddedView.refresh();
    }

    @FXML
    void onRemoveClicked(MouseEvent event) {
        final var selectedAccount = membersAddedView.getSelectionModel().getSelectedItem();
        membersAdded.remove(selectedAccount);
        membersRemoved.add(selectedAccount);
        membersAddedView.setItems(FXCollections.observableArrayList(membersAdded));

        final var projectMembers = new HashSet<>(membersAdded);
        projectMembers.removeAll(membersRemoved);

        final var availableAccounts = getAvailableAccounts(desiredProject);
        availableAccounts.removeAll(projectMembers);

        final var accounts = FXCollections.observableSet(availableAccounts);
        membersToAddView.setItems(FXCollections.observableArrayList(accounts));

        membersToAddView.refresh();
        membersAddedView.refresh();
    }

    private Set<Account> getAvailableAccounts(Project project) {
        return accountRepository.getAccounts().stream()
            .filter(account -> account.getAccountType() == AccountType.USER)
            .filter(account -> !account.getAccountId().equals(project.getOwnerId()))
            .collect(Collectors.toSet());
    }
}
