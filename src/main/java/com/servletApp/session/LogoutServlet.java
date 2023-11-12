package com.servletApp.session;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession(false);
            if (session != null) {

                session.removeAttribute("email");
                session.removeAttribute("password");
                session.invalidate();
            }

            deleteCookie(response, "user");

            response.sendRedirect(request.getContextPath());
        }

        private void deleteCookie(HttpServletResponse response, String name) {
            Cookie cookie = new Cookie(name, "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }


