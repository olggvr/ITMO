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

        req.setAttribute("fromController", "true");
        processRequest(req, resp);
        if(!resp.isCommitted()){
            req.getRequestDispatcher("./check").forward(req, resp);
        }
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json");

        String x = req.getParameter("x");
        String y = req.getParameter("y");
        String r = req.getParameter("r");
        HttpSession session = req.getSession();

        if (Validator.validateIsNull(x, y, r)) {
            sendError(resp, session, String.format(ERROR_MSG, "x, y and r are required"));
        } else if (Validator.validateIsEmpty(x, y, r)) {
            sendError(resp, session, String.format(ERROR_MSG, "x, y, and r should not be empty"));
        } else if (!Validator.isCorrectDiapason(Integer.parseInt(x), Double.parseDouble(y), Double.parseDouble(r))) {
            sendError(resp, session, String.format(ERROR_MSG, "incorrect range of variables"));
        } else if (!Validator.isCorrectR(Double.parseDouble(r))){
            sendError(resp, session, String.format(ERROR_MSG, "incorrect range of r"));
        }
    }

    private void sendError(HttpServletResponse resp, HttpSession session, String message) throws IOException {
        session.setAttribute("error", message);
        resp.setStatus(400);

        resp.sendRedirect("./error.jsp");
    }
}