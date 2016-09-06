package SalarySlipKata.domain;

import static SalarySlipKata.domain.Type.EARNING;

import java.time.LocalDate;

public class Bonus extends SalaryItem {
  public Bonus(GBP amount, LocalDate date) {
    super("Bonus", amount, date);
    type = EARNING;
  }
}
