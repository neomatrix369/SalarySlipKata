package SalarySlipKata.domain;

import java.time.LocalDate;

public abstract class SalaryItem {
  private String name;
  private GBP amount = new GBP(0.0);
  private LocalDate date;

  public SalaryItem(String name, GBP amount, LocalDate date) {
    this.name = name;
    this.amount = amount;
    this.date = date;
  }

  public GBP getAmount() {
    return new GBP(amount);
  }

  public boolean forDate(LocalDate date) {
    return this.date.equals(date);
  }
}
