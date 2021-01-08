package se.gu.smart.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import se.gu.smart.exception.SessionNotFoundException;
import se.gu.smart.model.account.Account;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.security.session.SessionManager;

import java.util.Optional;
import java.util.Random;

public class BaseUserController extends BaseController {

    private final SessionManager sessionManager = SessionManager.getInstance();
    private final AccountRepository accountRepository = Repositories.getAccountRepository();

    @FXML
    private Text topbarDisplaynameText;

    @FXML
    private Text sidebarUsernameText;

    @FXML
    private Optional<Account> loggedUserBase;

    @FXML
    protected Text randomQuoteText;

    @FXML
    protected Text randomQuoteAuthor;

    @FXML
    void randomizeQuotes(){
        String[] quoteTexts = {
                "If opportunity doesn’t knock, build a door.",
                "Carpe Diem",
                "Live Laugh Love",
                "In a gentle way, you can shake the world.",
                "The man who moves a mountain begins by carrying away small stones.",
                "I'm a success today because I had a friend who believed in me and I didn't have the heart to let him down.",
                "Stand for something or you will fall for anything. Today’s mighty oak is yesterday’s nut that held its ground.",
                "Opportunity is missed by most people because it is dressed in overalls and looks like work.",
                "Ugandan Bruce Lee... they call him... BRUCE U!",
                "When you reach the end of your rope, tie a knot in it and hang on."};
        String[] quoteAuthors = {
                "Milton Berle",
                "Horatious",
                "Karen",
                "Gandhi",
                "Confucius",
                "Abraham Lincoln",
                "Rosa Parks",
                "Thomas Edison",
                "VJ Emmie",
                "Franklin D. Roosevelt"};

        int x;
        Random rand = new Random();
        x = rand.nextInt(quoteTexts.length);
        randomQuoteText.setText("”" + quoteTexts[x] + "”");
        randomQuoteAuthor.setText("- " + quoteAuthors[x]);
    }

    @FXML
    void logOut(MouseEvent event) {
        sessionManager.removeActiveSession();
        redirect(event, "login.fxml");
    }

    @FXML
    void redirectReports(MouseEvent event) {
        redirect(event, "user_create_ticket");
    }

    @FXML
    void redirectViewProfile(MouseEvent event) {
        redirect(event, "user_view_profile");
    }

    @FXML
    void redirectDashboard(MouseEvent event) {
        redirect(event, "user_dashboard");
    }

    @FXML
    void redirectTimesheet(MouseEvent event) {
        redirect(event, "user_timesheet");
    }

    @FXML
    public void initialize() {
        randomizeQuotes();
        final var activeSession = sessionManager.getActiveSession();

        if (activeSession.isEmpty()) {
            throw new SessionNotFoundException();
        }

        this.loggedUserBase = accountRepository.getAccount(activeSession.get().getAccountId());
        loadTextDisplay();
    }
    @FXML
    void loadTextDisplay() {
        topbarDisplaynameText.setText(String.valueOf(loggedUserBase.get().getDisplayName()));
        sidebarUsernameText.setText(String.valueOf(loggedUserBase.get().getUsername()));
    }
}
