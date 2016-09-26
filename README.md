Salary slip kata
================
- Problem description: Salary slip generator application for UK companies.
 
  A typical salary slips contains employee details like employee id, employee name and their salary details like their gross salary, taxable income, tax-free allowance, national insurance contributions, taxes payable and net payable.
  
  In the event there are earnings like overtime and bonuses or deductions like loans, these are accounted for in the respective aspects of the salary slip and have an impact on the gross salary, national insurance contributions, and taxes to be paid.
  
  Salary slips are generated each month for every employee.
   
  Note: the tables used for calculating the National Insurance contributions and Taxes are for the current year (April 2016/17).
  Also note that when the Gross Salary exceeds £100,000, personal allows rules do not apply. Instead personal allowance decreases by a £1 for every £2 earned over £100,000.
  See table for further information on breakdown.

### Scenario 1: print a salary slip with employee details for an employee 
    
  <p>Given I have an employee</p>
  <p>When I generate a salary slip for the employee</p>
  <p>Then it should contain the Employee ID, Employee Name, Gross Salary, Taxable income, Tax-free allowance, National Insurance, Tax payable and Net payable</p>

- Acceptance criteria:
    - Should pass Employee Id, Employee Name and Annual Salary
    - Should generate the data that will be contained in an employee's salary slip
    - The entry point should be the following interface, which you can not change:
      ```java
      
          public class SalarySlipApplication {
            public void generateFor(Employee employee);
          }
      
      ```
    - The salary slip must contain the below items:     
      ```text
      
         Employee ID: 12345
         Employee Name: John J Doe
         Gross Salary: £2000.00
         Tax-free allowance: £916.67
         Taxable income: £1083.33
         National Insurance contributions: £159.40
         Tax Payable: £216.67 
         Net Payable: £1623.93
      ```

### Calculations: National Insurance contributions and Tax
 
   National Insurance contributions must follow the below bands for the UK:
   (description: PENDING)
   ```
         ---------------------+-----------------------+--------
         Band                 | NI deductible income  | NI Rate
         ---------------------+-----------------------+--------
         No contributions     | Up to £8,060          |    0%
         Basic contributions  | £8,061 to £43,000     |   12%
         Higher contributions | over £43,000          |    2%
         ---------------------+-----------------------+--------
   ```
 
   Tax payable must follow the below bands for the UK:
   (description: PENDING)
   ```
         ---------------------+----------------------+---------
         Band                 | Taxable income       | Tax rate
         ---------------------+----------------------+---------
         Personal Allowance*  | Up to £11,000        |    0%
         Basic rate           | £11,001 to £43,000   |   20%
         Higher rate          | £43,001 to £150,000  |   40%
         Additional rate      | over £150,000        |   45%
         ---------------------+----------------------+---------
         * - past a Gross Salary of £100,000, personal allowance rules change -
         for every extra £2 earned, the personal allowance reduces by £1. Past a salary of £122,000,
         no personal allowance is applicable.
   ```
   
   Total deductibles  = National Insurance + Tax

   Net payable        = Gross Salary - Total deductibles

