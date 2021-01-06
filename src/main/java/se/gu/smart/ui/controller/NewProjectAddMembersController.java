package se.gu.smart.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.model.account.Account;
import se.gu.smart.model.project.ProjectMember;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.SelectedProject;
import se.gu.smart.repository.SelectedUser;
import se.gu.smart.security.session.SessionManager;
import se.gu.smart.ui.util.FXMLUtil;

import java.util.Optional;

public class NewProjectAddMembersController extends BaseUserController{

    private final SessionManager sessionManager = SessionManager.getInstance();
    private final AccountRepository accountRepository = Repositories.getAccountRepository();
    private SelectedProject selectedProject = Repositories.getSelectedProject();
    private SelectedUser selectedUser = Repositories.getSelectedUser();

    @FXML
    private ListView<ProjectMember> memberListView;

    @FXML
    private ListView<Account> accountListView;

    @FXML
    private Optional<Account> userAccount;

    @FXML
    public void initialize() {

        accountListView.getSelectionModel().selectedItemProperty().addListener((observableValue, account, t1) -> {
            Optional<Account> accounts = accountRepository.getAccount(userAccount.get().getAccountId());
            if (accounts.isPresent()){
                var dashboardParent = FXMLUtil.loadFxml("user_new_project");
                var dashboardScene = new Scene((Parent) dashboardParent);

                var window = (Stage) accountListView.getScene().getWindow();
                window.setScene(dashboardScene);
                window.centerOnScreen();
                window.show();
            }
        });

        ObservableSet<Account> accounts = FXCollections.observableSet(accountRepository.getAccounts());
        accountListView.setItems(FXCollections.observableArrayList(accounts));
        accountListView.setEditable(true);

        final var activeSession = sessionManager.getActiveSession();

        if (activeSession.isEmpty()) {
            throw new SessionNotFoundException();
        }
        userAccount = accountRepository.getAccount(activeSession.get().getAccountId());

            ObservableSet<ProjectMember> projectMembers = FXCollections.observableSet(selectedProject.getProject().get().getMembers());
            memberListView.setItems(FXCollections.observableArrayList(projectMembers));
            memberListView.setEditable(true);
    }

    @FXML
    public Optional<Account> getUser(){
        return userAccount;
    }

    @FXML
    void onBackClicked(MouseEvent event){
        redirect(event, "user_new_project");
    }

}
