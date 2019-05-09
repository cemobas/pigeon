package rest.request;

import model.Account;

import javax.ws.rs.BadRequestException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Predicate;

public class TransferRequest {

    private static final String NO_SUCH_SOURCE_ACCOUNT = "Such source account doesn't exist.";
    private static final String NO_SUCH_DESTINATION_ACCOUNT = "Such destination account doesn't exist.";
    private static final String NOT_ENOUGH_BALANCE = "Amount exceeds the balance of the source account.";
    private static final String CURRENCY_DOESNT_MATCH = "Source and destination accounts are on different currencies.";

    private Optional<Account> source;
    private Optional<Account> destination;
    private BigDecimal amount;

    public TransferRequest(Optional<Account> source, Optional<Account> destination, BigDecimal amount) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
    }

    public boolean testAll() {
        if(!source.isPresent()) {
            throw new BadRequestException(NO_SUCH_SOURCE_ACCOUNT);
        }
        if(!destination.isPresent()) {
            throw new BadRequestException(NO_SUCH_DESTINATION_ACCOUNT);
        }
        if(isBalanceEnough(amount).negate().test(source.get())) {
            throw new BadRequestException(NOT_ENOUGH_BALANCE);
        }
        if(isCurrencyMatch(destination.get()).negate().test(source.get())) {
            throw new BadRequestException(CURRENCY_DOESNT_MATCH);
        }
        return true;
    }

    public static Predicate<Account> isBalanceEnough(BigDecimal amount)
    {
        return source -> amount.compareTo(source.getBalance().get()) <= 0;
    }

    public static Predicate<Account> isCurrencyMatch(Account destination)
    {
        return source -> destination.getAccountType().equals(source.getAccountType());
    }

    public Optional<Account> getSource() {
        return source;
    }

    public void setSource(Optional<Account> source) {
        this.source = source;
    }

    public Optional<Account> getDestination() {
        return destination;
    }

    public void setDestination(Optional<Account> destination) {
        this.destination = destination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
