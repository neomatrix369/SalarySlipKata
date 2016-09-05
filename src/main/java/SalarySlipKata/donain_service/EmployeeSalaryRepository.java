package SalarySlipKata.donain_service;

import static java.time.LocalDate.now;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import SalarySlipKata.domain.BasicSalary;
import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;
import SalarySlipKata.domain.SalaryItem;
import SalarySlipKata.donain.Bonus;

public class EmployeeSalaryRepository {
  private static final SalaryItem NO_BONUS = new Bonus(new GBP(0.0), now());
  private static final SalaryItem NO_BASIC_SALARY = new BasicSalary(new GBP(0.0), now());

  private final Map<EmployeeId, List<SalaryItem>> salaries = new HashMap<>();

  public EmployeeSalaryRepository() {}

  public void addBasicSalaryFor(EmployeeId employeeId, GBP amount, LocalDate date) {
    addSalaryToListFor(employeeId, new BasicSalary(amount, date));
  }

  public void addBonusFor(EmployeeId employeeId, GBP amount, LocalDate date) {
    addSalaryToListFor(employeeId, new Bonus(amount, date));
  }

  private void addSalaryToListFor(EmployeeId employeeId, SalaryItem salaryItem) {
    final List<SalaryItem> list = salaries.getOrDefault(employeeId, new ArrayList<>());
    list.add(salaryItem);

    salaries.put(employeeId, list);
  }

  public GBP getBasicSalaryFor(EmployeeId employeeId) {
    return getSalaryFromListFor(employeeId, eachSalaryItem -> eachSalaryItem instanceof BasicSalary, NO_BASIC_SALARY);
  }

  public GBP getBonusFor(EmployeeId employeeId) {
    return getSalaryFromListFor(employeeId, eachSalaryItem -> eachSalaryItem instanceof Bonus, NO_BONUS);
  }

  private GBP getSalaryFromListFor(
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
