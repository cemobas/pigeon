package rest.request;

import junit.framework.TestCase;
import model.Account;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static model.Account.AccountType.EUR;
import static model.Account.AccountType.PLN;

public class TransferRequestTest extends TestCase {

    private TransferRequest transferRequestBalanceOverAmount;
    private TransferRequest transferRequestBalanceEqualToAmount;
    private TransferRequest transferRequestBalanceNotEnough;
    private TransferRequest transferRequestCurrencyMatch;
    private TransferRequest transferRequestCurrencyDontMatch;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Account source = new Account("112222333344447777", new AtomicReference<BigDecimal>(new BigDecimal(11)), PLN);
        Account destination = new Account("992222333344447777", new AtomicReference<BigDecimal>(new BigDecimal(99)), PLN);
        transferRequestBalanceOverAmount = new TransferRequest(Optional.of(source), Optional.of(destination), new BigDecimal(1));
        transferRequestCurrencyMatch = new TransferRequest(Optional.of(source), Optional.of(destination), new BigDecimal(1));

        source = new Account("112222333344447777", new AtomicReference<BigDecimal>(new BigDecimal(11)), PLN);
        destination = new Account("992222333344447777", new AtomicReference<BigDecimal>(new BigDecimal(99)), PLN);
        transferRequestBalanceNotEnough = new TransferRequest(Optional.of(source), Optional.of(destination), new BigDecimal(12));

        source = new Account("112222333344447777", new AtomicReference<BigDecimal>(new BigDecimal(11)), PLN);
        destination = new Account("992222333344447777", new AtomicReference<BigDecimal>(new BigDecimal(99)), EUR);
        transferRequestBalanceEqualToAmount = new TransferRequest(Optional.of(source), Optional.of(destination), new BigDecimal(11));

        source = new Account("112222333344447777", new AtomicReference<BigDecimal>(new BigDecimal(11)), PLN);
        destination = new Account("992222333344447777", new AtomicReference<BigDecimal>(new BigDecimal(99)), EUR);
        transferRequestCurrencyDontMatch = new TransferRequest(Optional.of(source), Optional.of(destination), new BigDecimal(1));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        transferRequestBalanceOverAmount = null;
        transferRequestBalanceNotEnough = null;
        transferRequestBalanceEqualToAmount = null;
        transferRequestCurrencyMatch = null;
        transferRequestCurrencyDontMatch = null;
    }

    @Test
    public void testIsBalanceEnoughWhenBalanceEnough() {
        assertTrue(TransferRequest.isBalanceEnough(transferRequestBalanceOverAmount.getAmount()).test(transferRequestBalanceOverAmount.getSource().get()));
    }

    @Test
    public void testIsBalanceEnoughWhenBalanceEqualToAmount() {
        assertTrue(TransferRequest.isBalanceEnough(transferRequestBalanceEqualToAmount.getAmount()).test(transferRequestBalanceEqualToAmount.getSource().get()));
    }

    @Test
    public void testIsBalanceEnoughWhenBalanceNotEnough() {
        assertFalse(TransferRequest.isBalanceEnough(transferRequestBalanceNotEnough.getAmount()).test(transferRequestBalanceNotEnough.getSource().get()));
    }

    @Test
    public void testIsBalanceEnoughWhenCurrencyMatch() {
        assertTrue(TransferRequest.isCurrencyMatch(transferRequestCurrencyMatch.getSource().get()).test(transferRequestCurrencyMatch.getDestination().get()));
    }

    @Test
    public void testIsBalanceEnoughWhenCurrencyDontMatch() {
        assertFalse(TransferRequest.isCurrencyMatch(transferRequestCurrencyDontMatch.getSource().get()).test(transferRequestCurrencyDontMatch.getDestination().get()));
    }
}
