Salary slip kata
================

### Problem description: Salary slip generator for UK companies.
 
  A typical salary slips contains employee details like employee id, employee name and their monthly salary details like their gross salary, national insurance contributions, tax-free allowance*, taxable income and tax payable.
  
  Salary slips are generated each month for every employee.
   
  Note: the tables used for calculating the National Insurance contributions and Taxes are for the current year (April 2016/17).
  Also note that when the Annual Gross Salary exceeds £100,000.00, personal allowance rules do not apply. Instead personal allowance decreases by a £1 for every £2 earned over £100,000.00. And this excess is taxed at the Higher rate tax.
  See [Examples: National Insurance contributions and Tax calculations](#examples-national-insurance-contributions-and-tax-calculations) for further information on the breakdown.

### Scenario: print a salary slip with employee details for an employee 
    
  <p><b>Given</b> I have an employee John J Doe with an annual gross salary of £24,000.00</p>
  <p><b>When</b> I generate a monthly salary slip for the employee</p>
  <p><b>Then</b> it should contain the below:</p>
  
           Employee ID: 12345
           Employee Name: John J Doe
           Gross Salary: £2000.00
           National Insurance contributions: £159.40
           Tax-free allowance: £916.67
           Taxable income: £1083.33
           Tax payable: £216.67 
         
   **Note:** you do not need to use any formatting or a particular currency (for example: £) or even the exact labels for the fields like above. As long as the values are representative of the details of a salary slip above, distinct and human readable.

### Acceptance criteria:
- Salary slip generator should pass an employee with its Employee Id, Employee Name and Annual Gross Salary
- Salary slip should contain the Employee ID, Employee Name, Gross Salary, National Insurance, Tax-free allowance*, Taxable income and Tax payable for the month
- The entry point should be one of the following interfaces, which you CAN NOT change:
  ```java
  
      public class SalarySlipGenerator {
        public void generateFor(Employee employee);
      }
  
  ```    
    or
    
  ```java
  
      public class SalarySlipGenerator {
        public SalarySlip generateFor(Employee employee);
      }
  
  ```    
  You can, however, add **private** methods and fields to the `SalarySlipGenerator` class, change the constructor, and add extra classes.
  
  **Note:** The term 'Tax-free allowance' is interchangeably used with the term 'Personal Allowance' and 'Tax-free Personal Allowance' in this domain.
  
### Calculations: National Insurance contributions and Tax
 
   National Insurance contributions are made up of three bands. It is a deductible and computed as a sum of the contributions for every band the gross salary is applicable for.
   Further examples on how it is computed for various salaries can be found in the [Examples: National Insurance contributions and Tax calculations](#examples-national-insurance-contributions-and-tax-calculations) table 
   towards the lower power of this page. Below are the definitions of the bands for the UK:
      
   ```
         ---------------------+-------------------------+--------
         Band                 | NI deductible income    | NI Rate
         ---------------------+-------------------------+--------
         No contributions     | Up to £8,060.00         |    0%
         Basic contributions  | £8,060.00 to £43,000.00 |   12%
         Higher contributions | over £43,000.00         |    2%
         ---------------------+-------------------------+--------
   ```
   **Note:** for each of the above bands, NI deductible income is accounted for, starting from and including the first penny (1p) earned above the lower-limit, up to and including the higher-limit specified. 
 
  Tax payable is made up of four bands. It is a deductible and computed as a sum of the tax payable for every band the gross salary is applicable for.
  Further examples on how it is computed for various salaries can be found in the [Examples: National Insurance contributions and Tax calculations](#examples-national-insurance-contributions-and-tax-calculations) table 
  towards the lower power of this page. Below are the definitions of the bands for the UK:
     
   ```
         ---------------------+---------------------------+---------
         Band                 | Taxable income            | Tax rate
         ---------------------+---------------------------+---------
         Personal Allowance*  | Up to £11,000.00          |    0%
         Basic rate           | £11,000.00 to £43,000.00  |   20%
         Higher rate          | £43,000.00 to £150,000.00 |   40%
         Additional rate      | over £150,000.00          |   45%
         ---------------------+---------------------------+---------
   ```
   *past an Annual Gross Salary of £100,000.00, the personal allowance rules change i.e. for every extra £2 earned, the personal allowance reduces by £1. Past an annual gross salary of £122,000.00, NO personal allowance is applicable. And this excess is taxed at the Higher rate tax.

   **Note:** for each of the above bands, taxable income is accounted for, starting from and including the first penny (1p) earned above the lower-limit, up to and including the higher-limit specified.
   
   ```
        National Insurance contributions = sum of the contributions accumulated by the two contribution bands
       
        Tax payable = sum of all the tax payable accumulated by the three tax bands 
       
        Total deductibles = National Insurance contributions + Tax payable
   ```

### Examples: National Insurance contributions and Tax calculations

  See the [Examples: National Insurance contributions and Tax calculations](EXAMPLES.md) section for examples of the above mentioned NI and Tax calculations.   
 
### Resources
- [Sample Salary Slip](http://1.bp.blogspot.com/-lJXMuMQCGtE/Udm8dlTIeSI/AAAAAAAAA1Q/jLxBZndJTAA/s1600/Pay+Slip+Format.JPG)
- [Salary calculator](http://www.thesalarycalculator.co.uk/)
- [Tax breakdown calculator - ListenToTaxman](https://listentotaxman.com/122000?)
- [Tax breakdown calculator - HMRC](http://tools.hmrc.gov.uk/hmrctaxcalculator/screen/Personal+Tax+Calculator/en-GB/summary?user=guest)
- [National insurance rates](http://www.which.co.uk/money/tax/guides/national-insurance-explained/national-insurance-rates/)
- [HMRC - National insurance rates](https://www.gov.uk/guidance/rates-and-thresholds-for-employers-2016-to-2017)
