package se.gu.smart.ui.controller;

import static javafx.beans.binding.Bindings.createBooleanBinding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.model.Ticket;
import se.gu.smart.model.account.Account;
import se.gu.smart.model.project.Project;
import se.gu.smart.model.project.ProjectMember;
import se.gu.smart.permission.ProjectPermission;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.ProjectRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.security.session.SessionManager;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


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
    private Optional<Account> userAccount;

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
    private Button createProjectButton;

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

        ObservableSet<Account> accounts = FXCollections.observableSet(accountRepository.getAccounts());
        membersToAddView.setItems(FXCollections.observableArrayList(accounts));
        membersToAddView.setEditable(true);

    }

    @FXML
    void onDoneClicked(MouseEvent event){
        ProjectRepository projectRep = Repositories.getProjectRepository();
        Project project = projectRep.createProject(projectNameField.getText(), projectDescriptionField.getText(), projectStartDate.getValue() ,projectEndDate.getValue());
        project.addMember(user);
        project.addMemberPermission(user.getAccountId(),ProjectPermission.REMOVE_PROJECT, ProjectPermission.VIEW_PROJECT,
                ProjectPermission.EDIT_PROJECT_DETAILS, ProjectPermission.ADD_USER,ProjectPermission.REMOVE_USER,
                ProjectPermission.REMOVE_PROJECT, ProjectPermission.VIEW_PROGRESS, ProjectPermission.CREATE_SCHEDULE,
                ProjectPermission.EDIT_SCHEDULE, ProjectPermission.REMOVE_SCHEDULE);
        System.out.println(project.toString(user));
        redirectDashboard(event);

        for (Account user:membersAdded){
            project.addMember(user);
        }
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
