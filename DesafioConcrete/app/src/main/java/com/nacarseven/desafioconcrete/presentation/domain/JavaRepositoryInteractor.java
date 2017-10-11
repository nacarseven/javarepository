package com.nacarseven.desafioconcrete.presentation.domain;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nacarseven.desafioconcrete.BuildConfig;
import com.nacarseven.desafioconcrete.presentation.data.entities.Author;
import com.nacarseven.desafioconcrete.presentation.data.entities.Repository;
import com.nacarseven.desafioconcrete.presentation.network.ServiceGenerator;
import com.nacarseven.desafioconcrete.presentation.network.services.RepositoryService;

import java.util.ArrayList;
import java.util.List;

import rx.Single;
import rx.functions.Func1;

/**
 * Created by nacarseven on 10/10/17.
 */

public class JavaRepositoryInteractor {

    private RepositoryService service;

    public JavaRepositoryInteractor() {
        service = ServiceGenerator.createService(RepositoryService.class, BuildConfig.API_URL);
    }

    public Single<List<Repository>> getRepositories(int page) {
        return service.getRepositories("Java", "stars", page)
                .map(new Func1<JsonObject, List<Repository>>() {
                    @Override
                    public List<Repository> call(JsonObject jsonElements) {

                        List<Repository> list = new ArrayList<>();

                        JsonArray array = jsonElements.get("items").getAsJsonArray();

                        for (JsonElement json : array) {
                            JsonObject jsonObject = json.getAsJsonObject();
                            Repository rep = new GsonBuilder().create().fromJson(json, Repository.class);
                            Author author = rep.getAuthor();
                            author.setName(jsonObject.get("name").isJsonNull() ? "" : jsonObject.get("name").getAsString());
                            author.setFullName(jsonObject.get("full_name").isJsonNull() ? "" : jsonObject.get("full_name").getAsString());
                            list.add(rep);
                        }

                        return list;
                    }
                });
    }

}
