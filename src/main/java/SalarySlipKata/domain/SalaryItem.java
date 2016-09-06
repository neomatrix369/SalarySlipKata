package SalarySlipKata.domain;

import static SalarySlipKata.domain.GBP.zero;
import static SalarySlipKata.domain.Type.DEDUCTION;
import static SalarySlipKata.domain.Type.EARNING;
import static SalarySlipKata.domain.Type.NONE;

import java.time.LocalDate;

public abstract class SalaryItem {
  private String name;
  private GBP amount = zero();
  private LocalDate date;

  protected Type type = NONE;

  public SalaryItem(String name, GBP amount, LocalDate date) {
    this.name = name;
    this.amount = amount;
    this.date = date;
  }

  public static SalaryItem getDefaultSalaryItem(final LocalDate date) {
    return new SalaryItem("", zero(), date) {
      @Override
      public GBP getAmount() {
        return super.getAmount();
      }
    };
  }

  public GBP getAmount() {
    return new GBP(amount);
  }

  public boolean forDate(LocalDate date) {
    return this.date.equals(date);
  }

  public boolean isEarning() {
    return type == EARNING;
  }

  public boolean isDeduction() {
    return type == DEDUCTION;
  }
}
