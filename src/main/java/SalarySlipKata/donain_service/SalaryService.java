package SalarySlipKata.donain_service;

import java.time.LocalDate;

import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;

public class SalaryService {

  private final EmployeeSalaryRepository employeeSalaryRepository;
  private final NationalInsuranceCalculatorService nationalInsuranceCalculatorService;
  private final TaxCalculatorService taxCalculatorService;

  public SalaryService(
      EmployeeSalaryRepository employeeSalaryRepository,
      NationalInsuranceCalculatorService nationalInsuranceCalculatorService,
      TaxCalculatorService taxCalculatorService) {
    this.employeeSalaryRepository = employeeSalaryRepository;
    this.nationalInsuranceCalculatorService = nationalInsuranceCalculatorService;
    this.taxCalculatorService = taxCalculatorService;
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
    return taxCalculatorService.calculate(getBasicSalaryFor(employeeId));
  }

  public GBP getGrandTotal(EmployeeId employeeId) {
    return getBasicSalaryFor(employeeId);
  }

  public GBP getNetPayable(EmployeeId employeeId) {
    final GBP nationalInsurance = nationalInsuranceCalculatorService.calculate(getBasicSalaryFor(employeeId));
    final GBP deductions = nationalInsurance.plus(getTax(employeeId));
    return getGrandTotal(employeeId).minus(deductions);
  }
}
