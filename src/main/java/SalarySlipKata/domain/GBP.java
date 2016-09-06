package SalarySlipKata.domain;

import static java.lang.String.format;

public class GBP {
  private final double denomination;

  public GBP(double anotherDenomination) {
    this.denomination = anotherDenomination;
  }

  public static final GBP zero() {
    return new GBP(0.0);
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

  public GBP differenceFrom(GBP anotherDenomination) {
    return new GBP(this.denomination - anotherDenomination.denomination);
  }

  public GBP plus(GBP amount) {
    return new GBP(this.denomination + amount.denomination);
  }

  public GBP minus(GBP amount) {
    return new GBP(this.denomination - amount.denomination);
  }

  public boolean isGreaterThanZero() {
    return this.denomination > 0.0;
  }

  public boolean isGreaterThanOrEqualTo(GBP anotherDenomination) {
    return this.denomination >= anotherDenomination.denomination;
  }

  public boolean isLessThanOrEqualTo(GBP anotherDenomination)  {
    return this.denomination <= anotherDenomination.denomination;
  }

  @Override
  public String toString() {
    return format("£%.2f", denomination);
  }
}
