package SalarySlipKata.donain_service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import SalarySlipKata.domain.BasicSalary;
import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;
import SalarySlipKata.domain.SalaryItem;

public class EmployeeSalaryRepository {
  final Map<EmployeeId, SalaryItem> salaries = new HashMap<>();

  public EmployeeSalaryRepository() { }

  public void addBasicSalaryFor(EmployeeId employeeId, GBP amount, LocalDate date) {
    salaries.put(employeeId, new BasicSalary(amount, date));
  }

  public GBP getBasicSalaryFor(EmployeeId employeeId) {
    final SalaryItem salaryItem = salaries.get(employeeId);
    return salaryItem.getAmount();
  }
}
