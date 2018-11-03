package com.devilsoftware.healthy.api.servlets;

import com.devilsoftware.healthy.NNClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/nn")
public class NNSettingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        int update =  Integer.parseInt(httpServletRequest.getParameter("update"));

        if (update==1){
            System.out.println("Try to update NN");
            NNClass nnClass = new NNClass();
            nnClass.saveNN();
            httpServletResponse.getOutputStream().println("NN updated successfully.");
        }
    }
}
