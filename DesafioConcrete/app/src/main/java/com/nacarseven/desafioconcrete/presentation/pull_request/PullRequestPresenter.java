package com.nacarseven.desafioconcrete.presentation.pull_request;

import com.nacarseven.desafioconcrete.data.entities.PullRequest;
import com.nacarseven.desafioconcrete.presenters.BasePresenter;

import java.util.List;

/**
 * Created by nacarseven on 11/10/17.
 */

public class PullRequestPresenter implements BasePresenter {

    //region FIELDS
    private String repositoryName;
    private List<PullRequest> pullRequests;
    //endregion

    //region CONSTRUCTOR

    public PullRequestPresenter() {
    }

    //endregion

    //region METHODS

    //region PUBLIC METHODS

    public void setPullRequests(List<PullRequest> pullRequests) {
        this.pullRequests = pullRequests;
    }

    public List<PullRequest> getPullRequests() {
        return pullRequests;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    //endregion

    //region OVERRIDE METHODS
    @Override
    public void detachView() {

    }

    //endregion

    //endregion
}
