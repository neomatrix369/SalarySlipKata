package SalarySlipKata.donain_service;

import static java.time.LocalDate.now;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import SalarySlipKata.domain.Basic;
import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;
import SalarySlipKata.domain.Loan;
import SalarySlipKata.domain.Overtime;
import SalarySlipKata.domain.SalaryItem;
import SalarySlipKata.domain.Bonus;

public class EmployeeSalaryRepository {
  private static final SalaryItem NO_BASIC_SALARY = new Basic(new GBP(0.0), now());
  private static final SalaryItem NO_BONUS = new Bonus(new GBP(0.0), now());
  private static final SalaryItem NO_OVERTIME = new Overtime(new GBP(0.0), now());
  private static final SalaryItem NO_LOAN = new Loan(new GBP(0.0), now());

  private final Map<EmployeeId, List<SalaryItem>> salaries = new HashMap<>();

  public EmployeeSalaryRepository() {}

  public void addBasicSalaryFor(EmployeeId employeeId, GBP amount, LocalDate date) {
    addSalaryToListFor(employeeId, new Basic(amount, date));
  }

  public void addBonusFor(EmployeeId employeeId, GBP amount, LocalDate date) {
    addSalaryToListFor(employeeId, new Bonus(amount, date));
  }

  public void addOvertimeFor(EmployeeId employeeId, GBP amount, LocalDate date) {
    addSalaryToListFor(employeeId, new Overtime(amount, date));
  }

  public void addLoanFor(EmployeeId employeeId, GBP amount, LocalDate date) {
    addSalaryToListFor(employeeId, new Loan(amount, date));
  }

  private void addSalaryToListFor(EmployeeId employeeId, SalaryItem salaryItem) {
    final List<SalaryItem> list = salaries.getOrDefault(employeeId, new ArrayList<>());
    list.add(salaryItem);

    salaries.put(employeeId, list);
  }

  public GBP getBasicSalaryFor(EmployeeId employeeId) {
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
      Predicate<SalaryItem> predicate,
      SalaryItem defaultSalaryItem) {

    return salaries
        .get(employeeId)
        .stream()
        .filter(predicate)
        .findFirst()
        .orElse(defaultSalaryItem)
        .getAmount();
  }
}
