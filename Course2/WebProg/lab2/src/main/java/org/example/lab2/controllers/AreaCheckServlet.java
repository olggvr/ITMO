package org.example.lab2.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.lab2.models.Point;
import org.example.lab2.repository.PointsRepository;

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
        try{
            int x = Integer.parseInt(req.getParameter("x"));
            double y = Double.parseDouble(req.getParameter("y"));
            double r = Double.parseDouble(req.getParameter("r"));
            boolean result = checkArea(x, y, r);

            Point point = new Point(x, y, r, result);
            repositoryCheck(req, resp, point);

            req.setAttribute("result", point.result());
            req.getRequestDispatcher("result.jsp").forward(req, resp);
        }catch (NumberFormatException | NullPointerException | IllegalStateException e){
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    private boolean checkArea(int x, double y, double r) {
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
            return !(x < -r) && !(y > r);
        }
        return true;
    }

    private void repositoryCheck(HttpServletRequest req, HttpServletResponse resp, Point point){
        var session = req.getSession();
        PointsRepository repo = (PointsRepository) session.getAttribute("repo");
        if (repo == null) {
            repo = new PointsRepository();
            session.setAttribute("repo", repo);
        }
        repo.addPoint(point);
    }

}