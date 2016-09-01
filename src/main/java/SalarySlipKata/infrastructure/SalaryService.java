package SalarySlipKata.infrastructure;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import SalarySlipKata.EmployeeId;
import SalarySlipKata.domain.GBP;
import SalarySlipKata.domain.BasicSalary;
import SalarySlipKata.domain.SalaryItem;

public class SalaryService {
  private static final double NATIONAL_INSURANCE_THRESHOLD = 8060.00;
  private static final int TWELVE_MONTHS = 12;
  private static final double TWELVE_PERCENT = 0.12;

  private Map<EmployeeId, SalaryItem> salaries = new HashMap<>();

  public GBP getBasicSalaryFor(EmployeeId employeeId) {
    final SalaryItem salaryItem = salaries.get(employeeId);
    return salaryItem.getAmount();
  }

  public GBP getNationalInsuranceFor(EmployeeId employeeId) {
    return calculateNationalInsuranceFor(getBasicSalaryFor(employeeId));
  }

  private GBP calculateNationalInsuranceFor(GBP basicSalaryAmount) {
    double nationalInsuranceThresholdPerMonth = NATIONAL_INSURANCE_THRESHOLD / TWELVE_MONTHS;
    GBP differenceFromThreshold = basicSalaryAmount.differenceFrom(nationalInsuranceThresholdPerMonth);
    if (differenceFromThreshold.isGreaterThanZero()) {
      return differenceFromThreshold.multiplyBy(TWELVE_PERCENT);
    }

    return new GBP(0.0);
  }

  public void addBasicSalaryFor(EmployeeId employeeId, GBP amount, LocalDate date) {
    salaries.put(employeeId, new BasicSalary(amount, date));
  }
}
