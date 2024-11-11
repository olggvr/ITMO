package org.example.lab2.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String xParam = req.getParameter("x");
        String yParam = req.getParameter("y");
        String rParam = req.getParameter("r");

        req.setAttribute("x", Integer.parseInt(xParam));
        req.setAttribute("y", Integer.parseInt(yParam));
        req.setAttribute("r", Float.parseFloat(rParam));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/check");
        dispatcher.forward(req, resp);
    }

}
