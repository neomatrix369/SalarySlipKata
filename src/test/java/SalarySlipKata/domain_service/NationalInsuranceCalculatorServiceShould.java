package SalarySlipKata.domain_service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static SalarySlipKata.domain.GBP.zero;

import org.junit.Before;
import org.junit.Test;

import SalarySlipKata.domain.GBP;

public class NationalInsuranceCalculatorServiceShould {

  private NationalInsuranceCalculatorService nationalInsuranceCalculatorService;

  @Before
  public void initialise() {
    nationalInsuranceCalculatorService = new NationalInsuranceCalculatorService();
  }

  @Test public void
  return_zero_for_basic_salary_within_the_first_threshold() {
    assertThat(nationalInsuranceCalculatorService.calculate(new GBP(100.00)), is(zero()));
  }
  
  @Test public void
  return_twelve_percent_for_basic_salary_within_the_second_threshold() {
    assertThat(nationalInsuranceCalculatorService.calculate(new GBP(1000.00)).toString(), equalTo(new GBP(39.40).toString()));
  } 

  @Test public void
  return_12_percent_and_2_percent_for_the_two_slabs_respectively_for_basic_salary_above_the_2nd_threshold() {
    assertThat(nationalInsuranceCalculatorService.calculate(new GBP(4000.00)).toString(), equalTo(new GBP(357.73).toString()));
  } 
}
