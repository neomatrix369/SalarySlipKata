package SalarySlipKata.infrastructure;

import static java.lang.String.*;
import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.LocalDate;

import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.donain_service.SalaryService;

public class SalarySlipPrinter {
  private final Console console;
  private final Clock clock;
  private final SalaryService salaryService;

  public SalarySlipPrinter(Console console, Clock clock, SalaryService salaryService) {
    this.console = console;
    this.clock = clock;
    this.salaryService = salaryService;
  }

  public void printSalaryFor(EmployeeId employeeId) {
    String currentDate =
        getFormattedDate("dd MMM yyyy", clock.getCurrentDate());
    String salaryPeriod =
        getFormattedDate("MMM yyyy", clock.getCurrentDate());

    console.print(
        format("Date: %s            Salary for period: %s%n" +
               "                                                        %n" +
               "Employee ID: %s                                      %n" +
               "                                                        %n" +
               "SALARY                    DEDUCTION                     %n" +
               "Basic           %s  National Insurance     %s%n" +
               "                          Tax                    %s%n" +
               "                                                        %n" +
               "                                                        %n" +
               "Grand total     %s  Net payable           %s",
            currentDate,
            salaryPeriod,
            employeeId,
            salaryService.getBasicSalaryFor(employeeId),
            salaryService.getNationalInsuranceFor(employeeId),
            salaryService.getTax(employeeId),
            salaryService.getGrandTotal(employeeId),
            salaryService.getNetPayable(employeeId)
        )
    );
  }

  private String getFormattedDate(String pattern, LocalDate date) {
    return date.format(ofPattern(pattern));
  }
}
