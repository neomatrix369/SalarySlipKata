package SalarySlipKata;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static java.time.LocalDate.parse;

import org.junit.Test;

import SalarySlipKata.domain.GBP;
import SalarySlipKata.infrastructure.Console;

public class SalarySlipServiceShould {
  private static final EmployeeId EMPLOYEE_ID_12345 = new EmployeeId(12345);

  @Test public void
  display_a_simple_salary_slip_for_an_employee_receiving_just_base_salary() {
    Console console = mock(Console.class);
    SalarySlipService salarySlipService = new SalarySlipService(console);
    salarySlipService.addBaseSalaryFor(EMPLOYEE_ID_12345, new GBP(2000.00), parse("2016-09-01"));

    salarySlipService.printSalaryFor(EMPLOYEE_ID_12345);

    verify(console).print("Date: 01 September 2016   Salary for period: Sept. 2016\n");
    verify(console).print("         \n");
    verify(console).print("         Employee ID: 12345\n");
    verify(console).print("         \n");
    verify(console).print("         SALARY                    DEDUCTION\n");
    verify(console).print("         Basic           £2000.00  National Insurance     £159.40\n");
    verify(console).print("                                   Tax                    £216.67\n");
    verify(console).print("\n");
    verify(console).print("         \n");
    verify(console).print("         Grand total     £2000.00  Net payable           £1623.93");
  } 
}
