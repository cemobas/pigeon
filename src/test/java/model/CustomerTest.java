package model;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Collections;

import static model.HalLink.GET;
import static model.HalLink.SELF;

public class CustomerTest extends TestCase {
    private Customer customer;
    private HalLink expectedLink;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.customer = new Customer("Kamil", "Glik", 600, Collections.EMPTY_SET);
        this.expectedLink = HalLink.generate("/customers/600", SELF, GET);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        this.customer = null;
        this.expectedLink = null;
    }

    @Test
    public void testLinkWhenConstructorRuns() {
        assertEquals(this.expectedLink.getRel(), this.customer.getLink().getRel());
        assertEquals(this.expectedLink.getHref(), this.customer.getLink().getHref());
        assertEquals(this.expectedLink.getType(), this.customer.getLink().getType());
    }

}
