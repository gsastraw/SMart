package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.model.account.Account;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.security.session.SessionManager;

import java.util.Optional;

public class NewProjectAddMembersController extends BaseUserController{

    private final SessionManager sessionManager = SessionManager.getInstance();
    private final AccountRepository accountRepository = Repositories.getUserAccountRepository();

    @FXML
    private Optional<Account> userAccount;

    @FXML
    public void initialize() {
        final var activeSession = sessionManager.getActiveSession();

        if (activeSession.isEmpty()) {
            throw new SessionNotFoundException();
        }
        userAccount = accountRepository.getAccount(activeSession.get().getAccountId());
    }

    @FXML
    public Optional<Account> getUser (){
        return userAccount;
    }

    @FXML
    void onBackClicked(MouseEvent event){
        redirect(event, "user_new_project");
    }

    @FXML
    void onAddClicked(MouseEvent event){
        redirect(event, "user_view_member");
    }


}
