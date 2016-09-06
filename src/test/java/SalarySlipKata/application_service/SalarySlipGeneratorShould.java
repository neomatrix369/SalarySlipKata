package SalarySlipKata.application_service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static java.time.LocalDate.parse;

import org.junit.Before;
import org.junit.Test;

import SalarySlipKata.domain.Bonus;
import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;
import SalarySlipKata.domain.Loan;
import SalarySlipKata.domain.Overtime;
import SalarySlipKata.domain_service.EmployeeRepository;
import SalarySlipKata.domain_service.NationalInsuranceCalculatorService;
import SalarySlipKata.domain_service.SalaryRepository;
import SalarySlipKata.domain_service.SalaryService;
import SalarySlipKata.domain_service.TaxCalculatorService;
import SalarySlipKata.infrastructure.Clock;
import SalarySlipKata.infrastructure.Console;
import SalarySlipKata.infrastructure.StandardSalarySlipPrinter;

public class SalarySlipGeneratorShould {
  private static final EmployeeId EMPLOYEE_ID_12345 = new EmployeeId(12345);

  private Console console;
  private Clock clock;

  private SalaryService salaryService;

  private EmployeeRepository employeeRepository;
  private SalaryRepository salaryRepository;

  private StandardSalarySlipPrinter standardSalarySlipPrinter;

  private NationalInsuranceCalculatorService nationalInsuranceCalculatorService;
  private TaxCalculatorService taxCalculatorService;

  private SalarySlipGenerator salarySlipGenerator;

  @Before
  public void initialise() {
    console = mock(Console.class);
    clock = mock(Clock.class);

    employeeRepository = new EmployeeRepository();
    salaryRepository = new SalaryRepository();

    nationalInsuranceCalculatorService = new NationalInsuranceCalculatorService();
    taxCalculatorService = new TaxCalculatorService();
    salaryService =
        new SalaryService(employeeRepository, salaryRepository, nationalInsuranceCalculatorService, taxCalculatorService);

    standardSalarySlipPrinter = new StandardSalarySlipPrinter(console, clock, salaryService);

    salarySlipGenerator = new SalarySlipGenerator(salaryService, standardSalarySlipPrinter);
  }


  @Test public void
  display_a_salary_slip_with_employee_and_salary_details_bonus_overtime_load_taxes_and_NI() {
    when(clock.getCurrentDate()).thenReturn(parse("2016-09-01"));
    salaryService.addEmployee(EMPLOYEE_ID_12345, "John J Doe", new GBP(24000));

    salarySlipGenerator.addEarning(EMPLOYEE_ID_12345, new Bonus(new GBP(1000), clock.getCurrentDate()));
    salarySlipGenerator.addEarning(EMPLOYEE_ID_12345, new Overtime(new GBP(500), clock.getCurrentDate()));
    salarySlipGenerator.addDeductionFor(EMPLOYEE_ID_12345, new Loan(new GBP(200), clock.getCurrentDate()));

    salarySlipGenerator.printFor(EMPLOYEE_ID_12345);

    verify(console).print(
        "Date: 01 Sep 2016            Salary for period: Sep 2016\n" +
        "                                                        \n" +
        "Employee ID: 12345           Employee Name: John J Doe  \n" +
        "                                                        \n" +
        "SALARY                       DEDUCTION                     \n" +
        "Basic           £2000.00     Loan                   £200.00\n" +
        "Bonus           £1000.00     National Insurance     £315.40\n" +
        "Overtime         £500.00     Tax                    £476.67\n" +
        "                                                        \n" +
        "Gross salary    £3300.00     Net payable           £2507.93"
    );
  } 
}
