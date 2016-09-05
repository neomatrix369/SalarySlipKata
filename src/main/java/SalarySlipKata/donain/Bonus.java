package SalarySlipKata.donain;

import java.time.LocalDate;

import SalarySlipKata.domain.GBP;
import SalarySlipKata.domain.SalaryItem;

public class Bonus implements SalaryItem {
  private final GBP amount;
  private final LocalDate date;

  public Bonus(GBP amount, LocalDate date) {
    this.amount = amount;
    this.date = date;
  }

  @Override
  public GBP getAmount() {
    return amount;
  }
}
