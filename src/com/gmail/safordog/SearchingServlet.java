package com.gmail.safordog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SearchingServlet", urlPatterns = "/search")
public class SearchingServlet extends HttpServlet {

    private String msg = "";
    ArticleServlet as = new ArticleServlet();
    RegistrationServlet rs = new RegistrationServlet();
    private String template = rs.getTemplate() + "%s";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String author = req.getParameter("author");
        msg = "";
        for (Article art : as.getArticles()) {
            if (art.getUserName().equals(author)) {
                msg += "<br><h3 class=\"word\">" + art.getContent() + "</h3><br>" + art.getUserName() + "<br>" + art.getDate() + "<br><br><br>";
            }
        }
        resp.getWriter().print(String.format(template, msg));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String print = rs.getTemplate() + "<form action=\"/search\" method=\"post\" class=\"search\">"
                + "<br>Enter the name of the author:<input type=\"text\" name=\"author\"/>"
                + "<input type=\"submit\" value=\"search\"/></form></div></div>"
                + "</fieldset>ProJAVA Blog</body></html>";
        resp.getWriter().print(print);
    }
}
