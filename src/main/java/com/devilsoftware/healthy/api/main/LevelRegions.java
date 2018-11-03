package com.devilsoftware.healthy.api.main;

import com.devilsoftware.healthy.api.models.HashRegions;
import com.devilsoftware.healthy.api.models.Levels;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class LevelRegions {

    Gson gson = new Gson();

    public LevelRegions(){

    }

    public HashRegions getRegions(int level) throws FileNotFoundException {
        HashRegions hashRegions = new HashRegions();
        hashRegions.map = new HashMap<>();

        Scanner scanner = new Scanner( new File("levels"), "UTF-8" );
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();

        Levels levels = gson.fromJson(text, Levels.class);

        switch (level){
            case -1:
                int index = 1;
                for (String a: levels.first.keySet()){
                    hashRegions.map.put(index, levels.first.get(a));
                            index++;
                }
                break;
            case 0:
                index = 1;
                for (String a: levels.first.keySet()){
                    hashRegions.map.put(index, levels.first.get(a));
                    index++;
                }
                break;
            case 1:
                hashRegions.map = levels.a;
                break;
            case 2:
                hashRegions.map = levels.b;
                break;
            case 3:
                hashRegions.map = levels.c;
                break;
            case 4:
                hashRegions.map = levels.d;
                break;
            case 5:
                hashRegions.map = levels.e;
                break;
            case 6:
                hashRegions.map = levels.f;
                break;
        }

        return hashRegions;
    }

}
