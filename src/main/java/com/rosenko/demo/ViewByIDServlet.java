package com.rosenko.demo;


import com.rosenko.demo.entity.Employee;
import com.rosenko.demo.repository.EmployeeRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;


@WebServlet("/viewByIDServlet")
public class ViewByIDServlet extends HttpServlet {

    private final EmployeeRepository employeeRepository = new EmployeeRepository();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        Optional<Employee> employee = employeeRepository.getEmployeeById(id);

        if (employee.isPresent()) {
            out.print(employee.get());
        } else {
            out.print("Employee not found");
        }

        out.close();
    }
}