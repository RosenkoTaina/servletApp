package com.servletApp.filters;


import com.servletApp.entity.Employee;
import com.servletApp.repository.EmployeeDao;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;


@WebFilter("/authenticationFilter")
public class AuthenticationFilter implements Filter {

    private ServletContext context;
    private EmployeeDao employeeDao;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log(">>> AuthenticationFilter initialized");
        this.employeeDao = new EmployeeDao();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        this.context.log("Requested Resource::http://localhost:8080" + uri);

        HttpSession session = req.getSession(false);

        if (session == null && !(
                uri.startsWith(req.getContextPath() + "/servletApp/saveServlet") ||
                        uri.startsWith(req.getContextPath() + "/servletApp/viewByIDServlet") ||
                        uri.startsWith(req.getContextPath() + "/servletApp/loginServlet") ||
                        uri.startsWith(req.getContextPath() + "/servletApp/logoutServlet") ||
                        uri.startsWith(req.getContextPath() + "/servletApp/deleteServlet") ||
                        uri.startsWith(req.getContextPath() + "/servletApp/viewServlet"))) {

            String email = req.getParameter("email");
            String password = req.getParameter("password");

            // Authenticate the user
            Optional<Employee> optionalEmployee = employeeDao.authenticate(email, password);

            if (optionalEmployee.isPresent()) {

                session = req.getSession();
                session.setAttribute("email", email);
                session.setMaxInactiveInterval(30 * 60);

                res.sendRedirect(req.getContextPath() + "/welcome.jsp");
            } else {
                AuthenticationHelper.handleUnsuccessfulLogin(req, res);
                res.sendRedirect(req.getContextPath() + "/registration.jsp");
            }
        }
    }


    public void destroy() {
        // Close any resources here
    }
}

