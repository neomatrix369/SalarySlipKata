package SalarySlipKata.domain_service;

import SalarySlipKata.domain.GBP;

public class TaxCalculatorService {
  private static final int TWELVE_MONTHS = 12;
  private static final double TWENTY_PERCENT = 0.20;

  private static final GBP MONTHLY_PERSONAL_ALLOWANCE = monthly(new GBP(11000.00));
  private static final GBP TWENTY_PERCENT_TAX_MINIMUM_BRACKET = monthly(new GBP(11001.00));
  private static final GBP TWENTY_PERCENT_TAX_MAXIMUM_BRACKET = monthly(new GBP(43000.00));

  private static GBP monthly(GBP amount) {return amount.dividedBy(TWELVE_MONTHS);}

  public GBP calculate(GBP grossMonthlySalary) {
    final GBP monthlyTaxableSalary = grossMonthlySalary.minus(MONTHLY_PERSONAL_ALLOWANCE);

    if (monthlyTaxableSalary.isGreaterThanOrEqualTo(TWENTY_PERCENT_TAX_MINIMUM_BRACKET) &&
        monthlyTaxableSalary.isLessThanOrEqualTo(TWENTY_PERCENT_TAX_MAXIMUM_BRACKET)) {
      return monthlyTaxableSalary.multiplyBy(TWENTY_PERCENT);
    }

    return new GBP(0.00);
  }
}
