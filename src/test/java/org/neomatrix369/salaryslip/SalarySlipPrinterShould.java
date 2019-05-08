package org.neomatrix369.salaryslip;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static java.time.LocalDate.parse;

import org.junit.Before;
import org.junit.Test;

import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;
import SalarySlipKata.donain_service.EmployeeSalaryRepository;
import SalarySlipKata.donain_service.NationalInsuranceCalculatorService;
import SalarySlipKata.donain_service.SalaryService;
import SalarySlipKata.donain_service.TaxCalculatorService;
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

  private NationalInsuranceCalculatorService nationalInsuranceCalculatorService;
  private TaxCalculatorService taxCalculatorService;

  @Before
  public void initialise() {
    console = mock(Console.class);
    clock = mock(Clock.class);

    employeeSalaryRepository = new EmployeeSalaryRepository();

    nationalInsuranceCalculatorService = new NationalInsuranceCalculatorService();
    taxCalculatorService = new TaxCalculatorService();
    salaryService =
        new SalaryService(employeeSalaryRepository, nationalInsuranceCalculatorService, taxCalculatorService);

    standardSalarySlipPrinter = new StandardSalarySlipPrinter(console, clock, salaryService);
  }

  @Test public void
  display_a_standard_salary_slip_for_an_employee_receiving_just_basic_salary() {
    when(clock.getCurrentDate()).thenReturn(parse("2016-09-01"));
    salaryService.addBasicSalaryFor(EMPLOYEE_ID_12345, new GBP(2000.00), parse("2016-09-01"));
    salaryService.addBonusFor(EMPLOYEE_ID_12345, new GBP(1000.00), parse("2016-09-01"));
    salaryService.addOvertimeFor(EMPLOYEE_ID_12345, new GBP(500.00), parse("2016-09-01"));
    salaryService.addLoanFor(EMPLOYEE_ID_12345, new GBP(200.00), parse("2016-09-01"));

    standardSalarySlipPrinter.printSalaryFor(EMPLOYEE_ID_12345);

    verify(console).print(
        "Date: 01 Sep 2016            Salary for period: Sep 2016\n" +
        "                                                        \n" +
        "Employee ID: 12345                                      \n" +
        "                                                        \n" +
        "SALARY                    DEDUCTION                     \n" +
        "Basic           £2000.00  Loan                   £200.00\n" +
        "Bonus           £1000.00  National Insurance     £315.40\n" +
        "Overtime         £500.00  Tax                    £476.67\n" +
        "                                                        \n" +
        "Gross salary    £3300.00  Net payable           £2507.93"
    );
  } 
}
