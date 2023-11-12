package com.servletApp.session;

import com.servletApp.entity.Employee;
import com.servletApp.repository.EmployeeDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registrationServlet")
public class RegistrationServlet extends HttpServlet {

    private final EmployeeDao employeeDao = new EmployeeDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String password = request.getParameter("password");
        String role = "USER";

        Employee newEmployee = new Employee(name, email, country, password, role);


        if (employeeDao.isEmailRegistered(email)) {
           response.sendRedirect(request.getContextPath() + "/registration.jsp?error=email");
        } else {
           int status = employeeDao.saveUser(newEmployee);
            if (status > 0) {
               response.sendRedirect(request.getContextPath() + "/login.jsp");
            } else {
               response.sendRedirect(request.getContextPath() + "/registration.jsp?error=registration");
            }
        }
    }
}