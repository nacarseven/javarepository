package com.nacarseven.desafioconcrete.presentation.network.services;

import com.google.gson.JsonArray;

import retrofit2.http.GET;
import rx.Single;

/**
 * Created by nacarseven on 10/10/17.
 */

public interface RepositoryService {

    @GET("/orgs/octokit/repos")
    Single<JsonArray> getRepositories();

}
