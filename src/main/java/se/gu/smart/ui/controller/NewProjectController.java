package se.gu.smart.ui.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.model.account.Account;
import se.gu.smart.model.account.Account.AccountType;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.security.session.SessionManager;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static javafx.beans.binding.Bindings.createBooleanBinding;


public class NewProjectController extends BaseUserController {

    private final SessionManager sessionManager = SessionManager.getInstance();
    private final AccountRepository accountRepository = Repositories.getAccountRepository();
    private Account user;

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
    private Button createProjectButton;

    @FXML
    private Text topbarDisplaynameText;

    @FXML
    private Text sidebarUsernameText;

    @FXML
    public void initialize(){

        createProjectButton.disableProperty().bind(createBooleanBinding(
                () -> projectNameField.getText().trim().isEmpty()
                        || projectDescriptionField.getText().trim().isEmpty()
                        || projectStartDate.getValue()==null
                        || projectEndDate.getValue()==null,
                projectNameField.textProperty(),
                projectDescriptionField.textProperty(),
                projectStartDate.valueProperty(),
                projectEndDate.valueProperty()
        ));

        final var activeSession = sessionManager.getActiveSession();

        if (activeSession.isEmpty()) {
            throw new SessionNotFoundException();
        }

        user = accountRepository.getAccount(activeSession.get().getAccountId()).get();

        final var accounts = FXCollections.observableSet(accountRepository.getAll().stream()
            .filter(account -> account.getAccountType() == AccountType.USER)
            .filter(account -> !account.getAccountId().equals(user.getAccountId()))
            .collect(Collectors.toSet()));

        membersToAddView.setItems(FXCollections.observableArrayList(accounts));
        membersToAddView.setEditable(true);

        topbarDisplaynameText.setText(String.valueOf(user.getDisplayName()));
        sidebarUsernameText.setText(String.valueOf(user.getUsername()));
    }

    @FXML
    void onDoneClicked(MouseEvent event){
        final var projectRep = Repositories.getProjectRepository();
        final var project = projectRep.createProject(user.getAccountId(), projectNameField.getText(), projectDescriptionField.getText(), projectStartDate.getValue() ,projectEndDate.getValue());

        for (final var user : membersAdded){
            project.addMember(user);
        }

        redirectDashboard(event);
    }

    @FXML
    void onAddClicked(MouseEvent event) {
        final var selectedAccount = membersToAddView.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) {
            membersAdded.add(selectedAccount);
            membersAddedView.setItems(FXCollections.observableArrayList(membersAdded));
            membersAddedView.refresh();
        } else {
            System.out.println("No account selected");
            return;
        }
    }

    @FXML
    void onRemoveClicked(MouseEvent event) {
        final var selectedAccount = membersAddedView.getSelectionModel().getSelectedItem();
        membersAdded.remove(selectedAccount);
        membersAddedView.setItems(FXCollections.observableArrayList(membersAdded));

        membersAddedView.refresh();
    }
}
