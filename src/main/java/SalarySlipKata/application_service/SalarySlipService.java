package SalarySlipKata.application_service;

import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.LocalDate;

import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;
import SalarySlipKata.infrastructure.Clock;
import SalarySlipKata.infrastructure.Console;
import SalarySlipKata.donain_service.SalaryService;

public class SalarySlipService {

  private Console console;
  private Clock clock;
  private SalaryService salaryService = new SalaryService();

  public SalarySlipService(Console console, Clock clock) {
    this.console = console;
    this.clock = clock;
  }

  public void addBasicSalaryFor(EmployeeId employeeId, GBP amount, LocalDate date) {
    salaryService.addBasicSalaryFor(employeeId, amount, date);
  }

  public void printSalaryFor(EmployeeId employeeId) {
    String currentDate = getFormattedDate("dd MMM yyyy", clock.getCurrentDate());
    String salaryPeriod = getFormattedDate("MMM yyyy", clock.getCurrentDate());

    console.print(format("Date: %s            Salary for period: %s%n", currentDate, salaryPeriod));
    console.print(format("%n"));
    console.print(format("Employee ID: %s%n", employeeId));
    console.print(format("SALARY                    DEDUCTION%n"));
    console.print(format("Basic           %s  National Insurance     %s%n",
        salaryService.getBasicSalaryFor(employeeId),
        salaryService.getNationalInsuranceFor(employeeId))
    );
    console.print(format("                          Tax                    %s%n",
        salaryService.getTax(employeeId)));
    console.print(format("Grand total     %s  Net payable           %s",
        salaryService.getGrandTotal(employeeId),
        salaryService.getNetPayable(employeeId))
    );
  }

  private String getFormattedDate(String pattern, LocalDate date) {
    return date.format(ofPattern(pattern));
  }
}
