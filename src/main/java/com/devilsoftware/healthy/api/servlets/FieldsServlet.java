package com.devilsoftware.healthy.api.servlets;

import com.devilsoftware.healthy.api.main.LayoutFieldsClass;
import com.devilsoftware.healthy.api.models.Fields;
import com.devilsoftware.healthy.api.models.LayoutFields;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/field")
public class FieldsServlet extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse resp) throws ServletException, IOException {
        int region = Integer.parseInt(httpServletRequest.getParameter("region"));

        LayoutFieldsClass layoutFieldsClass = new LayoutFieldsClass();
        Fields fields = layoutFieldsClass.getFields(region);

        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(gson.toJson(fields));
    }
}
