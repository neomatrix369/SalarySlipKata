package SalarySlipKata.donain_service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;
import SalarySlipKata.domain.BasicSalary;
import SalarySlipKata.domain.SalaryItem;

public class SalaryService {
  private static final double ANNUAL_NATIONAL_INSURANCE_THRESHOLD = 8060.00;
  private static final double ANNUAL_PERSONAL_ALLOWANCE = 11000.00;

  private static final double TWENTY_PERCENT_TAX_MINIMUM_BRACKET = 11001.00;
  private static final double TWENTY_PERCENT_TAX_MAXIMUM_BRACKET = 43000.00;

  private static final int TWELVE_MONTHS = 12;
  private static final double TWELVE_PERCENT = 0.12;
  private static final double TWENTY_PERCENT = 0.20;

  private Map<EmployeeId, SalaryItem> salaries = new HashMap<>();

  public void addBasicSalaryFor(EmployeeId employeeId, GBP amount, LocalDate date) {
    salaries.put(employeeId, new BasicSalary(amount, date));
  }

  public GBP getBasicSalaryFor(EmployeeId employeeId) {
    final SalaryItem salaryItem = salaries.get(employeeId);
    return salaryItem.getAmount();
  }

  public GBP getNationalInsuranceFor(EmployeeId employeeId) {
    return calculateNationalInsuranceFor(getBasicSalaryFor(employeeId));
  }

  private GBP calculateNationalInsuranceFor(GBP basicSalaryAmount) {
    double nationalInsuranceThresholdPerMonth = ANNUAL_NATIONAL_INSURANCE_THRESHOLD / TWELVE_MONTHS;
    GBP differenceFromThreshold = basicSalaryAmount.differenceFrom(nationalInsuranceThresholdPerMonth);
    if (differenceFromThreshold.isGreaterThanZero()) {
      return differenceFromThreshold.multiplyBy(TWELVE_PERCENT);
    }

    return new GBP(0.0);
  }

  public GBP getTax(EmployeeId employeeId) {
    return calculateTaxFor(getBasicSalaryFor(employeeId));
  }

  private GBP calculateTaxFor(GBP basicSalaryAmount) {
    GBP basicAnnualSalaryAmount = basicSalaryAmount.multiplyBy(TWELVE_MONTHS);
    GBP annualTaxableSalary = basicAnnualSalaryAmount.minus(ANNUAL_PERSONAL_ALLOWANCE);
    if (annualTaxableSalary.isGreaterThanOrEqualTo(TWENTY_PERCENT_TAX_MINIMUM_BRACKET) &&
        annualTaxableSalary.isLessThanOrEqualTo(TWENTY_PERCENT_TAX_MAXIMUM_BRACKET)) {
        return annualTaxableSalary.dividedBy(TWELVE_MONTHS).multiplyBy(TWENTY_PERCENT);
    }
    return new GBP(0.00);
  }
}
