package SalarySlipKata;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static java.time.LocalDate.parse;

import org.junit.Before;
import org.junit.Test;

import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;
import SalarySlipKata.donain_service.EmployeeSalaryRepository;
import SalarySlipKata.donain_service.SalaryService;
import SalarySlipKata.infrastructure.Clock;
import SalarySlipKata.infrastructure.Console;
import SalarySlipKata.infrastructure.StandardSalarySlipPrinter;

public class SalarySlipPrinterShould {
  private static final EmployeeId EMPLOYEE_ID_12345 = new EmployeeId(12345);

  private Console console;
  private Clock clock;

  private SalaryService salaryService;
  private EmployeeSalaryRepository employeeSalaryRepository;
  private StandardSalarySlipPrinter standardSalarySlipPrinter;

  @Before
  public void initialise() {
    console = mock(Console.class);
    clock = mock(Clock.class);

    employeeSalaryRepository = new EmployeeSalaryRepository();

    salaryService = new SalaryService(employeeSalaryRepository);
    standardSalarySlipPrinter = new StandardSalarySlipPrinter(console, clock, salaryService);
  }

  @Test public void
  display_a_standard_salary_slip_for_an_employee_receiving_just_basic_salary() {
    when(clock.getCurrentDate()).thenReturn(parse("2016-09-01"));
    salaryService.addBasicSalaryFor(EMPLOYEE_ID_12345, new GBP(2000.00), parse("2016-09-01"));

    standardSalarySlipPrinter.printSalaryFor(EMPLOYEE_ID_12345);

    verify(console).print(
        "Date: 01 Sep 2016            Salary for period: Sep 2016\n" +
        "                                                        \n" +
        "Employee ID: 12345                                      \n" +
        "                                                        \n" +
        "SALARY                    DEDUCTION                     \n" +
        "Basic           £2000.00  National Insurance     £159.40\n" +
        "                          Tax                    £216.67\n" +
        "                                                        \n" +
        "                                                        \n" +
        "Grand total     £2000.00  Net payable           £1623.93"
    );
  } 
}
