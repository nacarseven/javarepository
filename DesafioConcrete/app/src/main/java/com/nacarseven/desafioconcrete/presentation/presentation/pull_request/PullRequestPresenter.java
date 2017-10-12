package com.nacarseven.desafioconcrete.presentation.presentation.pull_request;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.nacarseven.desafioconcrete.presentation.data.entities.PullRequest;
import com.nacarseven.desafioconcrete.presentation.presenters.BasePresenter;

import java.util.List;

/**
 * Created by nacarseven on 11/10/17.
 */

public class PullRequestPresenter implements BasePresenter {

    //region FIELDS
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

    public void openLinkUrl(String htmlUrl, Context context){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(htmlUrl));
        context.startActivity(browserIntent);
    }

    //endregion

    //region OVERRIDE METHODS
    @Override
    public void detachView() {

    }

    //endregion

    //endregion
}
