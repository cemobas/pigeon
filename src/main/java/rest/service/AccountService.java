package rest.service;

import model.AccountSet;
import rest.response.AccountResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.NoSuchElementException;
import java.util.Optional;

@Path("/accounts")
public class AccountService extends ServiceBase{

    private static final String NO_SUCH_ACCOUNT = "No such account.";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccounts() {
        return returnOK(new AccountSet(dao.fetchAccounts()));
    }

    @GET
    @Path("/{iban}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAccountByNo(@PathParam("iban")String iban) {
        Optional<AccountResponse> accountOpt = dao.fetchAccountResponseByIban(iban);
        if(accountOpt.isPresent()) {
            return returnOK(accountOpt.get());
        }
        throw new NoSuchElementException(NO_SUCH_ACCOUNT);
    }
}