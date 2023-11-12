import com.servletApp.entity.Employee;
import com.servletApp.repository.EmployeeDao;


public class Main {

    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDao();

        Employee employee = new Employee("John Doe", "john.doe@example.com", "Germany", "password", "user");
        int status = employeeDao.saveUser(employee);

        if (status > 0) {
            System.out.println("Employee saved successfully.");
        } else {
            System.out.println("Failed to save employee.");
        }
    }
}
