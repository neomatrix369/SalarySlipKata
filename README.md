Salary slip kata
================
- Problem description: Salary slip generator application for UK companies

### Scenario 1 - add employee basic salary and print a salary slip on the screen
    
  Given I have added the employee id
  And the employee's name
  And the employee's basic salary
  When I print the salary slip
  Then it should display the below on the screen:
  ```
         Date: 01 Sep 2016             Salary for period: Sep 2016

         Employee ID: 12345            Employee Name: John J Doe

         SALARY                   
         Basic            £2000.00
  ```

### Scenario 2 - calculate the National Insurance contributions based on the basic salary

  Given I have an employee id for an employee
  When I print the salary slip
  Then it should calculate the respective National Insurance contributions from the Basic Salary
  And display the below on the screen:
  ```
         Date: 01 Sep 2016             Salary for period: Sep 2016

         Employee ID: 12345            Employee Name: John J Doe

         SALARY                        DEDUCTION                   
         Basic            £2000.00     National Insurance     £159.40
  ```
  
### Scenario 3 - calculate the Tax to be paid based on the basic salary

  Given I have an employee id for an employee
  When I print the salary slip
  Then it should calculate the respective Tax to be paid from the Basic Salary
  And display the below on the screen:
  ```
         Date: 01 Sep 2016             Salary for period: Sep 2016
         
         Employee ID: 12345            Employee Name: John J Doe
         
         SALARY                        DEDUCTION                   
         Basic            £2000.00     National Insurance     £159.40
                                       Tax                    £216.67
  ```

### Scenario 4 - calculate the gross salary after adding a bonus to the basic salary

  Given I have an employee id for an employee
  And I have added a Bonus to the monthly salary
  When I print the salary slip
  Then it should calculate the Gross Salary (Basic + Bonus)
  And calculate the respective NI contributions based on the Gross Salary 
  And the Tax based on the Gross Salary
  And display the below on the screen:
  ```
         Date: 01 Sep 2016             Salary for period: Sep 2016

         Employee ID: 12345            Employee Name: John J Doe

         SALARY                        DEDUCTION                   
         Basic            £2000.00     National Insurance     £279.40
         Bonus            £1000.00     Tax                    £416.67

         Gross Salary     £3000.00
  ```

### Scenario 5 - calculate the gross salary after adding an overtime to the basic salary and bonus

  Given I have an employee id for an employee
  And I have added a Bonus to the monthly salary
  And I have added an Overtime to the monthly salary
  When I print the salary slip
  Then it should calculate the Gross Salary (Basic + Bonus + Overtime) 
  And calculate the respective NI contributions based on the Gross Salary 
  And the Tax based on the Gross Salary
  And display the below on the screen:
  ```
         Date: 01 Sep 2016             Salary for period: Sep 2016

         Employee ID: 12345            Employee Name: John J Doe

         SALARY                        DEDUCTION                   
         Basic            £2000.00     National Insurance     £339.40
         Bonus            £1000.00     Tax                    £516.67
         Overtime          £500.00

         Gross Salary     £3500.00
  ```

### Scenario 6 - calculate the gross salary after deducting a loan payment 

  Given I have an employee id for an employee
  And I have added a Bonus to the monthly salary
  And I have added an Overtime to the monthly salary
  And have added a Loan deduction to the monthly salary
  When I print the salary slip
  Then it should calculate the Gross Salary (Basic + Bonus + Overtime - Loan) 
  And calculate the respective NI contributions based on the Gross Salary 
  And the Tax based on the Gross Salary
  And the Net payable or salary to take home (Gross Salary - NI - Tax)
  And display the below on the screen:
  ```
         Date: 01 Sep 2016             Salary for period: Sep 2016

         Employee ID: 12345            Employee Name: John J Doe

         SALARY                        DEDUCTION
         Basic            £2000.00     Loan                   £200.00
         Bonus            £1000.00     National Insurance     £315.40
         Overtime          £500.00     Tax                    £467.67

         Gross Salary     £3300.00     Net payable           £2507.93
  ```
  
- Acceptance criteria:
    - Should accept EmployeeId, Employee Name and Basic Salary
    - Should print an employee's salary slip
    - The entry point should be the following interface, which you can not change:    
    ```java
    
        public class SalarySlipGenerator {
            public void addEarningsFor(EmployeeId employeeId, GBP amount, LocalDate date);
            public void applyDeductionsFor(EmployeeId employeeId, GBP amount, LocalDate date);
            public void printSalarySlipFor(EmployeeId employeeId);
        }
    
    ```

