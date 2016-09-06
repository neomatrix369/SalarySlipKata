package SalarySlipKata.domain_service;

import static java.time.LocalDate.now;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

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
  static final SalaryItem NO_BONUS = new Bonus(new GBP(0.0), now());

  public void addSalaryItem(EmployeeId employeeId, SalaryItem salaryItem) {
    final List<SalaryItem> list = salaries.getOrDefault(employeeId, new ArrayList<>());
    list.add(salaryItem);

    salaries.put(employeeId, list);
  }

  public GBP getBonusFor(EmployeeId employeeId, LocalDate date) {
    return getFromSalaryListFor(employeeId, eachSalaryItem -> eachSalaryItem instanceof Bonus, eachSalaryItem -> eachSalaryItem.forDate(date), NO_BONUS);
  }

  public GBP getOvertimeFor(EmployeeId employeeId, LocalDate date) {
    return getFromSalaryListFor(employeeId, eachSalaryItem -> eachSalaryItem instanceof Overtime, eachSalaryItem -> eachSalaryItem.forDate(date), NO_OVERTIME);
  }

  public GBP getLoanFor(EmployeeId employeeId, LocalDate date) {
    return getFromSalaryListFor(employeeId, eachSalaryItem -> eachSalaryItem instanceof Loan, eachSalaryItem -> eachSalaryItem.forDate(date), NO_LOAN);
  }

  private GBP getFromSalaryListFor(
      EmployeeId employeeId,
      Predicate<SalaryItem> bySalaryItemType,
      Predicate<SalaryItem> byDate, SalaryItem defaultSalaryItem) {

    return salaries
        .get(employeeId)
        .stream()
        .filter(bySalaryItemType)
        .filter(byDate)
        .findFirst()
        .orElse(defaultSalaryItem)
        .getAmount();
  }
}
