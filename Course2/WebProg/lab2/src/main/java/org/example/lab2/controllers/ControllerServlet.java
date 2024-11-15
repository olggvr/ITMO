package org.example.lab2.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.lab2.models.Validator;

import java.io.IOException;
import java.io.PrintWriter;

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
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String x = req.getParameter("x");
        String y = req.getParameter("y");
        String r = req.getParameter("r");
        HttpSession session = req.getSession();

        try {
            if (Validator.validateIsNull(x, y, r)) {
                sendError(resp, session, String.format(ERROR_MSG, "x, y and r are required"));
            } else if (Validator.validateIsEmpty(x, y, r)) {
                sendError(resp, session, String.format(ERROR_MSG, "x, y, and r should not be empty"));
            } else if (!Validator.isCorrectDiapason(Integer.parseInt(x), Double.parseDouble(y), Double.parseDouble(r))) {
                sendError(resp, session, String.format(ERROR_MSG, "Incorrect range of variables"));
            }
        } catch (NumberFormatException e) {
            sendError(resp, session, String.format(ERROR_MSG, "Number format error: " + e.getMessage()));
        } catch (NullPointerException e) {
            sendError(resp, session, String.format(ERROR_MSG, "Null value error: " + e.getMessage()));
        }
    }

    private void sendError(HttpServletResponse resp, HttpSession session, String message) throws IOException {
        session.setAttribute("error", message);
        resp.setStatus(400);
        resp.sendRedirect("./error.jsp");
    }
}