package SalarySlipKata.application_service;

import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.donain_service.SalaryService;
import SalarySlipKata.infrastructure.Clock;
import SalarySlipKata.infrastructure.Console;
import SalarySlipKata.infrastructure.SalarySlipPrinter;

public class SalarySlipService {

  private final SalarySlipPrinter salarySlipPrinter;

  public SalarySlipService(Console console, Clock clock, SalaryService salaryService) {
    salarySlipPrinter = new SalarySlipPrinter(console, clock, salaryService);
  }

  public void printSalaryFor(EmployeeId employeeId) {
    salarySlipPrinter.printSalaryFor(employeeId);
  }
}
