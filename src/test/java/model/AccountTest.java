package model;

import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

import static model.Account.AccountType.PLN;
import static model.HalLink.GET;
import static model.HalLink.SELF;

public class AccountTest extends TestCase {

    private Account account;
    private HalLink expectedLink;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.account = new Account("112222333344445555", new AtomicReference<BigDecimal>(new BigDecimal(500)), PLN);
        this.expectedLink = HalLink.generate("/accounts/112222333344445555", SELF, GET);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        this.account = null;
        this.expectedLink = null;
    }

    @Test
    public void testLinkWhenConstructorRuns() {
        assertEquals(this.expectedLink.getRel(), this.account.getLink().getRel());
        assertEquals(this.expectedLink.getHref(), this.account.getLink().getHref());
        assertEquals(this.expectedLink.getType(), this.account.getLink().getType());
    }

    @Test
    public void testAdd() {
        this.account.add(new BigDecimal(500));
        assertEquals(new BigDecimal(1000), this.account.getBalance().get());
    }

    @Test
    public void testSubtractWhenAmountIsEqualToBalance() {
        try {
            this.account.subtract(new BigDecimal(500));
            assertEquals(new BigDecimal(0), this.account.getBalance().get());
        } catch (IllegalAccessException e) {
            fail();
        }
    }

    @Test
    public void testSubtractWhenAmountIsHigherThanBalance() {
        try {
            this.account.subtract(new BigDecimal(600));
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalAccessException);
        }
    }
}
