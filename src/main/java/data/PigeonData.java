package data;

import model.Account;
import model.Customer;
import model.Transfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static model.Account.AccountType.*;

public class PigeonData {

  private static PigeonData data;
  private final IdPicker idPicker = new IdPicker();
  private final TreeSet<Transfer> transfers = new TreeSet<>(Comparator.comparing((Transfer t) -> t.getDateTime()).reversed());
  private final Set<Customer> customers = new HashSet<>();

  private PigeonData() {

    transfers.add(new Transfer(idPicker.pick(), "112222333355557777", "112222333355556666", new BigDecimal(50), LocalDateTime.now().minusDays(10)));
    transfers.add(new Transfer(idPicker.pick(), "112222333344447777", "112222333344445555", new BigDecimal(500), LocalDateTime.now().minusDays(15)));
    transfers.add(new Transfer(idPicker.pick(), "112222333344449999", "112222333344446666", new BigDecimal(20), LocalDateTime.now().minusDays(16)));
    transfers.add(new Transfer(idPicker.pick(), "112222333344443333", "112222333344440000", new BigDecimal(50), LocalDateTime.now().minusDays(20)));
    transfers.add(new Transfer(idPicker.pick(), "112222333344448888", "110000333344448888", new BigDecimal(10), LocalDateTime.now().minusDays(50)));

    Set<Account> c1Accounts = new HashSet<>();
    c1Accounts.add(new Account("112222333344445555", new AtomicReference<BigDecimal>(new BigDecimal(10000500)), PLN));
    c1Accounts.add(new Account("112222333344446666", new AtomicReference<BigDecimal>(new BigDecimal(2000020)), EUR));
    Customer c1 = new Customer("Michał", "Pazdan", 301, c1Accounts);
    customers.add(c1);

    Set<Account> c2Accounts = new HashSet<>();
    c2Accounts.add(new Account("112222333344447777", new AtomicReference<BigDecimal>(new BigDecimal(500)), PLN));
    c1Accounts.add(new Account("112222333344443333", new AtomicReference<BigDecimal>(new BigDecimal(10000000)), USD));
    c1Accounts.add(new Account("112222333355556666", new AtomicReference<BigDecimal>(new BigDecimal(50)), GBP));
    Customer c2 = new Customer("Kamil", "Grosicki", 302, c2Accounts);
    customers.add(c2);

    Set<Account> c3Accounts = new HashSet<>();
    c3Accounts.add(new Account("112222333344448888", new AtomicReference<BigDecimal>(new BigDecimal(50000000)), PLN));
    c3Accounts.add(new Account("112222333344449999", new AtomicReference<BigDecimal>(new BigDecimal(60000000)), EUR));
    c3Accounts.add(new Account("112222333344440000", new AtomicReference<BigDecimal>(new BigDecimal(70000050)), USD));
    c1Accounts.add(new Account("112222333355557777", new AtomicReference<BigDecimal>(new BigDecimal(0)), GBP));
    Customer c3 = new Customer("Robert", "Lewandowski", 303, c3Accounts);
    customers.add(c3);

    Set<Account> c4Accounts = new HashSet<>();
    c4Accounts.add(new Account("110000333344448888", new AtomicReference<BigDecimal>(new BigDecimal(10)), PLN));
    c4Accounts.add(new Account("110000333344449999", new AtomicReference<BigDecimal>(new BigDecimal(0)), EUR));
    c4Accounts.add(new Account("110000333344440000", new AtomicReference<BigDecimal>(new BigDecimal(0)), USD));
    c1Accounts.add(new Account("110000333355557777", new AtomicReference<BigDecimal>(new BigDecimal(0)), GBP));
    Customer c4 = new Customer("Adam", "Nawałka", 304, c4Accounts);
    customers.add(c4);

    customers.add(new Customer("Cem Onur", "Baş", 305, Collections.EMPTY_SET));
  }

  public static PigeonData getInstance() {
    if(data==null) {
      data = new PigeonData();
    }
    return data;
  }

  public TreeSet<Transfer> getTransfers() {
    return transfers;
  }

  public Set<Customer> getCustomers() {
    return customers;
  }

  public int pickId() {
    return idPicker.pick();
  }

}
