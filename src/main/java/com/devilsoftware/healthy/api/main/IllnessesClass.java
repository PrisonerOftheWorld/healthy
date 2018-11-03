package com.devilsoftware.healthy.api.main;

import com.devilsoftware.healthy.MongoDB;
import com.devilsoftware.healthy.api.models.ArticleModel;
import com.devilsoftware.healthy.api.models.Illness;
import com.devilsoftware.healthy.api.models.IllnessesJSON;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang.SerializationUtils;

public class IllnessesClass {

    Gson gson = new Gson();
    IllnessesJSON illnessesJSON;
    MongoDB mongoDB = new MongoDB();

    public List<ArticleModel> getIllnesses(String param) throws FileNotFoundException {

        /*
        Scanner scanner = new Scanner( new File("illnesses"), "UTF-8" );
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();

        illnessesJSON = gson.fromJson(text, IllnessesJSON.class);
           */


        IllnessesJSON illnessesBase = new IllnessesJSON();
        illnessesBase.illnesses = mongoDB.findIllnessesByParam(param);
                /*
        Iterator<ArticleModel> iterator = illnessesBase.illnesses.iterator();
        while(iterator.hasNext()){
            ArticleModel articleModel = iterator.next();
            if(!articleModel.title.toUpperCase().contains(param)){
                if(!articleModel.content.toUpperCase().contains(param)){
                    if(!articleModel.description.toUpperCase().contains(param)){
                        iterator.remove();
                    }
                }
            }
        }
        */

        return illnessesBase.illnesses;
    }

    public List<ArticleModel> getIllnessesById(List<Illness> list) throws FileNotFoundException {
        IllnessesJSON illnessesBase = new IllnessesJSON();

        illnessesBase.illnesses = mongoDB.findIllnessesById(list);

        /*
        List<Integer> ids = new ArrayList<>();
        for (Illness illness : list){
            ids.add(illness.id);
        }

        Iterator<ArticleModel> iterator = illnessesBase.illnesses.iterator();
        while(iterator.hasNext()){
            ArticleModel articleModel = iterator.next();
            if (!ids.contains(articleModel.id)){
                iterator.remove();
            }
        }
        */
        return illnessesBase.illnesses;
    }



}
