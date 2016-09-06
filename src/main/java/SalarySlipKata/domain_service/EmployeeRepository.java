package SalarySlipKata.domain_service;

import java.util.HashMap;
import java.util.Map;

import SalarySlipKata.domain.Employee;
import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;

public class EmployeeRepository {
  private final Map<EmployeeId, Employee> employees = new HashMap<>();

  public String getNameFor(EmployeeId employeeId) {
    final Employee employee = employees.get(employeeId);
    return employee.getName();
  }

  public GBP getAnnualSalaryFor(EmployeeId employeeId) {
    final Employee employee = employees.get(employeeId);
    return employee.getAnnualSalary();
  }

  public void addEmployee(EmployeeId employeeId, String name, GBP annualSalary) {
    employees.put(employeeId, new Employee(employeeId, name, annualSalary));
  }
}
