package SalarySlipKata.domain_service;

import static SalarySlipKata.domain.GBP.zero;

import java.time.LocalDate;
import java.util.function.Predicate;

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

  public void addSalaryItem(EmployeeId employeeId, SalaryItem salaryItem) {
    salaryRepository.addSalaryItem(employeeId, salaryItem);
  }

  public GBP getBasicSalaryFor(EmployeeId employeeId) {
    final GBP annualSalary = employeeRepository.getAnnualSalaryFor(employeeId);
    return annualSalary.dividedBy(TWELVE_MONTHS);
  }

  public GBP getGrossSalary(EmployeeId employeeId, LocalDate date) {
    final GBP sumOfEarningItems = sumSalaryItems(employeeId, date, SalaryItem::isEarning);
    final GBP sumOfDeductions = sumSalaryItems(employeeId, date, SalaryItem::isDeduction);

    return getBasicSalaryFor(employeeId)
        .plus(sumOfEarningItems)
        .minus(sumOfDeductions);
  }

  private GBP sumSalaryItems(EmployeeId employeeId, LocalDate date, Predicate<SalaryItem> bySalaryType) {
    return salaryRepository
        .getSalaryItemsFor(employeeId, date)
        .stream()
        .filter(bySalaryType)
        .map(SalaryItem::getAmount)
        .reduce(zero(), GBP::plus);
  }

  public GBP getSalaryItem(EmployeeId employeeId, Class<? extends SalaryItem> salaryItemClass, LocalDate date) {
    return salaryRepository.getSalaryItemFor(employeeId, salaryItemClass, date);
  }

  public GBP getNationalInsuranceFor(EmployeeId employeeId, LocalDate date) {
    return nationalInsuranceCalculatorService.calculate(getGrossSalary(employeeId, date));
  }

  public GBP getTax(EmployeeId employeeId, LocalDate date) {
    return taxCalculatorService.calculate(getGrossSalary(employeeId, date));
  }

  public GBP getNetPayable(EmployeeId employeeId, LocalDate date) {
    final GBP nationalInsurance = getNationalInsuranceFor(employeeId, date);
    final GBP tax = getTax(employeeId, date);
    return getGrossSalary(employeeId, date)
        .minus(nationalInsurance)
        .minus(tax);
  }

  public String getNameFor(EmployeeId employeeId) {
    return employeeRepository.getNameFor(employeeId);
  }

  public void addEmployee(EmployeeId employeeId, String name, GBP annualSalary) {
    employeeRepository.addEmployee(employeeId, name, annualSalary);
  }
}
