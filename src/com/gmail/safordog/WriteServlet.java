package com.gmail.safordog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WriteServlet", urlPatterns = "/edit")
public class WriteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleServlet as = new ArticleServlet();
        String content = req.getParameter("text");
        String iD = req.getParameter("ID");
        for (Article art : as.getArticles()) {
            if (art.getiD().equals(iD)) {
                art.setContent(content);
            }
        }
        as.saveArticles();
        req.setAttribute("editMessage", "The changes were saved.");
        req.getRequestDispatcher("index_response.jsp").forward(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
