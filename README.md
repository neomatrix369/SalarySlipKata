Salary slip kata
================
- Problem description: Salary slip generator application for UK companies.
 
  A typical salary slips contains employee details like employee id, employee name and their salary details like their basic salary, tax free allowance, gross salary, national insurance contributions, and taxes to be paid.
  
  In the event there are earnings like overtime and bonuses or deductions like loans, these are accounted for in the respective aspects of the salary slip and have an impact on the gross salary, national insurance contributions, and taxes to be paid.
  
  Salary slips are generated each month for every employee.
   
  Note: the tables used for calculating the National Insurance contributions and Taxes are for the current year (April 2016/17).
  Also note that when the Gross Salary exceeds £100,000, personal allows rules do not apply. Instead personal allowance decreases by a £1 for every £2 earned over £100,000.
  See table for further information on breakdown.

### Scenario 1: print a salary slip with employee details for an employee 
    
  <p>Given I have an employee</p>
  <p>When I generate a salary slip for the employee</p>
  <p>Then it should contain the Employee ID, Employee Name, Basic Salary, Tax free allowance, Gross Salary, National Insurance, Tax and Net payable</p> 

- Acceptance criteria:
    - Should pass Employee Id, Employee Name and Annual Basic Salary
    - Should generate the data that will be contained in an employee's salary slip
    - The entry point should be the following interface, which you can not change:    
      ```java
      
          public class SalarySlipApplication {
            public void printFor(Employee employee);
          }
      
      ``` 
    - The salary slip must contain the below items: 
      ```
         Employee ID: 12345            
         Employee Name: John J Doe
         Basic Salary: £2000.00
         Tax free allowance: £916.67
         Gross Salary: £2000.00
         National Insurance contribution: £159.40 
         Tax Payable: £216.67 
         Net Payable: £1623.93
      ```

### Calculations: National Insurance contributions and Tax
         
   Gross Salary       = Basic Salary + sum of all earnings - sum of all deductions

   National Insurance = NI deductable income * applicable NI rates
   National Insurance earnings threshold for the UK:
   ```
         Band                  NI deducable income     Rate
         --------------------------------------------------
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

### Examples: National Insurance contributions and Tax calculations
   - National Insurance contributions table:
    
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

   - Tax calculation table:

         Annual Income (£)   First slab           Second slab           Third Slab           Fourth Slab           Total (£)
                             (below £11,000.00)   (between £11,000.00   (between £43,000.00  (above £150,000.00)
                                                  and £43,000.00)       and £150,000.00)
                                     0%                   20%                   40%                  45%
        ---------------------------------------------------------------------------------------------------------------------------                                    
                 5,000.00                0.00                    0.00                  0.00                 0.00        
                                         0.00                    0.00                  0.00                 0.00        0.00
                11,000.00                0.00                    0.00                  0.00                 0.00        
                                         0.00                    0.00                  0.00                 0.00        0.00
                12,000.00                0.00                1,000.00                  0.00                 0.00        
                                         0.00                  200.00                  0.00                 0.00      200.00
                40,000.00                0.00               29,000.00                  0.00                 0.00        
                                         0.00                5,800.00                  0.00                 0.00    5,800.00
                45,000.00                0.00               32,000.00              2,000.00                 0.00   
                                         0.00                6,400.00                800.00                 0.00    7,200.00
                50,000.00                0.00               32,000.00              7,000.00                 0.00   
                                         0.00                6,400.00              2,800.00                 0.00    9,200.00
                60,000.00                0.00               32,000.00             17,000.00                 0.00   
                                         0.00                6,400.00              6,800.00                 0.00   13,200.00
               100,000.00                0.00               32,000.00             57,000.00                 0.00   
                                         0.00                6,400.00             22,800.00                 0.00   29,200.00
               111,000.00                0.00               32,000.00             68,000.00                 0.00   
                                         0.00                6,400.00             29,400.00*                0.00*  35,800.00*
               122,000.00                0.00               32,000.00             77,000.00                 0.00   
                                         0.00                6,400.00             36,000.00*                0.00*  42,400.00*
               150,000.00                0.00               32,000.00            107,000.00                 0.00   
                                         0.00                6,400.00             47,200.00*                0.00*  53,600.00*
               160,000.00                0.00               32,000.00            107,000.00            10,000.00
                                         0.00                6,400.00             47,200.00*            4,500.00*  58,100.00*

         * - past a Gross Salary of £100,000, personal allowance rules change - for every extra £1, the personal allowance reduces by £2. Past a salary of £106,500, no personal allowance is applicable.                                                                                                                                                                     
 
- Resources
    - [Sample Salary Slip](http://1.bp.blogspot.com/-lJXMuMQCGtE/Udm8dlTIeSI/AAAAAAAAA1Q/jLxBZndJTAA/s1600/Pay+Slip+Format.JPG)
    - [Salary calculator](http://www.thesalarycalculator.co.uk/)
    - [Tax breakdown calculator - ListenToTaxman](https://listentotaxman.com/122000?)
    - [Tax breakdown calculator - HMRC](http://tools.hmrc.gov.uk/hmrctaxcalculator/screen/Personal+Tax+Calculator/en-GB/summary?user=guest)
    - [National insurance rates](http://www.which.co.uk/money/tax/guides/national-insurance-explained/national-insurance-rates/)
    - [HMRC - National insurance rates](https://www.gov.uk/guidance/rates-and-thresholds-for-employers-2016-to-2017)
