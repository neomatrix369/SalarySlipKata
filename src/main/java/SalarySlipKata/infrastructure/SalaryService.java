package SalarySlipKata.infrastructure;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import SalarySlipKata.EmployeeId;
import SalarySlipKata.domain.GBP;
import SalarySlipKata.domain.BasicSalary;
import SalarySlipKata.domain.SalaryItem;

public class SalaryService {
  private Map<EmployeeId, SalaryItem> salaries = new HashMap<>();

  public GBP getBasicSalaryFor(EmployeeId employeeId) {
    final SalaryItem salaryItem = salaries.get(employeeId);
    return salaryItem.getAmount();
  }

  public GBP getNationalInsuranceFor(EmployeeId employeeId) {
    return new GBP(0.00);
  }

  public void addBasicSalaryFor(EmployeeId employeeId, GBP amount, LocalDate date) {
    salaries.put(employeeId, new BasicSalary(amount, date));
  }
}
