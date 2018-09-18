package com.gmail.safordog;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Article {

    private String name;
    private String content;
    private String date;
    private String userName;
    private String iD;

    public Article() {
    }

    public Article(String name, String content, String date, String userName, String iD) {
        this.name = name;
        this.content = content;
        this.date = date;
        this.userName = userName;
        this.iD = iD;
    }


    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getUserName() {
        return userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    @Override
    public String toString() {
        return ("Article: " + content + "; name: " + name + "; login: " + userName + "; date: " + date + "; ID: " + iD);
    }

}
