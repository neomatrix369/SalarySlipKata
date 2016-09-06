package SalarySlipKata.domain;

import static SalarySlipKata.domain.Type.DEDUCTION;

import java.time.LocalDate;

public class Loan extends SalaryItem {
  public Loan(GBP amount, LocalDate date) {
    super("Loan", amount, date);
    type = DEDUCTION;
  }
}
