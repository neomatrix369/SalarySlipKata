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

  public GBP multiplyBy(double value) {
    return new GBP(this.value * value);
  }

  public boolean isGreaterThan(double value) {
    return this.value > value;
  }

  public GBP differenceFrom(double value) {
    return new GBP(this.value - value);
  }

  public boolean isGreaterThanZero() {
    return isGreaterThan(0.00);
  }


  @Override
  public String toString() {
    return format("£%.2f", value);
  }
}
