package org.example.lab2.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/check")
public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int x = Integer.parseInt(req.getParameter("x"));
        int y = Integer.parseInt(req.getParameter("y"));
        float r = Float.parseFloat(req.getParameter("r"));

        boolean result = checkArea(x, y, r);

        req.setAttribute("x", x);
        req.setAttribute("y", y);
        req.setAttribute("r", r);
        req.setAttribute("result", result);

        RequestDispatcher dispatcher = req.getRequestDispatcher("result.jsp");
        dispatcher.forward(req, resp);
    }

    private boolean checkArea(int x, int y, float r) {
        if (x > 0 && y > 0) {
            return false;
        }
        if (x > 0 && y < 0) {
            if (x - y > r/2){
                return false;
            }
        }
        if (x < 0 && y < 0) {
            if (x*x + y*y > r) {
                return false;
            }
        }
        if (x < 0 && y > 0) {
            if (x < -r || y > r){
                return false;
            }
        }
        return true;
    }

}