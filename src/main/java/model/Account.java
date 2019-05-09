package model;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

import static model.HalLink.GET;
import static model.HalLink.SELF;

public class Account {

  public enum AccountType { EUR, USD, PLN, GBP };

  private String iban;
  private AtomicReference<BigDecimal> balance;
  private AccountType accountType;
  private HalLink link;

  public Account() {}

  public Account(String iban, AtomicReference<BigDecimal> balance, AccountType accountType) {
    this.iban = iban;
    this.balance = balance;
    this.accountType = accountType;
    this.link = HalLink.generate("/accounts/"+iban, SELF, GET);
  }

  public void add(BigDecimal amount) {
    this.balance.accumulateAndGet(amount, (x1, x2) -> x1.add(x2));
  }

  public void subtract(BigDecimal amount) throws IllegalAccessException {
    if(amount.compareTo(balance.get()) == 1) {
      throw new IllegalAccessException("Insufficient balance!");
    }
    this.balance.accumulateAndGet(amount, (x1, x2) -> x1.subtract(x2));
  }

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public AtomicReference<BigDecimal> getBalance() {
    return balance;
  }

  public void setBalance(AtomicReference<BigDecimal> balance) {
    this.balance = balance;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountType accountType) {
    this.accountType = accountType;
  }

  public HalLink getLink() {
    return link;
  }

  public void setLink(HalLink link) {
    this.link = link;
  }

}
