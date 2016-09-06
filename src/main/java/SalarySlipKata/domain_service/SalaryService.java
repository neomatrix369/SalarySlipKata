package SalarySlipKata.domain_service;

import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;
import SalarySlipKata.domain.SalaryItem;

public class SalaryService {

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

  public void addBasicSalaryFor(EmployeeId employeeId, SalaryItem salaryItem) {
    salaryRepository.addBasicSalaryFor(employeeId, salaryItem);
  }

  public void addBonusFor(EmployeeId employeeId, SalaryItem salaryItem) {
    salaryRepository.addBonusFor(employeeId, salaryItem);
  }

  public void addOvertimeFor(EmployeeId employeeId, SalaryItem salaryItem) {
    salaryRepository.addOvertimeFor(employeeId, salaryItem);
  }

  public void addLoanFor(EmployeeId employeeId, SalaryItem salaryItem) {
    salaryRepository.addLoanFor(employeeId, salaryItem);
  }

  public GBP getGrossSalary(EmployeeId employeeId) {
    return getBasicSalaryFor(employeeId)
        .plus(getBonus(employeeId))
        .plus(getOvertime(employeeId))
        .minus(getLoanFor(employeeId));
  }

  public GBP getBasicSalaryFor(EmployeeId employeeId) {
    return salaryRepository.getBasicSalaryFor(employeeId);
  }

  public GBP getBonus(EmployeeId employeeId) {
    return salaryRepository.getBonusFor(employeeId);
  }

  public GBP getOvertime(EmployeeId employeeId) {
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
