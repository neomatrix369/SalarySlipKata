package SalarySlipKata.infrastructure;

import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.LocalDate;

import SalarySlipKata.domain.Bonus;
import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;
import SalarySlipKata.domain.Loan;
import SalarySlipKata.domain.Overtime;
import SalarySlipKata.domain_service.SalaryService;

public class StandardSalarySlipPrinter {
  private static final int FIXED_LENGTH_FOR_AMOUNT = 8;

  private static final String CURRENT_DATE_FORMAT = "dd MMM yyyy";
  private static final String SALARY_PERIOD_FORMAT = "MMM yyyy";

  private static final String STANDARD_PAY_SLIP_FORMAT =
      "Date: %s            Salary for period: %s\n" +
      "                                                        \n" +
      "Employee ID: %s           Employee Name: %s  \n" +
      "                                                        \n" +
      "SALARY                       DEDUCTION                     \n" +
      "Basic           %s     Loan                  %s\n" +
      "Bonus           %s     National Insurance    %s\n" +
      "Overtime        %s     Tax                   %s\n" +
      "                                                        \n" +
      "Gross salary    %s     Net payable           %s";

  private final Console console;
  private final Clock clock;
  private final SalaryService salaryService;

  public StandardSalarySlipPrinter(Console console, Clock clock, SalaryService salaryService) {
    this.console = console;
    this.clock = clock;
    this.salaryService = salaryService;
  }

  public void printSalaryFor(EmployeeId employeeId, LocalDate date) {
    String currentDate =
        getFormattedDate(CURRENT_DATE_FORMAT, clock.getCurrentDate());
    String salaryPeriod =
        getFormattedDate(SALARY_PERIOD_FORMAT, date);

    String employeeName = salaryService.getNameFor(employeeId);
    console.print(
        format(STANDARD_PAY_SLIP_FORMAT,
            currentDate,
            salaryPeriod,
            rightPadWithSpaces(employeeId.toString(), 5),
            rightPadWithSpaces(employeeName, 10),
            leftPadWithSpaces(salaryService.getBasicSalaryFor(employeeId), FIXED_LENGTH_FOR_AMOUNT),
            leftPadWithSpaces(salaryService.getSalaryItem(employeeId, Loan.class, date), FIXED_LENGTH_FOR_AMOUNT),
            leftPadWithSpaces(salaryService.getSalaryItem(employeeId, Bonus.class, date), FIXED_LENGTH_FOR_AMOUNT),
            leftPadWithSpaces(salaryService.getNationalInsuranceFor(employeeId, date), FIXED_LENGTH_FOR_AMOUNT),
            leftPadWithSpaces(salaryService.getSalaryItem(employeeId, Overtime.class, date), FIXED_LENGTH_FOR_AMOUNT),
            leftPadWithSpaces(salaryService.getTax(employeeId, date), FIXED_LENGTH_FOR_AMOUNT),
            leftPadWithSpaces(salaryService.getGrossSalary(employeeId, date), FIXED_LENGTH_FOR_AMOUNT),
            leftPadWithSpaces(salaryService.getNetPayable(employeeId, date), FIXED_LENGTH_FOR_AMOUNT)
        )
    );
  }

  private String rightPadWithSpaces(String value, int totalLength) {
    return format("%0$-" + totalLength + "s", value);
  }

  private String leftPadWithSpaces(GBP amount, int totalLength) {
    return format("%1$" + totalLength + "s", amount.toString());
  }

  private String getFormattedDate(String pattern, LocalDate date) {
    return date.format(ofPattern(pattern));
  }
}
