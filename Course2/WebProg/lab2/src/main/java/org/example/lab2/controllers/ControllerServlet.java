package org.example.lab2.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.lab2.models.Validator;

import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    private static final String ERROR_MSG = "Incorrect data: %s";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
        req.getRequestDispatcher("./check").forward(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try{
            String x = req.getParameter("x");
            String y = req.getParameter("y");
            String r = req.getParameter("r");
            HttpSession session = req.getSession();

            if (Validator.validateIsNull(x, y, r)) {
                session.setAttribute("message", String.format(ERROR_MSG, "x, y and r are required"));
                resp.sendRedirect("./error.jsp");
            }

            if (Validator.validateIsEmpty(x, y, r)) {
                session.setAttribute("message", String.format(ERROR_MSG, "x, y, r should not be empty"));
                resp.sendRedirect("./error.jsp");
            }

            if (!Validator.isCorrectDiapason(Integer.parseInt(x), Double.parseDouble(y), Double.parseDouble(r))) {
                session.setAttribute("message", String.format(ERROR_MSG, "incorrect diapason of variables"));
                resp.sendRedirect("./error.jsp");
            }
        }catch (NumberFormatException | NullPointerException e){
            req.setAttribute("error", e.toString());
            resp.sendRedirect("./error.jsp");
        }
    }

}
