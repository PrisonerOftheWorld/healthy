package com.devilsoftware.healthy.api.servlets;

import com.devilsoftware.healthy.api.main.LevelRegions;
import com.devilsoftware.healthy.api.models.HashRegions;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/region")
public class RegionServlet extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse resp) throws IOException {
        String l = httpServletRequest.getParameter("level");
        int level = Integer.parseInt(l);

        LevelRegions levelRegions = new LevelRegions();
        HashRegions hashRegions = levelRegions.getRegions(level);

        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(gson.toJson(hashRegions));
    }
}
