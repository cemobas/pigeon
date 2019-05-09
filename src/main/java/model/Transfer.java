package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static model.HalLink.GET;
import static model.HalLink.SELF;

public class Transfer {

  private int id;
  private String sourceIban;
  private String destinationIban;
  private BigDecimal amount;
  private LocalDateTime dateTime;
  private HalLink link;

  public Transfer() {}

  public Transfer(int id, String sourceIban, String destinationIban, BigDecimal amount, LocalDateTime dateTime) {
    this.id = id;
    this.sourceIban = sourceIban;
    this.destinationIban = destinationIban;
    this.amount = amount;
    this.dateTime = dateTime;
    this.link = HalLink.generate("/transfer/find/"+id, SELF, GET);
  }

  public boolean isValid() {
    return sourceIban != null && destinationIban != null && amount != null;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSourceIban() {
    return sourceIban;
  }

  public void setSourceIban(String sourceIban) {
    this.sourceIban = sourceIban;
  }

  public String getDestinationIban() {
    return destinationIban;
  }

  public void setDestinationIban(String destinationIban) {
    this.destinationIban = destinationIban;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public HalLink getLink() {
    return link;
  }

  public void setLink(HalLink link) {
    this.link = link;
  }
}
