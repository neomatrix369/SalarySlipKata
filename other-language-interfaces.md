### Examples of interfaces for other languages

The entry point should be one of the following interfaces, which you CANNOT change:

  **Scala**
  ```scala
    class SalarySlipGenerator {
      def generateFor(employee: Employee)
    }
                ----- or ----
    class SalarySlipGenerator {
      def generateFor(employee: Employee): SalarySlip 
    }
  ```
  
  **Clojure**
  ```clojure
    <--- any suggestions? --->
  ```
  
  **Javascript**
  ```javascript
    class SalarySlipGenerator {
      void generateFor(Employee employee);
    }
                ----- or ----
    class SalarySlipGenerator {
      SalarySlip generateFor(Employee employee);
    }
  ```

  **Python**
  ```python
    class SalarySlipGenerator: 
        def generateFor(employee):
    
                  ----- or ----
    class SalarySlipGenerator:
        def generateFor(employee):
  ```  
  
  **Ruby**
  ```ruby
    class SalarySlipGenerator 
        def generateFor(employee)
        end
    
               ----- or ----
    class SalarySlipGenerator
        def generateFor(employee)
        end
    ```

  **C#**
  ```csharp
    public class SalarySlipGenerator {
      public void generateFor(Employee employee);
    }
                ----- or ----
    public class SalarySlipGenerator {
      public SalarySlip generateFor(Employee employee);
    }
  ```

You can, however, add **private** methods and fields to the `SalarySlipGenerator` class, change the constructor, and add extra classes.
