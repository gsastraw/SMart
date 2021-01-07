package se.gu.smart.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import se.gu.smart.model.project.Project;
import se.gu.smart.model.project.ProjectMember;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.SelectedProject;
import se.gu.smart.repository.SelectedUser;

public class ViewProjectController extends BaseUserController{
    private AccountRepository accountRepository = Repositories.getAccountRepository();
    private SelectedProject selectedProject = Repositories.getSelectedProject();
    private SelectedUser selectedUser = Repositories.getSelectedUser();

    @FXML
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
    void backButtonPressed(MouseEvent event){
        redirect(event, "user_dashboard");
    }

    @FXML
    public void initialize(){
        ObservableSet<ProjectMember> projectMembers = FXCollections.observableSet(selectedProject.getProject().get().getMembers());
        memberListView.setItems(FXCollections.observableArrayList(projectMembers));
        memberListView.setEditable(true);

        this.project = selectedProject.getProject().get();
        loadData();
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

}
