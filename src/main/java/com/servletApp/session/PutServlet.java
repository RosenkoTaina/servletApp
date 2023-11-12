package com.servletApp.session;


import com.servletApp.entity.Employee;
import com.servletApp.repository.EmployeeDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/putServlet")
public class PutServlet extends HttpServlet {
    private final EmployeeDao employeeDao = new EmployeeDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("hashed_password");
        String role = request.getParameter("role");

        Employee employee = new Employee(id, name, email, password, role, request.getParameter("country"));

        int status = employeeDao.updateUser(employee);
        if (status > 0) {
            response.sendRedirect("New user was added" + employee);
        } else {
            out.println("Sorry! unable to update record");
        }
        out.close();
    }
}