package org.example.lab2.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/save-square")
public class SaveSquareServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
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
