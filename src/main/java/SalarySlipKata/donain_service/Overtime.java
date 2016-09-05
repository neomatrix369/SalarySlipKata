package SalarySlipKata.donain_service;

import java.time.LocalDate;

import SalarySlipKata.domain.GBP;
import SalarySlipKata.domain.SalaryItem;

public class Overtime implements SalaryItem {
  private final GBP amount;
  private final LocalDate date;

  public Overtime(GBP amount, LocalDate date) {
    this.amount = amount;
    this.date = date;
  }

  @Override
  public GBP getAmount() {
    return new GBP(amount);
  }
}
