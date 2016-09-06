package SalarySlipKata.domain;

public class Employee {
  private EmployeeId employeeId;
  private final String name;
  private final GBP annualSalary;

  public Employee(EmployeeId employeeId, String name, GBP annualSalary) {
    this.employeeId = employeeId;
    this.name = name;
    this.annualSalary = annualSalary;
  }

  public String getName() {
    return name;
  }
}
