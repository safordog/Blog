<%@ page import="com.gmail.safordog.Article" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.gmail.safordog.ArticleServlet" %>
<%@ page import="com.gmail.safordog.AddUserServlet" %>
<%@ page import="com.gmail.safordog.SortingServlet" %><%--
  Created by IntelliJ IDEA.
  User: safordog
  Date: 13.09.18
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="/styles/reset.css">
    <link rel="stylesheet" type="text/css" href="/styles/styles.css">
    <title>ProgBlog - Title</title>
  </head>
  <body>
    <div id="header" class="title">
      <div id="problog_title">
      <a href="/index.jsp"><p>ProJava-BLOG</p></a>
      <p><my>public static void main(String[] args) {&#160&#160&#160&#160&#160...&#160&#160&#160..1001&#160&#160&#160...110110011</my></p>
      <p><my1>System.out.println("ProJava-BLOG");&#160&#160&#160&#160...100011010...</my1></p>
      <p><my2>}&#160&#160&#160&#160&#160...1011101110</my2></p>
      </div>
      <div id="image_title">
      <img src="/images/java.png" alt="java">
      </div>
    </div>
    <div id="navi" class="navi">
      <div id="my_cabinet">
        <a href="/my_cabinet"><p>my cabinet</p></a>
      </div>
      <div id="search">
        <a href="/search"><p>searching articles</p></a>
      </div>
      <div id="sort">
        <a href="/sort"><p>sorting articles</p></a>
      </div>
      <div id="registration">
        <a href="/registration"><p>login / registration</p></a>
      </div>
    </div>
    <fieldset>
      <div id="content">
        <div id="article">
          <%Article art = new Article();%>
          <%ArticleServlet as = new ArticleServlet();%>
          <%AddUserServlet aus = new AddUserServlet();%>
          <%SortingServlet ss = new SortingServlet();%>
          <%ss.sortByDate();%>
          <%if (as.getArticles().size() == 0) {%>
          <%for (Article temp : as.getArticlesFromFile()) {%>
          <br><h3 class="word"><%=temp.getContent()%></h3><br><%=temp.getUserName()%><br><%=temp.getDate()%><br><br><br>
            <%}%>
          <%} else {%>
          <%for (Article temp : as.getArticles()) {%>
          <br><h3 class="word"><%=temp.getContent()%></h3><br><%=temp.getUserName()%><br><%=temp.getDate()%><br><br><br>
          <%}%>
          <%}%>
          <%if (aus.getUsers().isEmpty()) {%>
          <%aus.getUsersFromFile();%>
          <%}%>
        </div>
      </div>
    </fieldset>
    <p>ProJAVA Blog</p>
  </body>
</html>