- Calculations
         
     Gross Salary       = Basic Salary + sum of all earnings/allowances - sum of all deductions

     National Insurance = NI deductable income * applicable NI rates
     National Insurance earnings threshold for the UK:
     ```
         Band                  NI deducable income     Rate
         ---------------------------------------------------
         No contributions      Up to £8,060             0%
         Basic contributions   £8,061 to £43,000       12%
         Higher contributions  over £43,000             2%
      ```

     Taxable Income     = Gross Salary - Personal Allowance 
     Tax                = Taxable Income * applicable tax rates
     All tax deductions must follow the below brackets for the UK: 
     ```
         Band                   Taxable income       Tax rate
         ----------------------------------------------------
         Personal Allowance     Up to £11,000            0%
         Basic rate             £11,001 to £43,000      20%
         Higher rate            £43,001 to £150,000     40%
         Additional rate        over £150,000           45%
     ```
     Total deductables  = National Insurance + Tax

     Net payable        = Gross Salary - Total Deductables

- Examples of National Insurance contributions and Tax calculations:
    - National Insurance contributions table:
    ```
        Annual Income (£)   First slab         Second slab         Third Slab         Total (£)
                            (below £8,060.00)  (between £8,060.00  (above £43,000.00)
                                               and £43,000.00)
                                   0%                  12%                2%
       -----------------------------------------------------------------------------------------        
                 5,000.00               0.00                 0.00               0.00       
                                        0.00                 0.00               0.00        0.00
                 8,060.00               0.00                 0.00               0.00       
                                        0.00                 0.00               0.00        0.00
                 9,060.00               0.00             1,000.00               0.00
                                        0.00               120.00               0.00      120.00
                40,000.00               0.00            31,940.00               0.00       
                                        0.00             3,832.80               0.00    3,832.80
                45,000.00               0.00            34,940.00           2,000.00 
                                        0.00             4,192.80              40.00    4,232.80
                50,000.00               0.00            34,940.00           7,000.00
                                        0.00             4,192.80             140.00    4,332.80
                60,000.00               0.00            34,940.00          17,000.00 
                                        0.00             4,192.80             340.00    4,532.80
    ```

    - Tax calculation table:
    ```
       Annual Income (£)   First slab           Second slab           Third Slab           Fourth Slab          Total (£)
                           (below £11,000.00)   (between £11,000.00   (between £43,000.00  (above £150,000.00)
                                                and £43,000.00)       and £150,000.00)
                                   0%                   20%                   40%                  45%
      --------------------------------------------------------------------------------------------------------------------------                                    
               5,000.00                0.00                    0.00                  0.00                 0.00       
                                       0.00                    0.00                  0.00                 0.00       0.00
              11,000.00                0.00                    0.00                  0.00                 0.00       
                                       0.00                    0.00                  0.00                 0.00       0.00
              12,000.00                0.00                1,000.00                  0.00                 0.00       
                                       0.00                  200.00                  0.00                 0.00     200.00
              40,000.00                0.00               29,000.00                  0.00                 0.00       
                                       0.00                5,800.00                  0.00                 0.00   5,800.00
              45,000.00                0.00               32,000.00              2,000.00                 0.00  
                                       0.00                6,400.00                800.00                 0.00   7,200.00
              50,000.00                0.00               32,000.00              7,000.00                 0.00  
                                       0.00                6,400.00              2,800.00                 0.00   9,200.00
              60,000.00                0.00               32,000.00             17,000.00                 0.00  
                                       0.00                6,400.00              6,800.00                 0.00  13,200.00
             100,000.00                0.00               32,000.00             57,000.00                 0.00  
                                       0.00                6,400.00             22,800.00                 0.00  29,200.00
    
    ```


- Resources
    - [Sample Salary Slip](http://1.bp.blogspot.com/-lJXMuMQCGtE/Udm8dlTIeSI/AAAAAAAAA1Q/jLxBZndJTAA/s1600/Pay+Slip+Format.JPG)
    - [Salary calculator](http://www.thesalarycalculator.co.uk/)
    - [Tax breakdown calculator](http://tools.hmrc.gov.uk/hmrctaxcalculator/screen/Personal+Tax+Calculator/en-GB/summary?user=guest)
    - [National insurance rates](http://www.which.co.uk/money/tax/guides/national-insurance-explained/national-insurance-rates/)
    - [HMRC - National insurance rates](https://www.gov.uk/guidance/rates-and-thresholds-for-employers-2016-to-2017)
