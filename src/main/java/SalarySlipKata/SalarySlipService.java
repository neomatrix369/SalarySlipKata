package SalarySlipKata;

import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import SalarySlipKata.domain.GBP;
import SalarySlipKata.infrastructure.Console;
import SalarySlipKata.infrastructure.SalaryService;

public class SalarySlipService {

  private Console console;
  private SalaryService salaryRepository = new SalaryService();

  public SalarySlipService(Console console) {
    this.console = console;
  }

  public void addBasicSalaryFor(EmployeeId employeeId, GBP amount, LocalDate date) {
    salaryRepository.addBasicSalaryFor(employeeId, amount, date);
  }

  public void printSalaryFor(EmployeeId employeeId) {
    String currentDate = getFormattedDate("dd MMM yyyy", LocalDate.now());
    String salaryPeriod = getFormattedDate("MMM yyyy", LocalDate.now());

    console.print(format("Date: %s            Salary for period: %s%n", currentDate, salaryPeriod));
    console.print(format("%n"));
    console.print(format("Employee ID: %s%n", employeeId));
    console.print(format("SALARY                    DEDUCTION%n"));
    console.print(format("Basic           %s  National Insurance     %s%n",
        salaryRepository.getBasicSalaryFor(employeeId),
        salaryRepository.getNationalInsuranceFor(employeeId)));
  }

  private String getFormattedDate(String pattern, LocalDate date) {
    DateTimeFormatter salaryPeriodFormatter = ofPattern(pattern);
    return date.format(salaryPeriodFormatter);
  }
}
