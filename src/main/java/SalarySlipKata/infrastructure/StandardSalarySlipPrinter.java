package SalarySlipKata.infrastructure;

import static java.lang.String.*;
import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.LocalDate;

import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.donain_service.SalaryService;

public class StandardSalarySlipPrinter {
  private static final String CURRENT_DATE_FORMAT = "dd MMM yyyy";
  private static final String SALARY_PERIOD_FORMAT = "MMM yyyy";

  private static final String STANDARD_PAY_SLIP_FORMAT =
      "Date: %s            Salary for period: %s%n" +
      "                                                        %n" +
      "Employee ID: %s                                      %n" +
      "                                                        %n" +
      "SALARY                    DEDUCTION                     %n" +
      "Basic           %s  National Insurance     %s%n" +
      "Bonus           %s  Tax                    %s%n" +
      "                                                        %n" +
      "                                                        %n" +
      "Grand total     %s  Net payable           %s";

  private final Console console;
  private final Clock clock;
  private final SalaryService salaryService;

  public StandardSalarySlipPrinter(Console console, Clock clock, SalaryService salaryService) {
    this.console = console;
    this.clock = clock;
    this.salaryService = salaryService;
  }

  public void printSalaryFor(EmployeeId employeeId) {
    String currentDate =
        getFormattedDate(CURRENT_DATE_FORMAT, clock.getCurrentDate());
    String salaryPeriod =
        getFormattedDate(SALARY_PERIOD_FORMAT, clock.getCurrentDate());

    console.print(
        format(STANDARD_PAY_SLIP_FORMAT,
            currentDate,
            salaryPeriod,
            employeeId,
            salaryService.getBasicSalaryFor(employeeId),
            salaryService.getNationalInsuranceFor(employeeId),
            salaryService.getBonus(employeeId),
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
