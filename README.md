Salary slip kata
================
- Problem description: 
   Salary slip generator for UK companies.

    - Create a Salary slip generator application with the following features:
        - Register employee's basic salary and print a salary slip to console
        - Register a overtime for a month and print a salary slip to console
        - Register a bonus for a month and print a salary slip to console
        - Register a loan deduction for a month and print a salary slip to console
         
    A salary slip printed on the console, will look like the below:
    
    ##### Salary slip (bonus, overtime earnings and loan deductions)

         Date: 01 Sep 2016             Salary for period: Sep 2016
         
         Employee ID: 12345
         
         SALARY                     DEDUCTION
         Basic            £2000.00  Loan                   £200.00
         Bonus            £1000.00  National Insurance     £315.40
         Overtime          £500.00  Tax                    £467.67
         
         Gross Salary     £3300.00  Net payable           £2507.93

- Acceptance criteria:
    - Should accept EmployeeId, employee Name and Basic Salary
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

     National Insurance = Gross Salary - applicable NI percentage
     National Insurance earnings threshold for the UK:
     ```
         Band                  NI deducable income     Rate
         ---------------------------------------------------
         No contributions      Up to £8,060             0%
         Basic contributions   £8,061 to £43,000       12%
         Higher contributions  over £43,000             2%
      ```

     Taxable Income     = Gross Salary - Personal Allowance 
     Tax                = Taxable Income - applicable tax percentage
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
        Annual Income (£)   First slab         Second slab        Third Slab           Total
                            (below £8060.00)   (between £8060.00  (above £43000.00)
                                               and £43000.00)
                                   0%                  12%                2%
       -----------------------------------------------------------------------------------------        
                 5000.00               0.00                 0.00               0.00       
                                       0.00                 0.00               0.00        0.00
                 8060.00               0.00                 0.00               0.00       
                                       0.00                 0.00               0.00        0.00
                 9060.00               0.00              1000.00               0.00
                                       0.00               120.00               0.00      120.00
                40000.00               0.00             31940.00               0.00       
                                       0.00              3832.80               0.00     3832.80
                45000.00               0.00             34940.00            2000.00       
                                       0.00              4192.80              40.00     4232.80
                50000.00               0.00             34940.00            7000.00
                                       0.00              4192.80             140.00     4332.80
                60000.00               0.00             34940.00           17000.00       
                                       0.00              4192.80             340.00     4532.80
    ```

    - Tax calculation table:
    ```
       Annual Income (£)   First slab           Second slab           Third Slab           Fourth Slab          Total
                           (below £11,000.00)   (between £11,000.00   (between £43,000.00  (above £150,000.00)
                                                and £43,000.00)       and £150,000.00)
                                   0%                   20%                   40%                  45%
      --------------------------------------------------------------------------------------------------------------------------                                    
                5000.00                0.00                    0.00                  0.00                 0.00       
                                       0.00                    0.00                  0.00                 0.00       0.00
               11000.00                0.00                    0.00                  0.00                 0.00       
                                       0.00                    0.00                  0.00                 0.00       0.00
               12000.00                0.00                 1000.00                  0.00                 0.00       
                                       0.00                  200.00                  0.00                 0.00     200.00
               40000.00                0.00                29000.00                  0.00                 0.00       
                                       0.00                 5800.00                  0.00                 0.00    5800.00
               45000.00                0.00                32000.00               2000.00                 0.00       
                                       0.00                 6400.00                800.00                 0.00    7200.00
               50000.00                0.00                32000.00               7000.00                 0.00       
                                       0.00                 6400.00               2800.00                 0.00    9200.00
               60000.00                0.00                32000.00              17000.00                 0.00       
                                       0.00                 6400.00               6800.00                 0.00   13200.00
              100000.00                0.00                32000.00              57000.00                 0.00       
                                       0.00                 6400.00              22800.00                 0.00   29200.00
    
    ```


- Resources
    - [Sample Salary Slip](http://1.bp.blogspot.com/-lJXMuMQCGtE/Udm8dlTIeSI/AAAAAAAAA1Q/jLxBZndJTAA/s1600/Pay+Slip+Format.JPG)
    - [Salary calculator](http://www.thesalarycalculator.co.uk/)
    - [Tax breakdown calculator](http://tools.hmrc.gov.uk/hmrctaxcalculator/screen/Personal+Tax+Calculator/en-GB/summary?user=guest)
    - [National insurance rates](http://www.which.co.uk/money/tax/guides/national-insurance-explained/national-insurance-rates/)
    - [HMRC - National insurance rates](https://www.gov.uk/guidance/rates-and-thresholds-for-employers-2016-to-2017)
