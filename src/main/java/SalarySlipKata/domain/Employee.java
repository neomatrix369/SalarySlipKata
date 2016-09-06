package SalarySlipKata.domain;

public class Employee {
  private final String name;
  private final GBP annualSalary;

  public Employee(String name, GBP annualSalary) {
    this.name = name;
    this.annualSalary = annualSalary;
  }

  public String getName() {
    return name;
  }
}
