package org.example.lab2.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.lab2.models.Point;
import org.example.lab2.repository.PointsRepository;

import java.io.IOException;

@WebServlet("/check")
public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try{
            int x = Integer.parseInt(req.getParameter("x"));
            double y = Double.parseDouble(req.getParameter("y"));
            double r = Double.parseDouble(req.getParameter("r"));
            boolean result = checkArea(x, y, r);

            Point point = new Point(x, y, r, result);
            var session = req.getSession();
            PointsRepository repo = repositoryCheck(session, point);

            session.setAttribute("repo", repo);
            resp.sendRedirect("./result.jsp");
        }catch (NumberFormatException | NullPointerException | IllegalStateException e){
            resp.sendRedirect("./index.jsp");
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

    private PointsRepository repositoryCheck(HttpSession session, Point point){
        PointsRepository repo = (PointsRepository) session.getAttribute("repo");
        if (repo == null) {
            repo = new PointsRepository();
            session.setAttribute("repo", repo);
        }
        repo.addPoint(point);

        return repo;
    }

}