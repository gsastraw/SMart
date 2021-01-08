package se.gu.smart.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import se.gu.smart.model.Ticket;
import se.gu.smart.model.account.Account;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.TicketRepository;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;

public class ReportsController extends BaseAdminController {
    private final TicketRepository ticketRepository = Repositories.getTicketRepository();
    private final ObservableList<Ticket> tickets = FXCollections.observableArrayList(ticketRepository.getTickets());

    @FXML
    private TableView<Ticket> reportTableView;

    @FXML
    private TableColumn<Ticket, String> ticketID;

    @FXML
    private TableColumn<Ticket, String> title;

    @FXML
    private TableColumn<Ticket, Account> createdBy;

    @FXML
    private TableColumn<Ticket, String> ticketDescription;

    @FXML
    private TableColumn<Ticket, Ticket.Status> status;

    @FXML
    private TableColumn<Ticket, LocalDate> dateOpened;

    @FXML
    private TableColumn<Ticket, LocalDate> dateClosed;

    @FXML
    private Button changeStatusButton;

    @FXML
    public void initialize() {
        super.initialize();

        ticketID.setCellValueFactory(new PropertyValueFactory<Ticket, String>("ticketId"));
        title.setCellValueFactory(new PropertyValueFactory<Ticket, String>("title"));
        createdBy.setCellValueFactory(new PropertyValueFactory<Ticket, Account>("createdBy"));
        ticketDescription.setCellValueFactory(new PropertyValueFactory<Ticket, String>("description"));
        status.setCellValueFactory(new PropertyValueFactory<Ticket, Ticket.Status>("status"));
        dateOpened.setCellValueFactory(new PropertyValueFactory<Ticket, LocalDate>("dateOpened"));
        dateClosed.setCellValueFactory(new PropertyValueFactory<Ticket, LocalDate>("dateClosed"));

        reportTableView.setEditable(true);
        reportTableView.setItems(tickets);


    }
    @FXML
    void changeStatus(MouseEvent event) {
        Ticket selectedTicket = reportTableView.getSelectionModel().getSelectedItem();
        if (selectedTicket != null) {
            selectedTicket.setStatus(Ticket.Status.RESOLVED);
            selectedTicket.setDateClosed(LocalDate.now());
            reportTableView.refresh();
        } else return;
    }
}


