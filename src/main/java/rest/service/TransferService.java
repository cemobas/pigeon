package rest.service;

import model.Transfer;
import model.TransferSet;
import rest.response.TransferResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/transfer")
public class TransferService extends ServiceBase{

    private static final String NO_SUCH_TRANSFER = "No such transfer.";

    @PUT
    @Path("/execute")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response execute(Transfer transfer) throws IllegalAccessException {
        TransferResponse transferResponse = dao.executeTransfer(transfer);
        return returnOK(transferResponse);
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransfers() {
        return returnOK(new TransferSet(dao.fetchTransfers()));
    }

    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransferById(@PathParam("id")int id) {
        Optional<Transfer> transferOpt = dao.fetchTransferById(id);
        if(transferOpt.isPresent()) {
            return returnOK(transferOpt.get());
        }
        throw new NoSuchElementException(NO_SUCH_TRANSFER);
    }

    @GET
    @Path("/last/{numberOfRecords}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransfers(@PathParam("numberOfRecords")int numberOfRecords) {
        return returnOK(new TransferSet(dao.fetchTransfers().stream().limit(numberOfRecords).collect(Collectors.toSet())));
    }
}