package com.servletApp.session;

import com.servletApp.entity.Employee;
import com.servletApp.filters.AuthenticationHelper;
import com.servletApp.repository.EmployeeDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Optional;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EmployeeDao employeeDao = new EmployeeDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Authenticate the user
        Optional<Employee> optionalEmployee = employeeDao.authenticate(email, password);

        if (optionalEmployee.isPresent()) {
            // User is authenticated
            AuthenticationHelper.handleSuccessfulLogin(request, response, email);
        } else {
            // User authentication failed
            AuthenticationHelper.handleUnsuccessfulLogin(request, response);
            response.sendRedirect(request.getContextPath() + "/registration.jsp");
        }
    }
}




