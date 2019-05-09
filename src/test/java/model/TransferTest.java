package model;

import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static model.HalLink.GET;
import static model.HalLink.SELF;

public class TransferTest extends TestCase {

    private Transfer validTransfer;
    private Transfer invalidTransfer;
    private HalLink expectedLink;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.validTransfer = new Transfer(100, "112222333355557777", "112222333355556666", new BigDecimal(50), LocalDateTime.now());
        this.invalidTransfer = new Transfer(100, null, "112222333355556666", new BigDecimal(50), LocalDateTime.now());
        this.expectedLink = HalLink.generate("/transfer/find/100", SELF, GET);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        this.validTransfer = null;
        this.invalidTransfer = null;
        this.expectedLink = null;
    }

    @Test
    public void testLinkWhenConstructorRuns() {
        assertEquals(this.expectedLink.getRel(), this.validTransfer.getLink().getRel());
        assertEquals(this.expectedLink.getHref(), this.validTransfer.getLink().getHref());
        assertEquals(this.expectedLink.getType(), this.validTransfer.getLink().getType());
    }

    @Test
    public void testIsValidWhenSourceIbanIsNull() {
        assertFalse(this.invalidTransfer.isValid());
    }
}
