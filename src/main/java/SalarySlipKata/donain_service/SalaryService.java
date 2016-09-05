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

  public void addBonusFor(EmployeeId employeeId, GBP amount, LocalDate date) {
    employeeSalaryRepository.addBonusFor(employeeId, amount, date);
  }

  public GBP getGrandTotal(EmployeeId employeeId) {
    return getBasicSalaryFor(employeeId)
        .plus(getBonus(employeeId));
  }

  public GBP getBasicSalaryFor(EmployeeId employeeId) {
    return employeeSalaryRepository.getBasicSalaryFor(employeeId);
  }

  public GBP getBonus(EmployeeId employeeId) {
    return employeeSalaryRepository.getBonusFor(employeeId);
  }

  public GBP getNationalInsuranceFor(EmployeeId employeeId) {
    return nationalInsuranceCalculatorService.calculate(getGrandTotal(employeeId));
  }

  public GBP getTax(EmployeeId employeeId) {
    return taxCalculatorService.calculate(getGrandTotal(employeeId));
  }

  public GBP getNetPayable(EmployeeId employeeId) {
    final GBP nationalInsurance = getNationalInsuranceFor(employeeId);
    final GBP deductions = nationalInsurance.plus(getTax(employeeId));
    return getGrandTotal(employeeId).minus(deductions);
  }
}
