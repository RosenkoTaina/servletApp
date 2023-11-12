package com.servletApp.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/servletApp/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String email = request.getParameter("email");
            String password = request.getParameter("password");


            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("password", password);


            request.getRequestDispatcher("/authenticationFilter").forward(request, response);

            response.sendRedirect(request.getContextPath() + "/welcome.jsp");
        }
    }




