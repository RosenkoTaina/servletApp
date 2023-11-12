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



@WebFilter("servletApp/*")
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
                uri.startsWith(req.getContextPath() + "/saveServlet") ||
                uri.startsWith(req.getContextPath() + "/viewByIDServlet") ||
                uri.startsWith(req.getContextPath() + "/loginServlet") ||
                uri.startsWith(req.getContextPath() + "/logoutServlet") ||
                uri.startsWith(req.getContextPath() + "/deleteServlet") ||
                uri.startsWith(req.getContextPath() + "/viewServlet"))) {

            // Redirect to welcome page if not logged in
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        // If the user is not logged in, display welcome page with login/register options
        if (session == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // User is logged in, continue with the request
        chain.doFilter(request, response);
    }



    public void destroy() {
        // Close any resources here
    }



}

