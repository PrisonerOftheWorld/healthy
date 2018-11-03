package com.devilsoftware.healthy.api.main;

import com.devilsoftware.healthy.api.models.Field;
import com.devilsoftware.healthy.api.models.Fields;
import com.devilsoftware.healthy.api.models.LayoutFields;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class LayoutFieldsClass {

    Gson gson;

    public LayoutFieldsClass(){
        gson = new Gson();
    }

    public Fields getFields(int region) throws FileNotFoundException {
        Scanner scanner = new Scanner( new File("layout"), "UTF-8" );
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();

        LayoutFields layoutFields = gson.fromJson(text, LayoutFields.class);
        List<Integer> fieldIds = layoutFields.map.get(region); // список полей (симптом) от региона

        scanner = new Scanner( new File("fields"), "UTF-8" );
        text = scanner.useDelimiter("\\A").next();
        scanner.close();

        Fields allFields = gson.fromJson(text, Fields.class); // все поля что только возможны, id с нуля



        Fields fields = new Fields();

        Iterator<Field> iterator = allFields.list.iterator();

        while (iterator.hasNext()){
            Field f = iterator.next();
            if (fieldIds.contains(f.id)){
                fields.list.add(f);
            }
        }

        return fields;
    }


}
