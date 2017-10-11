package com.nacarseven.desafioconcrete.presentation.domain;

import com.google.gson.JsonArray;
import com.nacarseven.desafioconcrete.BuildConfig;
import com.nacarseven.desafioconcrete.presentation.data.entities.Repository;
import com.nacarseven.desafioconcrete.presentation.network.ServiceGenerator;
import com.nacarseven.desafioconcrete.presentation.network.services.RepositoryService;

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

    public Single<List<Repository>> getRepositories(){
        return service.getRepositories()
                .map(new Func1<JsonArray, List<Repository>>() {
                    @Override
                    public List<Repository> call(JsonArray jsonArray) {
                        return null;
                    }
                });
    }

}
