package SalarySlipKata.domain;

import static SalarySlipKata.domain.Type.EARNING;

import java.time.LocalDate;

public class Overtime extends SalaryItem {
  public Overtime(GBP amount, LocalDate date) {
    super("Overtime", amount, date);
    type = EARNING;
  }
}
