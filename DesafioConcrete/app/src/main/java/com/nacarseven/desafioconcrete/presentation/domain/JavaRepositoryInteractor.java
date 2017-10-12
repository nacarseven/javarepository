package com.nacarseven.desafioconcrete.presentation.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.nacarseven.desafioconcrete.BuildConfig;
import com.nacarseven.desafioconcrete.presentation.common.helpers.DateFormatter;
import com.nacarseven.desafioconcrete.presentation.data.entities.Author;
import com.nacarseven.desafioconcrete.presentation.data.entities.PullRequest;
import com.nacarseven.desafioconcrete.presentation.data.entities.Repository;
import com.nacarseven.desafioconcrete.presentation.network.ServiceGenerator;
import com.nacarseven.desafioconcrete.presentation.network.services.RepositoryService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.Single;
import rx.functions.Func1;

/**
 * Created by nacarseven on 10/10/17.
 */

public class JavaRepositoryInteractor {

    //region FIELDS
    private RepositoryService service;

    //endregion

    //region CONSTRUCTOR
    public JavaRepositoryInteractor() {
        service = ServiceGenerator.createService(RepositoryService.class, BuildConfig.API_URL);
    }

    //endregion

    //region METHODS

    //region PUBLIC METHODS

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

    public Single<List<PullRequest>> getPulls(String author, String repository) {
        return service.getPulls(author, repository)
                .map(new Func1<JsonArray, List<PullRequest>>() {
                    @Override
                    public List<PullRequest> call(JsonArray jsonArray) {
                        List<PullRequest> prList = new ArrayList<>();
                        Gson defaultGson = getDefaultGson();
                        DateFormatter dateFormatter = new DateFormatter();

                        for (JsonElement json : jsonArray) {
                            JsonObject jsonObject = json.getAsJsonObject();
                            PullRequest pr = defaultGson.fromJson(jsonObject, PullRequest.class);
                            pr.setDate(dateFormatter.parseToCalendar(jsonObject.get("created_at").getAsString(),
                                    DateFormatter.DATE_FORMAT_API_OUTPUT, true));
                            prList.add(pr);
                        }

                        return prList;
                    }
                });
    }

    //endregion

    //region PRIVATE METHODS

    private Gson getDefaultGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .registerTypeAdapter(Calendar.class, new JsonDeserializer<Calendar>() {
                    @Override
                    public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new DateFormatter().parseToCalendar(json.getAsString(), DateFormatter.DATE_FORMAT_API_OUTPUT, true);
                    }
                })
                .create();
    }

    //endregion

    //endregion

}
