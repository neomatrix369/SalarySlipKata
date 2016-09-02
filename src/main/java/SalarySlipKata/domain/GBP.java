package SalarySlipKata.domain;

import static java.lang.String.format;

public class GBP {
  private double denomination;

  public GBP(double anotherDenomination) {
    this.denomination = anotherDenomination;
  }

  public GBP(GBP amount) {
    this.denomination = amount.denomination;
  }

  public GBP multiplyBy(double value) {
    return new GBP(this.denomination * value);
  }

  public GBP dividedBy(int value) {
    return new GBP(this.denomination / value);
  }

  public GBP differenceFrom(double anotherDenomination) {
    return new GBP(this.denomination - anotherDenomination);
  }

  public GBP plus(GBP amount) {
    return new GBP(this.denomination + amount.denomination);
  }

  public GBP minus(double anotherDenomination) {
    return new GBP(this.denomination - anotherDenomination);
  }

  public GBP minus(GBP amount) {
    return new GBP(this.denomination - amount.denomination);
  }

  public boolean isGreaterThanZero() {
    return this.denomination > 0.00;
  }

  public boolean isGreaterThanOrEqualTo(double anotherDenomination) {
    return this.denomination >= anotherDenomination;
  }

  public boolean isLessThanOrEqualTo(double anotherDenomination)  {
    return this.denomination <= anotherDenomination;
  }

  @Override
  public String toString() {
    return format("£%.2f", denomination);
  }
}
