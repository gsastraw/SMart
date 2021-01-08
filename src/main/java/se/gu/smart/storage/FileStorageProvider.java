package se.gu.smart.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import se.gu.smart.model.Ticket;
import se.gu.smart.model.account.Account;
import se.gu.smart.model.account.AccountCredentials;
import se.gu.smart.model.project.Project;
import se.gu.smart.repository.AccountCredentialsRepository;
import se.gu.smart.repository.AccountRepository;
import se.gu.smart.repository.ProjectRepository;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.Repository;
import se.gu.smart.repository.TicketRepository;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public final class FileStorageProvider implements StorageProvider {

    private static final String STORAGE_FOLDER = "storage";

    private static final String ACCOUNTS_FILE = "accounts.json";
    private static final String ACCOUNT_CREDENTIALS_FILE = "credentials.json";
    private static final String TICKETS = "tickets.json";
    private static final String PROJECTS = "projects.json";

    private final ObjectMapper objectMapper = JsonMapper.builder()
        .addModule(new JavaTimeModule())
        .build();

    private final AccountRepository accountRepository = Repositories.getAccountRepository();
    private final AccountCredentialsRepository accountCredentialsRepository = Repositories.getAccountCredentialsRepository();
    private final TicketRepository ticketRepository = Repositories.getTicketRepository();
    private final ProjectRepository projectRepository = Repositories.getProjectRepository();

    FileStorageProvider() {
        

    }

    @Override
    public void save() throws IOException {
        ensureStorageFilesExist();

        try {
            saveRepository(ACCOUNTS_FILE, accountRepository.getAccounts());
            saveRepository(ACCOUNT_CREDENTIALS_FILE, accountCredentialsRepository.getAllAccountCredentials());
            saveRepository(TICKETS, ticketRepository.getTickets());
            saveRepository(PROJECTS, projectRepository.getProjects());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void saveRepository(String fileName, Set<?> values) throws URISyntaxException, IOException {
        final var path = Path.of(STORAGE_FOLDER + "/" + fileName);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), values);
    }

    @Override
    public void load() throws IOException {
        ensureStorageFilesExist();

        loadRepository(ACCOUNTS_FILE, accountRepository, Account.class);
        loadRepository(ACCOUNT_CREDENTIALS_FILE, accountCredentialsRepository, AccountCredentials.class);
        loadRepository(TICKETS, ticketRepository, Ticket.class);
        loadRepository(PROJECTS, projectRepository, Project.class);
    }

    @SuppressWarnings("unchecked")
    private <T> void loadRepository(String fileName, Repository<T> repository, Class<T> clazz) throws IOException {
        final var file = new File(STORAGE_FOLDER + "/" + fileName);

        T[] elements = (T[]) objectMapper.readValue(file, clazz.arrayType());

        final List<T> ts = Arrays.asList(elements);

        repository.load(ts);
    }

    private void ensureStorageFilesExist() throws IOException {
        ensureStorageFileExists(ACCOUNTS_FILE);
        ensureStorageFileExists(ACCOUNT_CREDENTIALS_FILE);
        ensureStorageFileExists(TICKETS);
        ensureStorageFileExists(PROJECTS);
    }

    private void ensureStorageFileExists(String file) throws IOException {
        final var path = Path.of(STORAGE_FOLDER + "/" + file);

        if (Files.notExists(path, LinkOption.NOFOLLOW_LINKS)) {
            Files.createFile(path);

            objectMapper.writeValue(path.toFile(), JsonNodeFactory.instance.arrayNode());
        }
    }
}
