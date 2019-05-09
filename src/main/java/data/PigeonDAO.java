package data;

import model.Account;
import model.Customer;
import model.Transfer;
import rest.request.TransferRequest;
import rest.response.AccountResponse;
import rest.response.TransferResponse;

import javax.ws.rs.BadRequestException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PigeonDAO {

    private PigeonData data;
    private static PigeonDAO dao;

    private PigeonDAO() {
        data = PigeonData.getInstance();
    }

    public static PigeonDAO getInstance() {
        if(dao == null) {
            dao = new PigeonDAO();
        }
        return dao;
    }

    public Set<Customer> fetchCustomers() {
        return data.getCustomers();
    }

    public Set<Account> fetchAccounts() {
        return data.getCustomers().stream()
                .map(customer -> customer.getAccounts().stream().map(account -> new AccountResponse(customer, account)).collect(Collectors.toSet()))
                .flatMap(accounts -> accounts.stream())
                .collect(Collectors.toSet());
    }

    public TreeSet<Transfer> fetchTransfers() {
        return data.getTransfers();
    }

    public Optional<Transfer> fetchTransferById(int id) {
        return data.getTransfers().stream()
                .filter(transfer -> id == transfer.getId())
                .findFirst();
    }

    public Optional<Account> fetchAccountByIban(String iban) {
        return data.getCustomers().stream()
                .map(customer -> customer.getAccounts())
                .flatMap(accounts -> accounts.stream())
                .filter(account -> iban.equals(account.getIban()))
                .findFirst();
    }

    public Optional<AccountResponse> fetchAccountResponseByIban(String iban) {
        return data.getCustomers().stream()
                .map(customer -> customer.getAccounts().stream().map(account -> new AccountResponse(customer, account)).collect(Collectors.toSet()))
                .flatMap(accounts -> accounts.stream())
                .filter(account -> iban.equals(account.getIban()))
                .findFirst();
    }

    public Optional<Customer> fetchCustomerByNo(long customerNo) {
        return fetchCustomers().stream().filter(c -> customerNo == c.getCustomerNo()).findFirst();
    }

    public synchronized TransferResponse executeTransfer(Transfer transfer) throws IllegalAccessException {
        if(transfer.isValid()) {
            Optional<Account> source = fetchAccountByIban(transfer.getSourceIban());
            Optional<Account> destination = fetchAccountByIban(transfer.getDestinationIban());
            BigDecimal amount = transfer.getAmount();
            TransferRequest rq = new TransferRequest(source, destination, amount);

            if(rq.testAll()) {
                source.get().subtract(amount);
                destination.get().add(amount);
                transfer.setDateTime(LocalDateTime.now());
                transfer.setId(pickId());
                insertTransfer(transfer);
                return new TransferResponse(source.get(), destination.get(), transfer);
            }
        }

        throw new BadRequestException("Invalid request.");
    }

    private void insertTransfer(Transfer transfer) {
        data.getTransfers().add(transfer);
    }

    public int pickId() {
        return data.pickId();
    }
}
