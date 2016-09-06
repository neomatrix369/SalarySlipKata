package SalarySlipKata.domain_service;

import SalarySlipKata.domain.GBP;

public class NationalInsuranceCalculatorService {
  private static final double ANNUAL_NATIONAL_INSURANCE_THRESHOLD = 8060.00;

  private static final int TWELVE_MONTHS = 12;
  private static final double TWELVE_PERCENT = 0.12;

  public GBP calculate(GBP basicSalary) {
    double nationalInsuranceThresholdPerMonth = ANNUAL_NATIONAL_INSURANCE_THRESHOLD / TWELVE_MONTHS;
    GBP differenceFromThreshold = basicSalary.differenceFrom(nationalInsuranceThresholdPerMonth);

    if (differenceFromThreshold.isGreaterThanZero()) {
      return differenceFromThreshold.multiplyBy(TWELVE_PERCENT);
    }

    return new GBP(0.0);
  }
}
