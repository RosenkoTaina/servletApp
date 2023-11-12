package com.servletApp.service;

import com.servletApp.repository.EmployeeDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletApp/*")
public class AppControllerServlet extends HttpServlet {

    private final EmployeeDao employeeDao = new EmployeeDao();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();  // Use getPathInfo to get the path after the servlet mapping

        switch (action) {
            case "/viewServlet":
                viewServlet(request, response);
                break;
            case "/registrationServlet":
                registrationServlet(request, response);
                break;
            case "/logoutServlet":
                logoutServlet(request, response);
                break;
            case "/deleteServlet":
                deleteServlet(request, response);
                break;
            case "/putServlet":
                putServlet(request, response);
                break;
            case "/saveServlet":
                saveServlet(request, response);
                break;
            case "/viewByIDServlet":
                viewByIDServlet(request, response);
                break;
            default:
                loginServlet(request, response);
                break;
        }
    }

    private void registrationServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/registrationServlet");
            dispatcher.forward(request, response);
    }

    private void loginServlet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/loginServlet");
            dispatcher.forward(request, response);
    }


    private void logoutServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logoutServlet");
        dispatcher.forward(request, response);
    }


    private void deleteServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/deleteServlet");
        dispatcher.forward(request, response);
    }

    private void putServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/putServlet");
        dispatcher.forward(request, response);

    }

    private void saveServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/saveServlet");
        dispatcher.forward(request, response);

    }

    private void viewByIDServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewByIDServlet");
        dispatcher.forward(request, response);

    }

    private void viewServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewServlet");
        dispatcher.forward(request, response);

    }
}










