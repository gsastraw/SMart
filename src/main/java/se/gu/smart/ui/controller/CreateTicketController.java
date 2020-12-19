package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.model.Ticket;
import se.gu.smart.model.account.Account;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.TicketRepository;
import se.gu.smart.security.session.SessionManager;
import se.gu.smart.service.AccountService;
import se.gu.smart.service.Services;

import java.util.Optional;

public class CreateTicketController extends BaseUserController{
    private final SessionManager sessionManager = SessionManager.getInstance();
    private final AccountRepository accountRepository = Repositories.getUserAccountRepository();
    private final TicketRepository ticketRepository = Repositories.getTicketRepository();
    @FXML
    private Optional<Account> loggedUser;

    @FXML
    private TextField ticketNameField;

    @FXML
    private TextField ticketDescriptionField;

    @FXML
    private Text byUsername;

    @FXML
    private Button createTicketButton;

    @FXML
    public void initialize() {
        super.initialize();

        final var activeSession = sessionManager.getActiveSession();

        if (activeSession.isEmpty()) {
            throw new SessionNotFoundException();
        }

        this.loggedUser = accountRepository.getAccount(activeSession.get().getAccountId());

        loadUserData();

    }
    @FXML
    void loadUserData() {
        byUsername.setText(String.valueOf(loggedUser.get().getUsername()));
    }

    @FXML
    void onDoneClicked(MouseEvent event) {
        /*if (ticketNameField.getText().isEmpty() || ticketDescriptionField.getText().isEmpty()) {
            ticketNameField.setPromptText("Please fill this in before submitting a ticket");
            ticketDescriptionField.setPromptText("Please fill this in before submitting a ticket");
        } else {
            ticketRepository.createTicket(
                    ticketNameField.getText(),
                    ticketDescriptionField.getText(),
                    (loggedUser.get().getUsername()) DOESN'T WORK (?)
            );
        }*/

    }
}
