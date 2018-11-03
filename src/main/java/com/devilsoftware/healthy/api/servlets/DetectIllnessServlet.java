package com.devilsoftware.healthy.api.servlets;

import com.devilsoftware.healthy.api.main.DetectIllnessClass;
import com.devilsoftware.healthy.api.main.IllnessesClass;
import com.devilsoftware.healthy.api.models.Illness;
import com.devilsoftware.healthy.api.models.IllnessesResult;
import com.devilsoftware.healthy.api.models.IllnessRequest;
import com.google.gson.Gson;
import com.googlecode.fannj.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet("/detect")
public class DetectIllnessServlet extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = httpServletRequest.getReader().readLine()) != null) {
            sb.append(s);
        }

        IllnessRequest illnessRequest = gson.fromJson(sb.toString() , IllnessRequest.class);
        IllnessesResult illnessesResult = DetectIllnessClass.detectIllness(illnessRequest);

        IllnessesClass illnessesClass = new IllnessesClass();
        illnessesResult.articleModels = illnessesClass.getIllnessesById(illnessesResult.illnessResults);

        httpServletResponse.setContentType("application/json; charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().println(gson.toJson(illnessesResult));
        httpServletResponse.getWriter().flush();
    }

}
