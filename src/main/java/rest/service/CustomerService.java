package rest.service;

import model.Account;
import model.AccountSet;
import model.Customer;
import model.CustomerSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Path("/customers")
public class CustomerService extends ServiceBase{

    private static final String NO_SUCH_CUSTOMER = "No such customer.";
    private static final String CUSTOMER_HAS_NO_ACCOUNTS = "This customer has no accounts.";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        return returnOK(new CustomerSet(dao.fetchCustomers()));
    }

    @GET
    @Path("/{customerNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCustomerByNo(@PathParam("customerNo")long customerNo) {
        Optional<Customer> customerOpt = dao.fetchCustomerByNo(customerNo);
        if(customerOpt.isPresent()) {
            return returnOK(customerOpt.get());
        }
        throw new NoSuchElementException(NO_SUCH_CUSTOMER);
    }

    @GET
    @Path("/{customerNo}/accounts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountsByCustomerNo(@PathParam("customerNo")long customerNo) {
        Optional<Customer> customer = dao.fetchCustomerByNo(customerNo);
        if(customer.isPresent()) {
            Set<Account> accounts = customer.get().getAccounts();
            if(accounts.size() > 0) {
                return returnOK(new AccountSet(accounts));
            }
            throw new NoSuchElementException(CUSTOMER_HAS_NO_ACCOUNTS);
        }
        throw new NoSuchElementException(NO_SUCH_CUSTOMER);
    }
}