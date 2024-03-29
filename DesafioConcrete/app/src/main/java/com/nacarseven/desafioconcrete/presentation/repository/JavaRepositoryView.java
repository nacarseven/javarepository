package com.nacarseven.desafioconcrete.presentation.repository;

import com.nacarseven.desafioconcrete.data.entities.PullRequest;
import com.nacarseven.desafioconcrete.data.entities.Repository;

import java.util.List;

/**
 * Created by nacarseven on 10/10/17.
 */

public interface JavaRepositoryView {

    void showLoading(boolean show);

    void showPagingLoading(boolean show);

    void showMessageError();

    void showTextNoData(boolean show);

    void loadRepositories(List<Repository> repositories);

    void loadPullRequest(String repositoryName, List<PullRequest> pullRequests);
}
