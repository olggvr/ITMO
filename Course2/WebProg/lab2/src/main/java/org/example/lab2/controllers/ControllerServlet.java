package org.example.lab2.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.lab2.models.Validator;

import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    private static final String ERROR_MSG = "Incorrect data: %s";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
        req.getRequestDispatcher("/check").forward(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try{
            String x = req.getParameter("x");
            String y = req.getParameter("y");
            String r = req.getParameter("r");

            if (Validator.validateIsNull(x, y, r)) {
                req.setAttribute("error", String.format(ERROR_MSG, "x, y and r are required"));
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }

            if (Validator.validateIsEmpty(x, y, r)) {
                req.setAttribute("error", String.format(ERROR_MSG, "x, y, r should not be empty"));
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }

            if (!Validator.isCorrectDiapason(Integer.parseInt(x), Double.parseDouble(y), Double.parseDouble(r))) {
                req.setAttribute("error", String.format(ERROR_MSG, "incorrect diapason of variables"));
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }catch (NumberFormatException | NullPointerException e){
            req.setAttribute("error", e.toString());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

}
