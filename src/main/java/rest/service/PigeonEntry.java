package rest.service;

import model.HalLink;
import org.json.JSONException;
import org.json.JSONObject;
import rest.request.TransferRequest;
import rest.response.EntryResponse;
import rest.response.Option;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

import static model.HalLink.SELF;

@Path("pigeon")
public class PigeonEntry extends ServiceBase {

    private static final String WELCOME = "Pigeon is a banking API to store Customers, Accounts, Transfer records and wiring cash.";

    private static final String CUSTOMER_TITLE = "view customer";
    private static final String CUSTOMER_HREF = "/customers/<customerNo>";
    private static final String CUSTOMER_DESC = "You can view a particular customer's personal info.";

    private static final String CUSTOMERS_TITLE = "view customers";
    private static final String CUSTOMERS_HREF = "/customers";
    private static final String CUSTOMERS_DESC = "You can view full list of customers.";

    private static final String CUSTOMER_ACCOUNTS_TITLE = "view customer accounts";
    private static final String CUSTOMER_ACCOUNTS_HREF = "/customer/<customerNo>/accounts";
    private static final String CUSTOMER_ACCOUNTS_DESC = "You can view accounts of a particular customer.";

    private static final String ACCOUNTS_TITLE = "view accounts";
    private static final String ACCOUNTS_HREF = "/accounts";
    private static final String ACCOUNTS_DESC = "You can view all accounts of all customers.";

    private static final String ACCOUNT_TITLE = "view an account";
    private static final String ACCOUNT_HREF = "/accounts/<iban>";
    private static final String ACCOUNT_DESC = "You can view a particular account in detail.";

    private static final String TRANSFER_EXE_TITLE = "execute money transfer";
    private static final String TRANSFER_EXE_HREF = "/transfer/execute";
    private static final String TRANSFER_EXE_DESC = "You can execute money transfer and see details in response.";

    private static final String TRANSFER_LIST_TITLE = "view transfer records";
    private static final String TRANSFER_LIST_HREF = "/transfer/list";
    private static final String TRANSFER_LIST_DESC = "You can list transfer records.";

    private static final String TRANSFER_RECORD_TITLE = "view a transfer record";
    private static final String TRANSFER_RECORD_HREF = "/transfer/find/<id>";
    private static final String TRANSFER_RECORD_DESC = "You can view a transfer record.";

    private static final String TRANSFER_LAST_TITLE = "view latest transfers";
    private static final String TRANSFER_LAST_HREF = "/transfer/last/<number_of_latest_transfers>";
    private static final String TRANSFER_LAST_DESC = "You can view latest transfers as many as specified.";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response welcome() {
        Set<Option> options = new HashSet<>();
        options.add(new Option(CUSTOMER_TITLE, CUSTOMER_DESC, HalLink.generate(CUSTOMER_HREF, SELF, HalLink.GET), null));
        options.add(new Option(CUSTOMERS_TITLE, CUSTOMERS_DESC, HalLink.generate(CUSTOMERS_HREF, SELF, HalLink.GET), null));
        options.add(new Option(CUSTOMER_ACCOUNTS_TITLE, CUSTOMER_ACCOUNTS_DESC, HalLink.generate(CUSTOMER_ACCOUNTS_HREF, SELF, HalLink.GET), null));
        options.add(new Option(ACCOUNTS_TITLE, ACCOUNTS_DESC, HalLink.generate(ACCOUNTS_HREF, SELF, HalLink.GET), null));
        options.add(new Option(ACCOUNT_TITLE, ACCOUNT_DESC, HalLink.generate(ACCOUNT_HREF, SELF, HalLink.GET), null));
        JSONObject transferExeSchema = null;
        try {
            transferExeSchema = new JSONObject(new String(Files.readAllBytes(new File(TransferRequest.class.getClassLoader().getResource("schema/transferRequest.jsonschema").getFile()).toPath())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        options.add(new Option(TRANSFER_EXE_TITLE, TRANSFER_EXE_DESC, HalLink.generate(TRANSFER_EXE_HREF, SELF, HalLink.PUT), transferExeSchema.toString()));
        options.add(new Option(TRANSFER_LIST_TITLE, TRANSFER_LIST_DESC, HalLink.generate(TRANSFER_LIST_HREF, SELF, HalLink.GET), null));
        options.add(new Option(TRANSFER_RECORD_TITLE, TRANSFER_RECORD_DESC, HalLink.generate(TRANSFER_RECORD_HREF, SELF, HalLink.GET), null));
        options.add(new Option(TRANSFER_LAST_TITLE, TRANSFER_LAST_DESC, HalLink.generate(TRANSFER_LAST_HREF, SELF, HalLink.GET), null));
        EntryResponse entryResponse = new EntryResponse(WELCOME, options);
        return returnOK(entryResponse);
    }
}
