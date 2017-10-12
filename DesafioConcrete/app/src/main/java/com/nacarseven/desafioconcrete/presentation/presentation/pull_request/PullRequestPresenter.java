package com.nacarseven.desafioconcrete.presentation.presentation.pull_request;

import com.nacarseven.desafioconcrete.presentation.data.entities.PullRequest;
import com.nacarseven.desafioconcrete.presentation.presenters.BasePresenter;

import java.util.List;

/**
 * Created by nacarseven on 11/10/17.
 */

public class PullRequestPresenter implements BasePresenter {


    private List<PullRequest> pullRequests;
    private PullRequestView view;

    public PullRequestPresenter(PullRequestView view) {
        this.view = view;
    }


    public void setPullRequests(List<PullRequest> pullRequests) {
        this.pullRequests = pullRequests;
    }

    @Override
    public void detachView() {

    }
}
