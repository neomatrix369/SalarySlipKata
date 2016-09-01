package SalarySlipKata;

import static java.lang.String.valueOf;

public class EmployeeId {
  private int id;

  public EmployeeId(int id) {this.id = id;}

  @Override
  public String toString() {
    return valueOf(id);
  }
}
