Salary slip specification
=========================

### Problem description: Salary slip generator for UK companies (Tax year 2016-17)
 
  A typical salary slips contains employee details like employee id, employee name and their monthly salary details like their gross salary, national insurance contributions, tax-free allowance*, taxable income and tax payable.
  
  Salary slips are generated each month for every employee.
   
  **Note:** the tables used for calculating the National Insurance contributions and Taxes are for the year (April 2016/17). Also note that when the Annual Gross Salary exceeds £100,000.00, personal allowance rules do not apply. Instead personal allowance decreases by a £1 for every £2 earned over £100,000.00. And this excess is taxed at the Higher rate tax.

  See [Calculations: National Insurance contributions and Tax](#calculations-national-insurance-contributions-and-tax) and [Examples: National Insurance contributions and Tax calculations](#examples-national-insurance-contributions-and-tax-calculations) for further information on the breakdown.

### Scenario 1: print a salary slip with employee details for an employee (annual gross salary of £24,000.00)
    
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
   See [Acceptance criteria](#acceptance-criteria) before proceeding. 

### Scenario 2: print a salary slip with employee details for an employee (annual gross salary of £45,000.00)
    
  <p><b>Given</b> I have an employee John J Doe with an annual gross salary of £45,000.00</p>
  <p><b>When</b> I generate a monthly salary slip for the employee</p>
  <p><b>Then</b> it should contain the below:</p>
  
           Employee ID: 12345
           Employee Name: John J Doe
           Gross Salary: £3750.00
           National Insurance contributions: £352.73
           Tax-free allowance: £916.67
           Taxable income: £2,833.33
           Tax payable: £600.00       
   
   **Note:** you do not need to use any formatting or a particular currency (for example: £) or even the exact labels for the fields like above. As long as the values are representative of the details of a salary slip above, distinct and human readable.
   See [Acceptance criteria](#acceptance-criteria) before proceeding. 

### Scenario 3: print a salary slip with employee details for an employee (annual gross salary of £101,000.00)
    
  <p><b>Given</b> I have an employee John J Doe with an annual gross salary of £101,000.00</p>
  <p><b>When</b> I generate a monthly salary slip for the employee</p>
  <p><b>Then</b> it should contain the below:</p>
  
           Employee ID: 12345
           Employee Name: John J Doe
           Gross Salary: £8,416.67
           National Insurance contributions: £446.07
           Tax-free allowance: £875.00
           Taxable income: £7,541.67
           Tax payable: £2,483.33 
   
   **Note:** you do not need to use any formatting or a particular currency (for example: £) or even the exact labels for the fields like above. As long as the values are representative of the details of a salary slip above, distinct and human readable.
   See [Acceptance criteria](#acceptance-criteria) before proceeding.

### Scenario 4: bonus points to able to handle some tricky challenges and situations in the specification
   
   - You will see from the Calculations and Examples sections that when annual gross salary goes past £100,000 the calculations become more challenging. How will you tackle this issue from software design point of view?
   - Have you checked the accuracy of the calculations so far up to the last penny with the provided below [Resources](#resources), are they all matching up with our results for tax year 2016-17 (output of the implementation and test cases)?
   
   **Note:** this is a due-diligence scenario, no salary slips need printing but requires verification and validation of the developed solution up to scenario 3. 
   
### Acceptance criteria:
- Salary slip generator should pass an employee with its Employee Id, Employee Name and Annual Gross Salary
- Salary slip should contain the Employee ID, Employee Name, Gross Salary, National Insurance, Tax-free allowance*, Taxable income and Tax payable for the month
- The entry point should be one of the following interfaces, which you CANNOT change:
  
  **Java** 
  ```java  
    public class SalarySlipGenerator {
        public void generateFor(Employee employee);
    }
              ----- or ----
    public class SalarySlipGenerator {
        public SalarySlip generateFor(Employee employee);
    }
  ```
  
  You can, however, add **private** methods and fields to the `SalarySlipGenerator` class, change the constructor, and add extra classes.
      
  **Note:** The term 'Tax-free allowance' is interchangeably used with the term 'Personal Allowance' and 'Tax-free Personal Allowance' in this domain.
  
  #### See examples of [interfaces for other programming languages](other-language-interfaces.md).  
  
### Calculations: National Insurance contributions and Tax

  See the [Calculations: National Insurance contributions and Tax](CALCULATIONS.md) section to find out what the calculations tables for both National Insurance and Tax calculations. 

### Examples: National Insurance contributions and Tax calculations

  See the [Examples: National Insurance contributions and Tax calculations](EXAMPLES.md) section for examples of the above mentioned National Insurance and Tax calculations.   
 
### Resources

- [Sample Salary Slip](http://1.bp.blogspot.com/-lJXMuMQCGtE/Udm8dlTIeSI/AAAAAAAAA1Q/jLxBZndJTAA/s1600/Pay+Slip+Format.JPG)
- [Salary calculator](http://www.thesalarycalculator.co.uk/)
- [Tax breakdown calculator - ListenToTaxman](https://listentotaxman.com/122000?)
- [Tax breakdown calculator - HMRC](http://tools.hmrc.gov.uk/hmrctaxcalculator/screen/Personal+Tax+Calculator/en-GB/summary?user=guest)
- [National insurance rates](http://www.which.co.uk/money/tax/guides/national-insurance-explained/national-insurance-rates/)
- [HMRC - National insurance rates](https://www.gov.uk/guidance/rates-and-thresholds-for-employers-2016-to-2017)

Go back to [README.md](README.md).
