package model;

import java.util.Set;

public class TransferSet {
    private Set<Transfer> transfers;

    public TransferSet() {

    }

    public TransferSet(Set<Transfer> transfers) {
        this.transfers = transfers;
    }

    public Set<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(Set<Transfer> transfers) {
        this.transfers = transfers;
    }
}
