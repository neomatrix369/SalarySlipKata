package SalarySlipKata;

import java.time.LocalDate;

import SalarySlipKata.domain.GBP;
import SalarySlipKata.infrastructure.Console;

public class SalarySlipService {

  private Console console;

  public SalarySlipService(Console console) {
    this.console = console;
  }

  public void addBaseSalaryFor(EmployeeId employeeId, GBP amount, LocalDate date) {

  }

  public void printSalaryFor(EmployeeId employeeId) {

  }
}
