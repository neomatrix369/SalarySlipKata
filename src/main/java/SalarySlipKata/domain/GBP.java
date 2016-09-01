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

  public GBP dividedBy(int value) {
    return new GBP(this.value / 12);
  }

  public GBP differenceFrom(double value) {
    return new GBP(this.value - value);
  }

  public GBP minus(double value) {
    return new GBP(this.value - value);
  }

  public boolean isGreaterThanZero() {
    return isGreaterThan(0.00);
  }

  public boolean isGreaterThan(double value) {
    return this.value > value;
  }

  public boolean isGreaterThanOrEqualTo(double value) {
    return this.value >= value;
  }

  public boolean isLessThanOrEqualTo(double value)  {
    return this.value <= value;
  }

  @Override
  public String toString() {
    return format("£%.2f", value);
  }
}
