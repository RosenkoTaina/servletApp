package com.rosenko.demo.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        String userID = "admin";
        String password = "password";

        try (PrintWriter out = response.getWriter()) {
            if (userID.equals(user) && password.equals(pwd)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", "user");
                session.setMaxInactiveInterval(30 * 60);
                Cookie userName = new Cookie("user", user);
                userName.setMaxAge(30 * 60);
                response.addCookie(userName);
                out.println("Welcome back to the team, " + user + "!");
            } else {
                out.println("Either user name or password is wrong!");
            }
        }
    }
}