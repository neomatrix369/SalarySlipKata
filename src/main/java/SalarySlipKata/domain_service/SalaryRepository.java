package SalarySlipKata.domain_service;

import static SalarySlipKata.domain.SalaryItem.getDefaultSalaryItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.domain.GBP;
import SalarySlipKata.domain.SalaryItem;

public class SalaryRepository {
  private final Map<EmployeeId, List<SalaryItem>> salaries = new HashMap<>();

  public void addSalaryItem(EmployeeId employeeId, SalaryItem salaryItem) {
    final List<SalaryItem> list = salaries.getOrDefault(employeeId, new ArrayList<>());
    list.add(salaryItem);

    salaries.put(employeeId, list);
  }

  public GBP getSalaryItemFor(
      EmployeeId employeeId,
      Class<? extends SalaryItem> salaryItemClass,
      LocalDate date) {
    return getSalaryItemsFor(employeeId, date)
        .stream()
        .filter(eachSalaryItem -> eachSalaryItem.getClass().equals(salaryItemClass))
        .findFirst()
        .orElse(getDefaultSalaryItem(date))
        .getAmount();
  }

  public List<SalaryItem> getSalaryItemsFor(EmployeeId employeeId, LocalDate date) {
    return salaries
        .get(employeeId)
        .stream()
        .filter(eachSalaryItem -> eachSalaryItem.forDate(date))
        .collect(Collectors.toList());
  }
}
