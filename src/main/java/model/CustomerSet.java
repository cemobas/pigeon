package model;

import java.util.Set;

public class CustomerSet {
    private Set<Customer> customers;

    public CustomerSet() {

    }

    public CustomerSet(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
