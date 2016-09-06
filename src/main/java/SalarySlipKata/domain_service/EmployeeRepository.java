package SalarySlipKata.domain_service;

import java.util.HashMap;
import java.util.Map;

import SalarySlipKata.domain.Employee;
import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;

public class EmployeeRepository {
  private final Map<EmployeeId, Employee> employees = new HashMap<>();

  public String getNameFor(EmployeeId employeeId) {
    return employees
        .get(employeeId)
        .getName();
  }

  public GBP getAnnualSalaryFor(EmployeeId employeeId) {
    return employees
        .get(employeeId)
        .getAnnualSalary();
  }

  public void addEmployee(EmployeeId employeeId, String name, GBP annualSalary) {
    employees.put(employeeId, new Employee(employeeId, name, annualSalary));
  }
}
