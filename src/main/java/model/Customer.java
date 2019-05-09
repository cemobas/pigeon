package model;

import java.util.Set;

import static model.HalLink.GET;
import static model.HalLink.SELF;

public class Customer {

  private String name;
  private String surname;
  private long customerNo;
  private Set<Account> accounts;
  private HalLink link;

  public Customer() {}

  public Customer(String name, String surname, long customerNo, Set<Account> accounts) {
    this.name = name;
    this.surname = surname;
    this.customerNo = customerNo;
    this.accounts = accounts;
    this.link = HalLink.generate("/customers/"+customerNo, SELF, GET);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public long getCustomerNo() {
    return customerNo;
  }

  public void setCustomerNo(long customerNo) {
    this.customerNo = customerNo;
  }

  public Set<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(Set<Account> accounts) {
    this.accounts = accounts;
  }

  public HalLink getLink() {
    return link;
  }

  public void setLink(HalLink link) {
    this.link = link;
  }
}
