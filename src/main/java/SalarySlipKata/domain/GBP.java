package SalarySlipKata.domain;

import static java.lang.String.format;

public class GBP {
  private double value;

  public GBP(double value) {
    this.value = value;
  }

  public GBP(GBP amount) {
    this.value = amount.value;
  }

  @Override
  public String toString() {
    return format("£%.2f", value);
  }
}
