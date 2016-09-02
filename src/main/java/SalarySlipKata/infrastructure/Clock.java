package SalarySlipKata.infrastructure;

import static java.time.LocalDate.now;

import java.time.LocalDate;

public class Clock {
  public LocalDate getCurrentDate() {return now();}
}
