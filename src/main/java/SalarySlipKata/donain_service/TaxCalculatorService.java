package SalarySlipKata.donain_service;

import SalarySlipKata.domain.GBP;

public class TaxCalculatorService {
  private static final double ANNUAL_PERSONAL_ALLOWANCE = 11000.00;
  private static final double TWENTY_PERCENT_TAX_MINIMUM_BRACKET = 11001.00;
  private static final double TWENTY_PERCENT_TAX_MAXIMUM_BRACKET = 43000.00;

  private static final double TWENTY_PERCENT = 0.20;

  private static final int TWELVE_MONTHS = 12;

  public GBP calculate(GBP basicSalary) {
    final GBP basicAnnualSalaryAmount = basicSalary.multiplyBy(TWELVE_MONTHS);

    final GBP annualTaxableSalary = basicAnnualSalaryAmount.minus(ANNUAL_PERSONAL_ALLOWANCE);
    if (annualTaxableSalary.isGreaterThanOrEqualTo(TWENTY_PERCENT_TAX_MINIMUM_BRACKET) &&
        annualTaxableSalary.isLessThanOrEqualTo(TWENTY_PERCENT_TAX_MAXIMUM_BRACKET)) {
      return annualTaxableSalary.dividedBy(TWELVE_MONTHS).multiplyBy(TWENTY_PERCENT);
    }

    return new GBP(0.00);
  }
}
