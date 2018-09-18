package com.gmail.safordog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

@WebServlet(name = "SortingServlet", urlPatterns = "/sort")
public class SortingServlet extends HttpServlet {

    private String msg = "";
    RegistrationServlet rs = new RegistrationServlet();
    private String template = rs.getTemplate() + "%s";

    ArticleServlet as = new ArticleServlet();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("sort").equals("author")) {
            msg = "";
            Collections.sort(as.getArticles(), new Comparator<Article>() {
                @Override
                public int compare(Article o1, Article o2) {
                    return o1.getUserName().compareTo(o2.getUserName());
                }
            });
            for (Article art : as.getArticles()) {
                msg += "<br><h3 class=\"word\">" + art.getContent() + "</h3><br>" + art.getUserName() + "<br>"
                        + art.getDate() + "<br><br><br>";
            }
        } else if (req.getParameter("sort").equals("date")) {
            msg = "";
            Collections.sort(as.getArticles(), new Comparator<Article>() {
                @Override
                public int compare(Article o1, Article o2) {
                    return o2.getDate().compareTo(o1.getDate());
                }
            });
            for (Article art : as.getArticles()) {
                msg += "<br><h3 class=\"word\">" + art.getContent() + "</h3><br>" + art.getUserName() + "<br>"
                        + art.getDate() + "<br><br><br>";
            }
        }
        resp.getWriter().print(String.format(template, msg));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String print = rs.getTemplate() + "<form action=\"/sort\" method=\"post\" class=\"search\">"
                + "<br><br>sorting by author:<input type=\"radio\" name=\"sort\" value=\"author\"/>"
                + "<br>sorting by date:<input type=\"radio\" name=\"sort\" value=\"date\"/>"
                + "<br><input type=\"submit\" value=\"sort\"/></form></div></div>"
                + "</fieldset>ProJAVA Blog</body></html>";
        resp.getWriter().print(print);
    }

    public void sortByDate() {
        Collections.sort(as.getArticles(), new Comparator<Article>() {
            public int compare(Article o1, Article o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
    }

}
