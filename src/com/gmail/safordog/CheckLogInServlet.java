package com.gmail.safordog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;

@WebServlet(name = "CheckLogInServlet", urlPatterns = "/my_cabinet")
public class CheckLogInServlet extends HttpServlet {

    final static String TEXT = "<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"/web../../../../styles/reset.css\">"
            + "<link rel=\"stylesheet\" type=\"text/css\" href=\"/web../../../../styles/styles.css\">"
            + "<title>ProgBlog - Title</title></head><body><div id=\"header\" class=\"title\">"
            + "<div id=\"problog_title\"><a href=\"/index.jsp\"><p>ProJava-BLOG</p></a>"
            + "<p><my>public static void main(String[] args) {&#160&#160&#160&#160&#160...&#160&#160&#160..1001&#160&#160&#160...110110011</my></p>"
            + "<p><my1>System.out.println(\"ProJava-BLOG\");&#160&#160&#160&#160...100011010...</my1></p>"
            + "<p><my2>}&#160&#160&#160&#160&#160...1011101110</my2></p></div><div id=\"image_title\">"
            + "<img src=\"/images/java.png\" alt=\"java\"></div></div><div id=\"navi\" class=\"navi\">"
            + "<div id=\"my_cabinet\"><a href=\"/my_cabinet\"><p>my cabinet</p></a></div>"
            + "<div id=\"search\"><a href=\"/search\"><p>searching articles</p></a>"
            + "</div><div id=\"sort\"><a href=\"/sort\"><p>sorting articles</p></a></div><div id=\"registration\">"
            + "<a href=\"/registration\"><p>login / registration</p></a></div></div><fieldset>"
            + "<div id=\"content\"><div id=\"article\"><div class=\"my_key_new\"><form action=\"/write\" method=\"get\">"
            + "<input type=\"submit\" value=\"create new article\"/>"
            + "</form></div>%s</div></div></fieldset>ProJAVA Blog</body></html>";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            HttpSession session = req.getSession();
            AddUserServlet aus = new AddUserServlet();
            ArticleServlet as = new ArticleServlet();
            String msg = "";
            if (aus.getUsers().containsKey(session.getAttribute("login"))) {
                for (Article temp : as.getArticles()) {
                    if (session.getAttribute("login").equals("admin")) {
                        msg += "<form action=\"/edit\" method=\"post\"><br><textarea name=\"text\">" + temp.getContent() + "</textarea><br>"
                                + "<textarea name=\"ID\" readonly class=\"article_ID\">" + temp.getiD() + "</textarea>"
                                + temp.getUserName() + "<br>" + temp.getDate() + "<br>"
                                + "<div class=\"my_key\"><input type=\"submit\" value=\"edit this article\" name=\"edit\"/><input type=\"submit\""
                                + "value=\"delete this article\" name=\"delete\" formaction=\"/delete\" formmethod=\"post\"/></form></div><br><br><br>";
                    } else if (temp.getUserName().equals((session.getAttribute("login")))) {
                        msg += "<form action=\"/edit\" method=\"post\"><br><textarea name=\"text\">" + temp.getContent() + "</textarea><br>"
                                + "<textarea name=\"ID\" readonly class=\"article_ID\">" + temp.getiD() + "</textarea>"
                                + temp.getUserName() + "<br>" + temp.getDate() + "<br>"
                                + "<div class=\"my_key\"><input type=\"submit\" value=\"edit this article\" name=\"edit\"/><input type=\"submit\""
                                + "value=\"delete this article\" name=\"delete\" formaction=\"/delete\" formmethod=\"post\"/></form></div><br><br><br>";
                    }
                }
                resp.getWriter().println(String.format(TEXT, msg));
            } else {
                req.setAttribute("noCabinetMessage", "Register or Log-in to access here");
                req.getRequestDispatcher("index_response.jsp").forward(req, resp);
            }
    }

}
