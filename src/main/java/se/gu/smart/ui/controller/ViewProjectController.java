package se.gu.smart.ui.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.model.account.Account;
import se.gu.smart.model.project.Project;
import se.gu.smart.model.project.ProjectMember;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.SelectedProject;
import se.gu.smart.repository.SelectedUser;
import se.gu.smart.security.session.SessionManager;

import java.util.Optional;

public class ViewProjectController extends BaseUserController{

    private final SessionManager sessionManager = SessionManager.getInstance();
    private final AccountRepository accountRepository = Repositories.getAccountRepository();
    private final SelectedProject selectedProject = Repositories.getSelectedProject();
    private final SelectedUser selectedUser = Repositories.getSelectedUser();

    private Project project;

    @FXML
    private Text projectName;
    @FXML
    private Text projectDescription;
    @FXML
    private TextField startDate;
    @FXML
    private TextField endDate;
    @FXML
    private ListView<ProjectMember> memberListView;
    @FXML
    private Button editProjectButton;
    @FXML
    private Text topbarDisplaynameText;
    @FXML
    private Text sidebarUsernameText;
    @FXML
    private Optional<Account> account;


    @FXML
    void backButtonPressed(MouseEvent event){
        redirect(event, "user_dashboard");
    }

    @FXML
    public void initialize(){
        super.initialize();

        final var activeSession = sessionManager.getActiveSession();

        if (activeSession.isEmpty()) {
            throw new SessionNotFoundException();
        }

        this.project = selectedProject.getProject().get();
        loadData();

        if (!project.getOwnerId().equals(activeSession.get().getAccountId())) {
            editProjectButton.disableProperty().setValue(true);
        }

        final var projectMembers = FXCollections.observableSet(project.getMembers());
        memberListView.setItems(FXCollections.observableArrayList(projectMembers));
        memberListView.setEditable(true);
        memberListView.setCellFactory(new ProjectMemberCellFactory(project, accountRepository));
    }

    @FXML
    void loadData(){
        projectName.setText(String.valueOf(project.getTitle()));
        projectDescription.setText(String.valueOf(project.getDescription()));
        startDate.setText(String.valueOf(project.getStartDate()));
        endDate.setText(String.valueOf(project.getDeadline()));
    }


    public void setUser(){
        selectedUser.clearUser();

        final var selectedItem = memberListView.getSelectionModel().getSelectedItems().stream().findAny();

        if (selectedItem.isPresent()) {
            final var selectedRows = accountRepository.getAccount(selectedItem.get().getAccountId());
            selectedUser.setUser(selectedRows.orElseThrow());
        }
    }

    @FXML
    void viewProfilePressed(MouseEvent event){
        setUser();
        if (selectedUser.getUser().isPresent()){
            redirect(event, "user_view_member");
            System.out.println(selectedUser.getUser());
        }
    }

    @FXML
    void redirectIssues(MouseEvent event){
        redirect(event, "user_issues");
    }

    @FXML
    void redirectEditProject(MouseEvent event){
        redirect(event, "user_edit_project");
    }

    private static class ProjectMemberCellFactory implements Callback<ListView<ProjectMember>, ListCell<ProjectMember>> {

        private final Project project;
        private final AccountRepository accountRepository;

        private ProjectMemberCellFactory(Project project, AccountRepository accountRepository) {
            this.project = project;
            this.accountRepository = accountRepository;
        }

        @Override
        public ListCell<ProjectMember> call(ListView<ProjectMember> param) {
            return new ListCell<>() {
                @Override
                protected void updateItem(ProjectMember projectMember, boolean b) {
                    super.updateItem(projectMember, b);
                    if (projectMember != null) {
                        final var account = accountRepository.getAccount(projectMember.getAccountId()).orElseThrow();
                        final var isOwner = project.getOwnerId().equals(account.getAccountId());
                        setText((isOwner ? "[OWNER]" : "[MEMBER]") + " " + account.getUsername());
                    } else {
                        setText("");
                    }
                }
            };
        }
    }
}
