import com.rosenko.demo.entity.Employee;
import com.rosenko.demo.repository.EmployeeRepository;

public class Main {

    public static void main(String[] args) {
        EmployeeRepository employeeRepository = new EmployeeRepository();

        Employee employee = new Employee("John Doe", "john.doe@example.com", "Germany");
        int status = employeeRepository.save(employee);

        if (status > 0) {
            System.out.println("Employee saved successfully.");
        } else {
            System.out.println("Failed to save employee.");
        }
    }
}
