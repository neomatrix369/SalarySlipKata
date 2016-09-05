package SalarySlipKata.domain;

import java.time.LocalDate;

public class Overtime extends SalaryItem {
  public Overtime(GBP amount, LocalDate date) {
    super(amount, date);
  }
}
