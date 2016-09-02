package SalarySlipKata.application_service;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.LocalDate;

import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.donain_service.SalaryService;
import SalarySlipKata.infrastructure.Clock;
import SalarySlipKata.infrastructure.Console;

public class SimpleSalarySlipPrinter {
  private final Console console;
  private final Clock clock;
  private final SalaryService salaryService;

  public SimpleSalarySlipPrinter(Console console, Clock clock, SalaryService salaryService) {
    this.console = console;
    this.clock = clock;
    this.salaryService = salaryService;
  }

  public void printSalaryFor(EmployeeId employeeId) {
    String currentDate =
        getFormattedDate("dd MMM yyyy", clock.getCurrentDate());
    String salaryPeriod =
        getFormattedDate("MMM yyyy", clock.getCurrentDate());

    console.print(String.format("Date: %s            Salary for period: %s%n", currentDate, salaryPeriod));
    console.print(String.format("%n"));
    console.print(String.format("Employee ID: %s%n", employeeId));
    console.print(String.format("SALARY                    DEDUCTION%n"));
    console.print(String.format("Basic           %s  National Insurance     %s%n",
        salaryService.getBasicSalaryFor(employeeId),
        salaryService.getNationalInsuranceFor(employeeId))
    );
    console.print(String.format("                          Tax                    %s%n",
        salaryService.getTax(employeeId)));
    console.print(String.format("Grand total     %s  Net payable           %s",
        salaryService.getGrandTotal(employeeId),
        salaryService.getNetPayable(employeeId))
    );
  }

  private String getFormattedDate(String pattern, LocalDate date) {
    return date.format(ofPattern(pattern));
  }
}
