package com.gmail.safordog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteArticleServlet", urlPatterns = "/delete")
public class DeleteArticleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleServlet as = new ArticleServlet();
        String content = req.getParameter("text");
        String iD = req.getParameter("ID");
        for (int i = 0; i < as.getArticles().size(); i++) {
            if (as.getArticles().get(i).getiD().equals(iD)) {
                as.getArticles().remove(as.getArticles().get(i));
            }
        }
        as.saveArticles();
        req.setAttribute("deleteMessage", "The article was deleted.");
        req.getRequestDispatcher("index_response.jsp").forward(req, resp);
    }

}
