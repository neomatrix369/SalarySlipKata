package SalarySlipKata.domain;

import java.time.LocalDate;

public class BasicSalary implements SalaryItem {
  private final GBP amount;
  private final LocalDate date;

  public BasicSalary(GBP amount, LocalDate date) {
    this.amount = amount;
    this.date = date;
  }

  @Override
  public GBP getAmount() {
    return new GBP(amount);
  }
}
