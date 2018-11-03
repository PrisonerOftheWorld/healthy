package com.devilsoftware.healthy.api.servlets;

import com.devilsoftware.healthy.MongoDB;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/articles")
public class ArticleServlet extends HttpServlet {

    private Gson gson = new Gson();
    private MongoDB db = new MongoDB();

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        System.out.println("article get");
        httpServletResponse.setContentType("text/json; charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().println(gson.toJson(db.getMainList()));
        httpServletResponse.getWriter().flush();
    }
}
