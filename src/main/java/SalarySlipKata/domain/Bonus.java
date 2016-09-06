package SalarySlipKata.domain;

import java.time.LocalDate;

public class Bonus extends SalaryItem {
  public Bonus(GBP amount, LocalDate date) {
    super("Bonus", amount, date);
  }
}
