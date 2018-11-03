package com.devilsoftware.healthy.api.servlets;

import com.devilsoftware.healthy.TeacherClass;
import com.devilsoftware.healthy.api.main.DetectIllnessClass;
import com.devilsoftware.healthy.api.models.*;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddIllnessServlet extends HttpServlet {

    Gson gson = new Gson();
    TeacherClass teacherClass = new TeacherClass();

    public AddIllnessServlet() throws IOException {
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = httpServletRequest.getReader().readLine()) != null) {
            sb.append(s);
        }

        IllnessAddRequest addRequest = gson.fromJson(sb.toString(), IllnessAddRequest.class);

        System.out.println(gson.toJson(addRequest));

        int lastId = 0;

        for (int i : addRequest.map.keySet()){
            if (lastId<i) lastId = i;
        }

        int size = teacherClass.getNumberOfInputNeurons();
        if (lastId>size){
            size = lastId;
        }

        float[] map = new float[size];
        System.out.println(size);
        for (int i : addRequest.map.keySet()){
            System.out.println("ID = " + i);
            map[i-1] = addRequest.map.get(i);
        }

        teacherClass.update();
        teacherClass.addIllness(map, addRequest.id);
        teacherClass.close();

        Status status = new Status(200, "Success");

        httpServletResponse.getOutputStream().println(gson.toJson(status));


    }
}
