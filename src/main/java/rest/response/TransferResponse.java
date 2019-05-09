package rest.response;

import model.Account;
import model.Transfer;

public class TransferResponse extends Transfer {

    private Account source;
    private Account destination;

    public TransferResponse() {}

    public TransferResponse(Account source, Account destination, Transfer transfer) {
        super(transfer.getId(), transfer.getSourceIban(), transfer.getDestinationIban(), transfer.getAmount(), transfer.getDateTime());
        this.source = source;
        this.destination = destination;
    }

    public Account getSource() {
        return source;
    }

    public void setSource(Account source) {
        this.source = source;
    }

    public Account getDestination() {
        return destination;
    }

    public void setDestination(Account destination) {
        this.destination = destination;
    }
}
