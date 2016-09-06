package SalarySlipKata.domain_service;

import SalarySlipKata.domain.GBP;

public class NationalInsuranceCalculatorService {
  private static final int TWELVE_MONTHS = 12;

  private static final GBP MONTH_NATIONAL_INSURANCE_THRESHOLD = new GBP(8060.00).dividedBy(TWELVE_MONTHS);

  private static final double TWELVE_PERCENT = 0.12;

  public GBP calculate(GBP basicSalary) {
    GBP differenceFromThreshold = basicSalary.differenceFrom(MONTH_NATIONAL_INSURANCE_THRESHOLD);

    if (differenceFromThreshold.isGreaterThanZero()) {
      return differenceFromThreshold.multiplyBy(TWELVE_PERCENT);
    }

    return new GBP(0.0);
  }
}
