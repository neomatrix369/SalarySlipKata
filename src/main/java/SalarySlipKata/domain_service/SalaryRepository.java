package SalarySlipKata.domain_service;

import static java.time.LocalDate.now;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import SalarySlipKata.domain.Basic;
import SalarySlipKata.domain.Bonus;
import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;
import SalarySlipKata.domain.Loan;
import SalarySlipKata.domain.Overtime;
import SalarySlipKata.domain.SalaryItem;

public class SalaryRepository {
  private final Map<EmployeeId, List<SalaryItem>> salaries = new HashMap<>();

  static final SalaryItem NO_OVERTIME = new Overtime(new GBP(0.0), now());
  static final SalaryItem NO_LOAN = new Loan(new GBP(0.0), now());
  static final SalaryItem NO_BASIC_SALARY = new Basic(new GBP(0.0), now());
  static final SalaryItem NO_BONUS = new Bonus(new GBP(0.0), now());

  public void addBasicSalaryFor(EmployeeId employeeId, SalaryItem salaryItem) {
    addSalaryToListFor(employeeId, salaryItem);
  }

  public void addBonusFor(EmployeeId employeeId, SalaryItem salaryItem) {
    addSalaryToListFor(employeeId, salaryItem);
  }

  public void addOvertimeFor(EmployeeId employeeId, SalaryItem salaryItem) {
    addSalaryToListFor(employeeId, salaryItem);
  }

  public void addLoanFor(EmployeeId employeeId, SalaryItem salaryItem) {
    addSalaryToListFor(employeeId, salaryItem);
  }

  private void addSalaryToListFor(EmployeeId employeeId, SalaryItem salaryItem) {
    final List<SalaryItem> list = salaries.getOrDefault(employeeId, new ArrayList<>());
    list.add(salaryItem);

    salaries.put(employeeId, list);
  }

  public  GBP getBasicSalaryFor(EmployeeId employeeId) {
    return getFromSalaryListFor(employeeId, eachSalaryItem -> eachSalaryItem instanceof Basic, NO_BASIC_SALARY);
  }

  public GBP getBonusFor(EmployeeId employeeId) {
    return getFromSalaryListFor(employeeId, eachSalaryItem -> eachSalaryItem instanceof Bonus, NO_BONUS);
  }

  public GBP getOvertimeFor(EmployeeId employeeId) {
    return getFromSalaryListFor(employeeId, eachSalaryItem -> eachSalaryItem instanceof Overtime, NO_OVERTIME);
  }

  public GBP getLoanFor(EmployeeId employeeId) {
    return getFromSalaryListFor(employeeId, eachSalaryItem -> eachSalaryItem instanceof Loan, NO_LOAN);
  }

  private GBP getFromSalaryListFor(
      EmployeeId employeeId,
      Predicate<SalaryItem> condition,
      SalaryItem defaultSalaryItem) {

    return salaries
        .get(employeeId)
        .stream()
        .filter(condition)
        .findFirst()
        .orElse(defaultSalaryItem)
        .getAmount();
  }
}
