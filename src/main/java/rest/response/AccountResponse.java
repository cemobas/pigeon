package rest.response;

import model.Account;
import model.Customer;
import model.HalLink;

public class AccountResponse extends Account {
    private String customerName;
    private String customerSurname;
    private long customerNo;
    private HalLink customerLink;

    public AccountResponse(){}

    public AccountResponse(Customer customer, Account account){
        super(account.getIban(), account.getBalance(), account.getAccountType());
        this.customerName = customer.getName();
        this.customerSurname = customer.getSurname();
        this.customerNo = customer.getCustomerNo();
        this.customerLink = customer.getLink();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public long getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(long customerNo) {
        this.customerNo = customerNo;
    }

    public HalLink getCustomerLink() {
        return customerLink;
    }

    public void setCustomerLink(HalLink customerLink) {
        this.customerLink = customerLink;
    }
}
