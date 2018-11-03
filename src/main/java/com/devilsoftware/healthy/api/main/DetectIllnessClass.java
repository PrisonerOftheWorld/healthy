package com.devilsoftware.healthy.api.main;

import com.devilsoftware.healthy.TeacherClass;
import com.devilsoftware.healthy.api.models.IllnessesResult;
import com.devilsoftware.healthy.api.models.Illness;
import com.devilsoftware.healthy.api.models.IllnessRequest;
import com.googlecode.fannj.Fann;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetectIllnessClass {



    public static IllnessesResult detectIllness(IllnessRequest illnessRequest) throws IOException {
        TeacherClass teacherClass = new TeacherClass();



        float[] map = new float[teacherClass.getNumberOfInputNeurons()];
        System.out.println(map.length);
        for (int i : illnessRequest.map.keySet()){
            if(!(map.length <i)) map[i-1] = illnessRequest.map.get(i); else System.out.println("Слишком много ID, обновите библиотеку");

        }

        Fann fann = new Fann("NN");

        return getResult(fann.run(map));
    }

    private static IllnessesResult getResult(float[] in){

        List<Illness> ills = new ArrayList<Illness>();

        for (int j = 0; j <in.length; j++) {
            if(in[j]>0.1f) {
                Illness illnessResult = new Illness();
                illnessResult.id = j+1;
                illnessResult.percent = in[j];
                ills.add(illnessResult);
            }
        }

        boolean debug = true;

        if (debug){
            for (Illness ill : ills) {
                System.out.println("id: " + ill.id + " percent: " + ill.percent);
            }
        }

        for (int j = 0; j < ills.size()-1; j++)
            for (int i = 0; i < ills.size()-1; i++){
                if(ills.get(i).percent<ills.get(i+1).percent){
                    Illness buffer = ills.get(i);
                    ills.set(i,ills.get(i+1));
                    ills.set(i+1, buffer);
                }
            }

        IllnessesResult illnessMap = new IllnessesResult();
        illnessMap.illnessResults = ills;
        return illnessMap;
    }


}
