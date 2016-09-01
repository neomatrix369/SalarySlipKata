Salary slip kata
================
- Objective:
	- Test application from outside, according to side effect - TDD Outside-in
	- Apply SOLID, Simple Design and other design principles where necessary or apply trade-offs instead
	- Use the nouns and verbs of the domain to name variables, fields, parameters, methods, classes and eventually the package hierarchy
	- Solution is extendable, components are modular and cohesive

- Problem description: 
	Salary slip generator for UK companies.

    - Create a Salary slip generator application with the following features:
        - Record someone's base salary
        - Record overtime
        - Record bonus
        - Print a salary slip at the end of the month
    
    - Bonus features:
        - Record an expense on the salary slip (before tax deduction)
        - Record a deduction on the salary slip (before tax deduction)
        - Salary slip can be printed to a PDF file (future feature)

    To keep the employee details anonymous we will only be using `employee id` as a way to refer to an employee.
    Which means salary additions and retrieval of salary slips will be via the use of the `employee id`.     
    
    A printed salary slip will look like the below:
    
    ##### Salary slip template
         
         Date: [Current date]      Salary for period: [Month Year]
         
         Employee ID: [EmpID]
         
         SALARY                    DEDUCTION
         Basic           £nnnn.nn  National Insurance     £nnn.nn
         ...                  ...  Tax                    £nnn.nn
         ...                  ...  ...                    ...
         
         Grand total     £nnnn.nn  Net payable           £nnnn.nn
         
    ##### Calculations
     
         Grand total = Basic + sum of all Allowances - sum of all Deductions
         National Insurance = Grand Total - applicable NI percentage
         Taxable Income = Grand Total - Personal Allowance 
         Tax = Taxable Income - applicable tax percentage
         Total deductables = National Insurance + Tax
         Net payable = Grand Total - Total Deductables
         
    ##### Simple salary slip

         Date: 01 September 2016   Salary for period: Sept. 2016
         
         Employee ID: 12345
         
         SALARY                    DEDUCTION
         Basic           £2000.00  National Insurance     £159.40
                                   Tax                    £216.67

         
         Grand total     £2000.00  Net payable           £1623.93

    ##### Salary slip with a bonus or overtime

         Date: 01 September 2016   Salary for period: Sept. 2016
         
         Employee ID: 12345
         
         SALARY                    DEDUCTION
         Basic           £2000.00  National Insurance     £279.40
         Bonus           £1000.00  Tax                    £416.67

         
         Grand total     £3000.00  Net payable           £2303.93
    
    ##### Mixed Salary slip (bonus, overtime earnings and loan deductions)

         Date: 01 September 2016   Salary for period: Sept. 2016
         
         Employee ID: 12345
         
         SALARY                    DEDUCTION
         Basic           £2000.00  Loan                   £200.00
         Bonus           £1000.00  National Insurance     £315.40
         Overtime         £500.00  Tax                    £467.67
         
         Grand total     £3300.00  Net payable           £2507.93

- Acceptance criteria:
	- Should be able to print someone's salary slip
	- All expenses are tax deductable
	- All deductions are made on the gross salary (before tax deductions)
	- All tax deductions must follow the below brackets for the UK: 
	```
		Band                 Taxable income        Tax rate
		Personal Allowance   Up to £11,000             0%
		Basic rate           £11,001 to £43,000       20%
		Higher rate          £43,001 to £150,000      40%
		Additional rate      over £150,000            45%
	```	
	- Tax is calculated on gross earnings above the earnings threshold	
	- National Insurance is calculated on gross earnings (before tax deductions) above the earnings threshold
	- National Insurance earnings threshold for the UK:
	 ```
      The National Insurance threshold is £155 a week or £620 a month or £8,060 a year
      If your earnings are below the earnings threshold you pay no National Insurance contributions
      If you earn above the threshold you pay 12% of your earnings between £8,060 and £43,000 on your annual salary or 2% on your monthly salary
     ```

- Resources
    - [Salary calculator](http://www.thesalarycalculator.co.uk/)
    - [National insurance rates](http://www.which.co.uk/money/tax/guides/national-insurance-explained/national-insurance-rates/)
    - [HMRC - National insurance rates](https://www.gov.uk/guidance/rates-and-thresholds-for-employers-2016-to-2017)
    - [Sample Salary Slip](http://1.bp.blogspot.com/-lJXMuMQCGtE/Udm8dlTIeSI/AAAAAAAAA1Q/jLxBZndJTAA/s1600/Pay+Slip+Format.JPG)
