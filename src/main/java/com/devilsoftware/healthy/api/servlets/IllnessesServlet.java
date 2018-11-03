package com.devilsoftware.healthy.api.servlets;

import com.devilsoftware.healthy.MongoDB;
import com.devilsoftware.healthy.api.main.IllnessesClass;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

@WebServlet("/illnesses")
public class IllnessesServlet extends HttpServlet {

    Gson gson = new Gson();
    IllnessesClass illnessesClass = new IllnessesClass();

    public IllnessesServlet() throws FileNotFoundException {
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse resp) throws IOException {
        String find = httpServletRequest.getParameter("find");
        if (find!=null){
            resp.setContentType("application/json; charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(gson.toJson(illnessesClass.getIllnesses(find)));
            resp.getWriter().flush();
        }
    }
}
