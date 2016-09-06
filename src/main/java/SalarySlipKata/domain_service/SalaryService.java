package SalarySlipKata.domain_service;

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

  public GBP getGrossSalary(EmployeeId employeeId) {
    return getBasicSalaryFor(employeeId)
        .plus(getBonusFor(employeeId))
        .plus(getOvertimeFor(employeeId))
        .minus(getLoanFor(employeeId));
  }

  public GBP getBonusFor(EmployeeId employeeId) {
    return salaryRepository.getBonusFor(employeeId);
  }

  public GBP getOvertimeFor(EmployeeId employeeId) {
    return salaryRepository.getOvertimeFor(employeeId);
  }

  public GBP getLoanFor(EmployeeId employeeId) {
    return salaryRepository.getLoanFor(employeeId);
  }

  public GBP getNationalInsuranceFor(EmployeeId employeeId) {
    return nationalInsuranceCalculatorService.calculate(getGrossSalary(employeeId));
  }

  public GBP getTax(EmployeeId employeeId) {
    return taxCalculatorService.calculate(getGrossSalary(employeeId));
  }

  public GBP getNetPayable(EmployeeId employeeId) {
    final GBP nationalInsurance = getNationalInsuranceFor(employeeId);
    final GBP deductions = nationalInsurance.plus(getTax(employeeId));
    return getGrossSalary(employeeId).minus(deductions);
  }

  public String getNameFor(EmployeeId employeeId) {
    return employeeRepository.getNameFor(employeeId);
  }

  public void addEmployee(EmployeeId employeeId, String name, GBP annualSalary) {
    employeeRepository.addEmployee(employeeId, name, annualSalary);
  }
}
