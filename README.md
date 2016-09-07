Salary slip kata
================
- Problem description: Salary slip generator application for UK companies.
 
  A typical salary slips contains employee details like employee id, employee name and their salary details like their basic salary, gross salary, national insurance contributions, and taxes to be paid.
  
  In the event there are earnings like overtime and bonuses or deductions like loans, these are accounted for in the respective aspects of the salary slip and have an impact on the gross salary, national insurance contributions, and taxes to be paid.
  
  Salary slips are generated each month for every employee. 

### Scenario 1: add employee details and print a salary slip
    
  Given I have added an employee
  When I print the salary slip
  Then it should display the Date, Salary period, Employee ID, Employee Name and Basic Salary 

- Acceptance criteria:
    - Should accept EmployeeId, Employee Name and Basic Salary
    - Should print an employee's salary slip
    - The entry point should be the following interface, which you can not change:    
      ```java
      
          public class SalarySlipGenerator {
              public void addSalaryItem(EmployeeId employeeId, SalaryItem salaryItem, String salaryPeriod);
              public void printFor(EmployeeId employeeId, String salaryPeriod);
          }
      
      ``` 
    - The resulting output must be in the below format: 
      ```
         Date: 01 Sep 2016             Salary for period: Sep 2016
    
         Employee ID: 12345            Employee Name: John J Doe
    
         EARNINGS                                  
         Basic            £2000.00
      ```
 
- Resources
    - [Sample Salary Slip](http://1.bp.blogspot.com/-lJXMuMQCGtE/Udm8dlTIeSI/AAAAAAAAA1Q/jLxBZndJTAA/s1600/Pay+Slip+Format.JPG)
    - [Salary calculator](http://www.thesalarycalculator.co.uk/)
    - [Tax breakdown calculator](http://tools.hmrc.gov.uk/hmrctaxcalculator/screen/Personal+Tax+Calculator/en-GB/summary?user=guest)
    - [National insurance rates](http://www.which.co.uk/money/tax/guides/national-insurance-explained/national-insurance-rates/)
    - [HMRC - National insurance rates](https://www.gov.uk/guidance/rates-and-thresholds-for-employers-2016-to-2017)
