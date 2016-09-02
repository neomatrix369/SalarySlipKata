package SalarySlipKata.donain_service;

import java.time.LocalDate;

import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;

public class SalaryService {
  private static final double ANNUAL_PERSONAL_ALLOWANCE = 11000.00;

  private static final double TWENTY_PERCENT_TAX_MINIMUM_BRACKET = 11001.00;
  private static final double TWENTY_PERCENT_TAX_MAXIMUM_BRACKET = 43000.00;

  private static final double TWENTY_PERCENT = 0.20;
  private static final int TWELVE_MONTHS = 12;

  private final EmployeeSalaryRepository employeeSalaryRepository;
  private final NationalInsuranceCalculatorService nationalInsuranceCalculatorService;

  public SalaryService(EmployeeSalaryRepository employeeSalaryRepository, NationalInsuranceCalculatorService nationalInsuranceCalculatorService) {
    this.employeeSalaryRepository = employeeSalaryRepository;
    this.nationalInsuranceCalculatorService = nationalInsuranceCalculatorService;
  }

  public void addBasicSalaryFor(EmployeeId employeeId, GBP amount, LocalDate date) {
    employeeSalaryRepository.addBasicSalaryFor(employeeId, amount, date);
  }

  public GBP getBasicSalaryFor(EmployeeId employeeId) {
    return employeeSalaryRepository.getBasicSalaryFor(employeeId);
  }

  public GBP getNationalInsuranceFor(EmployeeId employeeId) {
    return nationalInsuranceCalculatorService.calculate(getBasicSalaryFor(employeeId));
  }

  public GBP getTax(EmployeeId employeeId) {
    return calculateTaxFor(getBasicSalaryFor(employeeId));
  }

  private GBP calculateTaxFor(GBP basicSalaryAmount) {
    GBP basicAnnualSalaryAmount = basicSalaryAmount.multiplyBy(TWELVE_MONTHS);
    GBP annualTaxableSalary = basicAnnualSalaryAmount.minus(ANNUAL_PERSONAL_ALLOWANCE);
    if (annualTaxableSalary.isGreaterThanOrEqualTo(TWENTY_PERCENT_TAX_MINIMUM_BRACKET) &&
        annualTaxableSalary.isLessThanOrEqualTo(TWENTY_PERCENT_TAX_MAXIMUM_BRACKET)) {
        return annualTaxableSalary.dividedBy(TWELVE_MONTHS).multiplyBy(TWENTY_PERCENT);
    }
    return new GBP(0.00);
  }

  public GBP getGrandTotal(EmployeeId employeeId) {
    return getBasicSalaryFor(employeeId);
  }

  public GBP getNetPayable(EmployeeId employeeId) {
    final GBP nationalInsurance = nationalInsuranceCalculatorService.calculate(getBasicSalaryFor(employeeId));
    final GBP tax = getTax(employeeId);
    GBP deductions = nationalInsurance.plus(tax);
    return getBasicSalaryFor(employeeId).minus(deductions);
  }
}
