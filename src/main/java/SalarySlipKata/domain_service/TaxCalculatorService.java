package SalarySlipKata.domain_service;

import SalarySlipKata.domain.GBP;

public class TaxCalculatorService {
  private static final int TWELVE_MONTHS = 12;
  private static GBP MONTHLY(GBP amount) {return amount.dividedBy(TWELVE_MONTHS);}

  private static final GBP MONTHLY_PERSONAL_ALLOWANCE = MONTHLY(new GBP(11000.00));
  private static final GBP TWENTY_PERCENT_TAX_MINIMUM_BRACKET = MONTHLY(new GBP(11001.00));
  private static final GBP TWENTY_PERCENT_TAX_MAXIMUM_BRACKET = MONTHLY(new GBP(43000.00));

  private static final double TWENTY_PERCENT = 0.20;

  public GBP calculate(GBP grossMonthlySalary) {
    final GBP monthlyTaxableSalary = grossMonthlySalary.minus(MONTHLY_PERSONAL_ALLOWANCE);

    if (monthlyTaxableSalary.isGreaterThanOrEqualTo(TWENTY_PERCENT_TAX_MINIMUM_BRACKET) &&
        monthlyTaxableSalary.isLessThanOrEqualTo(TWENTY_PERCENT_TAX_MAXIMUM_BRACKET)) {
      return monthlyTaxableSalary.multiplyBy(TWENTY_PERCENT);
    }

    return new GBP(0.00);
  }
}
