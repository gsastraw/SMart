package se.gu.smart.ui.controller;

import static javafx.beans.binding.Bindings.createBooleanBinding;

import javafx.beans.Observable;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.DatePicker;
import se.gu.smart.model.project.Project;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import se.gu.smart.repository.ProjectRepository;
import se.gu.smart.security.session.SessionManager;

import java.time.LocalDate;

public class NewProjectController extends BaseUserController {

    private final SessionManager sessionManager = SessionManager.getInstance();
    private final ProjectRepository project = new ProjectRepository();

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
    }

    @FXML
    void onDoneClicked(MouseEvent event){
        System.out.println(project.createProject(projectNameField.getText(), projectDescriptionField.getText(), projectStartDate.getValue() ,projectEndDate.getValue()).toString());

        redirectDashboard(event);

    }

    @FXML
    void onAddMemberClicked(MouseEvent event){
        redirect(event, "user_new_project_add_members");
    }



}
