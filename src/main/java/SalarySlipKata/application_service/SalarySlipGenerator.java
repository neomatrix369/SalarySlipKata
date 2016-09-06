package SalarySlipKata.application_service;

import java.time.LocalDate;

import SalarySlipKata.domain.EmployeeId;
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
    salaryService.addEarningFor(employeeId, earning);
  }

  public void addDeductionFor(EmployeeId employeeId, SalaryItem deduction) {
    salaryService.addDeductionFor(employeeId, deduction);
  }

  public void printFor(EmployeeId employeeId, LocalDate date) {
    standardSalarySlipPrinter.printSalaryFor(employeeId, date);
  }
}
