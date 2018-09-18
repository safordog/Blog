package com.gmail.safordog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@WebServlet(name = "ArticleServlet", urlPatterns = "/write")
public class ArticleServlet extends HttpServlet {

    private static List<Article> articles = new ArrayList<>();

    static final String TEXT = "<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"/web../../../../styles/reset.css\">"
            + "<link rel=\"stylesheet\" type=\"text/css\" href=\"/web../../../../styles/styles.css\">"
            + "<title>ProgBlog - Title</title></head><body><div id=\"header\" class=\"title\">"
            + "<div id=\"problog_title\"><a href=\"index.jsp\"><p>ProJava-BLOG</p></a>"
            + "<p><my>public static void main(String[] args) {&#160&#160&#160&#160&#160...&#160&#160&#160..1001&#160&#160&#160...110110011</my></p>"
            + "<p><my1>System.out.println(\"ProJava-BLOG\");&#160&#160&#160&#160...100011010...</my1></p>"
            + "<p><my2>}&#160&#160&#160&#160&#160...1011101110</my2></p></div><div id=\"image_title\">"
            + "<img src=\"/images/java.png\" alt=\"java\"></div></div><div id=\"navi\" class=\"navi\">"
            + "<div id=\"my_cabinet\"><a href=\"/my_cabinet\"><p>my cabinet</p></a></div>"
            + "<div id=\"search\"><a href=\"/search\"><p>searching articles</p></a>"
            + "</div><div id=\"sort\"><a href=\"/sort\"><p>sorting articles</p></a></div><div id=\"registration\">"
            + "<a href=\"/registration\"><p>login / registration</p></a></div></div><fieldset>"
            + "<div id=\"content\"><div id=\"article\"><p><form action=\"/write\" method=\"post\">\n" +
            "    <p><b>Input an article:</b></p>\n" +
            "    <p><textarea rows=\"30\" cols=\"70\" name=\"text\"></textarea></p>\n" +
            "    <p><input type=\"submit\" value=\"save\" id=\"save\" name=\"save\"></p>\n" +
            "  </form></p></div></div></fieldset>$END$</body></html>";

    public static List<Article> getArticles() {
        return articles;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Article art = new Article();
        String a = req.getParameter("text");
        art.setContent(a);
        Date d = new Date();
        art.setDate(d.toString());
        art.setUserName((String) session.getAttribute("login"));
        art.setName((String) session.getAttribute("name"));
        int id = (int) (Math.random() * 100000000);
        art.setiD(Integer.toString(id));
        articles.add(art);
        saveArticles();
        req.setAttribute("savedMessage", "Your article is saved");
        req.getRequestDispatcher("index_response.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(TEXT);
    }

    public void printArticles() {
        for (Article art : articles) {
            System.out.println(art.getContent() + "; " + art.getName() + "; " + art.getUserName() + "; " + art.getDate());
        }
    }

    public void saveArticles() throws IOException {
        File save = new File("/home/safordog/IdeaProjects/ProgBlog/article.txt");
        if (!save.exists()) {
            save.createNewFile();
        }
        try (PrintWriter pw = new PrintWriter(save)) {
            for (int i = 0; i < articles.size(); i++) {
                if (articles.get(i) != null) {
                    pw.print(articles.get(i).getContent() + "~" + articles.get(i).getName()
                            + "~" + articles.get(i).getUserName() + "~" + articles.get(i).getDate() + "~" + articles.get(i).getiD());
                    pw.println();
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public List<Article> getArticlesFromFile() {
        String str = "";
        SortingServlet ss = new SortingServlet();
        File file = new File("/home/safordog/IdeaProjects/ProgBlog/article.txt");
        try (Scanner sc = new Scanner(file)) {
            for (; sc.hasNextLine();) {
                str = sc.nextLine();
                String[] arrStr = str.split("~");
                String content = arrStr[0];
                String name = arrStr[1];
                String userName = arrStr[2];
                String date = arrStr[3];
                String iD = arrStr[4];
                Article art = new Article(name, content, date, userName, iD);
                articles.add(art);
                ss.sortByDate();

            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        return articles;
    }

}
