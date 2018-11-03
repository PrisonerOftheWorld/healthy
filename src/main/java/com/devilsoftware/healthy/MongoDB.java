package com.devilsoftware.healthy;

import com.devilsoftware.healthy.api.models.ArticleModel;
import com.devilsoftware.healthy.api.models.Illness;
import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDB {

    private MongoClient mongoClient;
    private MongoDatabase db;
    private MongoCollection<Document> table;
    private MongoCollection<Document> mainTable;

    List<ArticleModel> buffer;

    private Gson gson = new Gson();

    public MongoDB(){
        // Создаем подключение
        mongoClient = MongoClients.create("mongodb+srv://healthy-read:XJPA6tq3ylXTvxew@db-iw37r.gcp.mongodb.net/test?retryWrites=true");

        // Выбираем БД для дальнейшей работы
        db = mongoClient.getDatabase("illnesses");

        // Выбираем коллекцию/таблицу для дальнейшей работы
        table = db.getCollection("illnesses_collection");
        mainTable = db.getCollection("illnesses_main");
    }

    public List<ArticleModel> getMainList(){
        final List<ArticleModel> illnessModels = new ArrayList<>();

        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(Document document) {

                illnessModels.add(gson.fromJson(document.toJson(), ArticleModel.class));

            }
        };

        mainTable.find().forEach(printBlock);


        return illnessModels;
    }

    public List<ArticleModel> findIllnessesById(final List<Illness> list){
        final List<ArticleModel> illnessModels = new ArrayList<>();

        final List<Integer> ids = new ArrayList<>();
        for (Illness illness : list){
            ids.add(illness.id);
        }

        final Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(Document document) {

                if (ids.contains(document.getInteger("id"))) {
                    ArticleModel articleModel = new ArticleModel();
                    articleModel.id = document.getInteger("id");
                    articleModel.title = document.getString("title");
                    articleModel.content = document.getString("content");
                    articleModel.description = document.getString("description");
                    articleModel.urlMainImage = document.getString("urlMainImage");
                    illnessModels.add(articleModel);
                }


            }
        };

        table.find().forEach(printBlock);
        return illnessModels;
    }

    public List<ArticleModel> findIllnessesByParam(String param){
        final List<ArticleModel> illnessModels = new ArrayList<>();

        param = param.toUpperCase();
        final String finalParam = param;
        final Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(Document document) {
                    ArticleModel articleModel = new ArticleModel();
                    articleModel.id = document.getInteger("id");
                    articleModel.title = document.getString("title");
                    articleModel.content = document.getString("content");
                    articleModel.description = document.getString("description");
                    articleModel.urlMainImage = document.getString("urlMainImage");
                    if(articleModel.title.toUpperCase().contains(finalParam)){
                        illnessModels.add(articleModel);
                    } else if(articleModel.content.toUpperCase().contains(finalParam)){
                        illnessModels.add(articleModel);
                    } else if(articleModel.description.toUpperCase().contains(finalParam)){
                        illnessModels.add(articleModel);
                    }


            }
        };

        table.find().forEach(printBlock);
        return illnessModels;
    }



}