### Examples: National Insurance contributions and Tax calculations
   - National Insurance contributions table (illustrated using both annual and monthly salaries):

         ---------------------+-------------+----------------------------+-----------------------------+--------------
                              | NI          |                            |                             |
                              | Deductible  | Basic contributions        |    Higher contributions     |
                              | Income      | (£8,060.00 - £43,000.00)   |    (above £43,000.00)       |
                              | after       |                            |                             |   Total NI
            Annual Income (£) | deducting   |           12%              |             2%              |   Contributed
                              | £8,060 from +------------+---------------+-------------+---------------+      (£)
                              | annual      |   Excess   |               |   Excess    |               |
                              | salary      |   Income   | Contributions |   Income    | Contributions |
         ---------------------|-------------+------------+---------------+-------------+---------------+--------------
                   5,000.00   |       0.00  |       0.00 |         0.00  |       0.00  |         0.00  |        0.00
                     416.67   |       0.00  |       0.00 |         0.00  |       0.00  |         0.00  |        0.00
                              |             |            |               |             |               |
                   8,060.00   |       0.00  |       0.00 |         0.00  |       0.00  |         0.00  |        0.00
                     671.67   |       0.00  |       0.00 |         0.00  |       0.00  |         0.00  |        0.00
                              |             |            |               |             |               |
                   9,060.00   |   1,000.00  |   1,000.00 |       120.00  |       0.00  |         0.00  |      120.00
                     755.00   |      83.33  |      83.33 |        10.00  |       0.00  |         0.00  |       10.00
                              |             |            |               |             |               |
                  40,000.00   |  31,940.00  |  31,940.00 |     3,832.80  |       0.00  |         0.00  |    3,832.80
                    3333.33   |   2,661.67  |   2,661.67 |       319.40  |       0.00  |         0.00  |      319.40
                              |             |            |               |             |               |
                  43,000.00   |  34,940.00  |  34,940.00 |     4,192.80  |       0.00  |         0.00  |    4,192.80
                   3,583.33   |   2,911.67  |   2,911.67 |       349.40  |       0.00  |         0.00  |      349.40
                              |             |            |               |             |               |
                  45,000.00   |  36,940.00  |  34,940.00 |     4,192.80  |   2,000.00  |        40.00  |    4,232.80
                   3,750.00   |   3,078.33  |   2,911.67 |       349.40  |     166.67  |         3.33  |      352.73
                              |             |            |               |             |               |
                  50,000.00   |  41,940.00  |  34,940.00 |     4,192.80  |   7,000.00  |       140.00  |    4,332.80
                   4,166.67   |   3,495.00  |   2,911.67 |       349.40  |     583.33  |        11.67  |      361.07
                              |             |            |               |             |               |
                  60,000.00   |  51,940.00  |  34,940.00 |     4,192.80  |  17,000.00  |       340.00  |    4,532.80
                   5,000.00   |   4,328.33  |   2,911.67 |       349.40  |   1,416.67  |        28.33  |      377.73
                              |             |            |               |             |               |
         ---------------------+-------------+------------+---------------+-------------+---------------+--------------

   - Tax calculation table:

         -------------------+-------------+-------------------------+-------------------------+------------------------+---------------
                            |             |   Basic rate band       |   Higher rate band      |  Additional rate band  |
                            |  Taxable    |   (between £11,000.00   |   (between £43,000.00   |  (above £150,000.00)   |  
                            |  Income     |   and £43,000.00)       |   and £150,000.00)      |                        |  
          Annual Income (£) |  after      |                         |                         |                        |  Total Tax
                            |  deducting  |           20%           |           40%           |           45%          |  Payable (£)
                            |  Personal   +------------+------------+------------+------------+-----------+------------+
                            |  Allowance  |  Excess    |   Tax      |  Excess    |   Tax      |  Excess   |   Tax      |
                            |             |  Income    |   Payable  |  Income    |   Payable  |  Income   |   Payable  |
         -------------------+-------------+------------+------------+------------+------------+-----------+------------+---------------
                 5,000.00   |       0.00  |       0.00 |      0.00  |       0.00 |      0.00  |      0.00 |      0.00  |        0.00
                            |             |            |            |            |            |           |            |
                11,000.00   |       0.00  |       0.00 |      0.00  |       0.00 |      0.00  |      0.00 |      0.00  |        0.00
                            |             |            |            |            |            |           |            |
                12,000.00   |   1,000.00  |   1,000.00 |    200.00  |       0.00 |      0.00  |      0.00 |      0.00  |      200.00
                            |             |            |            |            |            |           |            |
                40,000.00   |  29,000.00  |  29,000.00 |  5,800.00  |       0.00 |      0.00  |      0.00 |      0.00  |    5,800.00
                            |             |            |            |            |            |           |            |
                45,000.00   |  34,000.00  |  32,000.00 |  6,400.00  |   2,000.00 |    800.00  |      0.00 |      0.00  |    7,200.00
                            |             |            |            |            |            |           |            |
                50,000.00   |  39,000.00  |  32,000.00 |  6,400.00  |   7,000.00 |  2,800.00  |      0.00 |      0.00  |    9,200.00
                            |             |            |            |            |            |           |            |
                60,000.00   |  49,000.00  |  32,000.00 |  6,400.00  |  17,000.00 |  6,800.00  |      0.00 |      0.00  |   13,200.00
                            |             |            |            |            |            |           |            |
               100,000.00   |  89,000.00  |  32,000.00 |  6,400.00  |  57,000.00 | 22,800.00  |      0.00 |      0.00  |   29,200.00
                            |             |            |            |            |            |           |            |
               111,000.00*  | 105,500.00* |  32,000.00 |  6,400.00  |  73,500.00 | 29,400.00* |      0.00 |      0.00  |   35,800.00*
                            |             |            |            |            |            |           |            |
               122,000.00*  | 122,000.00* |  32,000.00 |  6,400.00  |  90,000.00 | 36,000.00* |      0.00 |      0.00  |   42,400.00*
                            |             |            |            |            |            |           |            |
               150,000.00*  | 150,000.00* |  32,000.00 |  6,400.00  | 118,000.00 | 47,200.00* |      0.00 |      0.00  |   53,600.00*
                            |             |            |            |            |            |           |            |
               160,000.00*  | 160,000.00* |  32,000.00 |  6,400.00  | 118,000.00 | 47,200.00* | 10,000.00 |  4,500.00  |   58,100.00*
                            |             |            |            |            |            |           |            |
         -------------------+-------------+------------+------------+------------+------------+-----------+------------+--------------

         * - past a Gross Salary of £100,000, personal allowance rules change - for every extra £2 earned, the personal allowance reduces by £1. 
         Past a salary of £122,000, no personal allowance is applicable.
 
- Resources
    - [Sample Salary Slip](http://1.bp.blogspot.com/-lJXMuMQCGtE/Udm8dlTIeSI/AAAAAAAAA1Q/jLxBZndJTAA/s1600/Pay+Slip+Format.JPG)
    - [Salary calculator](http://www.thesalarycalculator.co.uk/)
    - [Tax breakdown calculator - ListenToTaxman](https://listentotaxman.com/122000?)
    - [Tax breakdown calculator - HMRC](http://tools.hmrc.gov.uk/hmrctaxcalculator/screen/Personal+Tax+Calculator/en-GB/summary?user=guest)
    - [National insurance rates](http://www.which.co.uk/money/tax/guides/national-insurance-explained/national-insurance-rates/)
    - [HMRC - National insurance rates](https://www.gov.uk/guidance/rates-and-thresholds-for-employers-2016-to-2017)
