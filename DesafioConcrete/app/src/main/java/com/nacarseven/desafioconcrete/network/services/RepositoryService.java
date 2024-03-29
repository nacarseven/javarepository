package com.nacarseven.desafioconcrete.network.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

/**
 * Created by nacarseven on 10/10/17.
 */

public interface RepositoryService {

    @GET("search/repositories")
    Single<JsonObject> getRepositories(@Query("q") String language,
                                       @Query("sort") String stars,
                                       @Query("page") int page);

    @GET("repos/{owner}/{repo}/pulls")
    Single<JsonArray> getPulls(@Path("owner") String author,
                               @Path("repo") String repoName);

}
