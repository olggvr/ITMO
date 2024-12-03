package org.example.lab2.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/save-square")
public class SaveSquareServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        String x = req.getParameter("x");
        String y = req.getParameter("y");
        String width = req.getParameter("width");
        String height = req.getParameter("height");

        HttpSession session = req.getSession();
        session.setAttribute("x", x);
        session.setAttribute("y", y);
        session.setAttribute("width", width);
        session.setAttribute("height", height);

        resp.setStatus(HttpServletResponse.SC_OK);
    }



}
