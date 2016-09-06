package SalarySlipKata.domain_service;

import java.time.LocalDate;

import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;
import SalarySlipKata.domain.SalaryItem;

public class SalaryService {

  private static final int TWELVE_MONTHS = 12;

  private final EmployeeRepository employeeRepository;
  private final SalaryRepository salaryRepository;
  private final NationalInsuranceCalculatorService nationalInsuranceCalculatorService;
  private final TaxCalculatorService taxCalculatorService;

  public SalaryService(
      EmployeeRepository employeeRepository, SalaryRepository salaryRepository,
      NationalInsuranceCalculatorService nationalInsuranceCalculatorService,
      TaxCalculatorService taxCalculatorService) {
    this.employeeRepository = employeeRepository;
    this.salaryRepository = salaryRepository;
    this.nationalInsuranceCalculatorService = nationalInsuranceCalculatorService;
    this.taxCalculatorService = taxCalculatorService;
  }

  public void addEarningFor(EmployeeId employeeId, SalaryItem earning) {
    salaryRepository.addEarningFor(employeeId, earning);
  }

  public void addDeductionFor(EmployeeId employeeId, SalaryItem deduction) {
    salaryRepository.addDeductionFor(employeeId, deduction);
  }

  public GBP getBasicSalaryFor(EmployeeId employeeId) {
    final GBP annualSalary = employeeRepository.getAnnualSalaryFor(employeeId);
    return annualSalary.dividedBy(TWELVE_MONTHS);
  }

  public GBP getGrossSalary(EmployeeId employeeId, LocalDate date) {
    return getBasicSalaryFor(employeeId)
        .plus(getBonusFor(employeeId, date))
        .plus(getOvertimeFor(employeeId, date))
        .minus(getLoanFor(employeeId, date));
  }

  public GBP getBonusFor(EmployeeId employeeId, LocalDate date) {
    return salaryRepository.getBonusFor(employeeId, date);
  }

  public GBP getOvertimeFor(EmployeeId employeeId, LocalDate date) {
    return salaryRepository.getOvertimeFor(employeeId, date);
  }

  public GBP getLoanFor(EmployeeId employeeId, LocalDate date) {
    return salaryRepository.getLoanFor(employeeId, date);
  }

  public GBP getNationalInsuranceFor(EmployeeId employeeId, LocalDate date) {
    return nationalInsuranceCalculatorService.calculate(getGrossSalary(employeeId, date));
  }

  public GBP getTax(EmployeeId employeeId, LocalDate date) {
    return taxCalculatorService.calculate(getGrossSalary(employeeId, date));
  }

  public GBP getNetPayable(EmployeeId employeeId, LocalDate date) {
    final GBP nationalInsurance = getNationalInsuranceFor(employeeId, date);
    final GBP deductions = nationalInsurance.plus(getTax(employeeId, date));
    return getGrossSalary(employeeId, date).minus(deductions);
  }

  public String getNameFor(EmployeeId employeeId) {
    return employeeRepository.getNameFor(employeeId);
  }

  public void addEmployee(EmployeeId employeeId, String name, GBP annualSalary) {
    employeeRepository.addEmployee(employeeId, name, annualSalary);
  }
}
