package SalarySlipKata.application_service;

import java.time.LocalDate;

import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;
import SalarySlipKata.donain_service.SalaryService;
import SalarySlipKata.infrastructure.Clock;
import SalarySlipKata.infrastructure.Console;

public class SalarySlipService {

  private final SimpleSalarySlipPrinter simpleSalarySlipPrinter;

  private SalaryService salaryService;

  public SalarySlipService(Console console, Clock clock, SalaryService salaryService) {
    this.salaryService = salaryService;
    simpleSalarySlipPrinter = new SimpleSalarySlipPrinter(console, clock, salaryService);
  }

  public void addBasicSalaryFor(EmployeeId employeeId, GBP amount, LocalDate date) {
    salaryService.addBasicSalaryFor(employeeId, amount, date);
  }

  public void printSalaryFor(EmployeeId employeeId) {
    simpleSalarySlipPrinter.printSalaryFor(employeeId);
  }
}
