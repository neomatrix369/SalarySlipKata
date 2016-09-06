package SalarySlipKata.domain;

import java.time.LocalDate;

public abstract class SalaryItem {
  private GBP amount = new GBP(0.0);
  private LocalDate date;

  public SalaryItem(GBP amount, LocalDate date) {
    this.amount = amount;
    this.date = date;
  }

  public GBP getAmount() {
    return new GBP(amount);
  }
}
