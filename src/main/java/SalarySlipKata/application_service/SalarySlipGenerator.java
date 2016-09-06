package SalarySlipKata.application_service;

import SalarySlipKata.domain.Bonus;
import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.Overtime;
import SalarySlipKata.domain.SalaryItem;
import SalarySlipKata.domain_service.SalaryService;
import SalarySlipKata.infrastructure.StandardSalarySlipPrinter;

public class SalarySlipGenerator {
  private final SalaryService salaryService;
  private final StandardSalarySlipPrinter standardSalarySlipPrinter;

  public SalarySlipGenerator(SalaryService salaryService, StandardSalarySlipPrinter standardSalarySlipPrinter) {
    this.salaryService = salaryService;
    this.standardSalarySlipPrinter = standardSalarySlipPrinter;
  }

  public void addEarning(EmployeeId employeeId, SalaryItem earning) {
    if (earning instanceof Bonus) {
      salaryService.addBonusFor(employeeId, earning);
    } else if (earning instanceof Overtime) {
      salaryService.addOvertimeFor(employeeId, earning);
    }
  }

  public void addDeductionFor(EmployeeId employeeId, SalaryItem deduction) {
    salaryService.addLoanFor(employeeId, deduction);
  }

  public void printFor(EmployeeId employeeId) {
    standardSalarySlipPrinter.printSalaryFor(employeeId);
  }
}
