package com.nacarseven.desafioconcrete.presentation.presentation.repository;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;

import com.nacarseven.desafioconcrete.presentation.data.entities.PullRequest;
import com.nacarseven.desafioconcrete.presentation.data.entities.Repository;
import com.nacarseven.desafioconcrete.presentation.domain.JavaRepositoryInteractor;
import com.nacarseven.desafioconcrete.presentation.presentation.pull_request.PullRequestActivity;
import com.nacarseven.desafioconcrete.presentation.presenters.BasePresenter;

import org.parceler.Parcels;

import java.util.List;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by nacarseven on 10/10/17.
 */

public class JavaRepositoryPresenter implements BasePresenter {

    private boolean noMoreItems;
    private int currentPage = 1;
    private List<Repository> repositories;
    private JavaRepositoryView view;
    private Subscription subscription;
    private JavaRepositoryInteractor interactor;


    public JavaRepositoryPresenter(JavaRepositoryView view) {
        this.view = view;
        interactor = new JavaRepositoryInteractor();
    }

    public void getRepositories(final boolean nextPage) {
        if (nextPage) {
            if (noMoreItems) return;
            currentPage++;
            view.showPagingLoading(true);
        } else {
            currentPage = 1;
            noMoreItems = false;
            view.showLoading(true);
        }

        subscription = interactor.getRepositories(currentPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<List<Repository>>() {
                    @Override
                    public void onSuccess(List<Repository> value) {
                        if (view == null) return;

                        hideLoading(nextPage);

                        if (value.isEmpty()) {
                            noMoreItems = true;
                            if (nextPage) return;
                        }

                        if (nextPage) {
                            repositories.addAll(value);
                        } else {
                            repositories = value;
                            view.showTextNoData(repositories.isEmpty());
                        }

                        view.loadRepositories(repositories);

                    }

                    @Override
                    public void onError(Throwable error) {
                        if (view == null) return;
                        hideLoading(nextPage);
                        view.showMessageError();
                    }
                });
    }

    public void getPullsRepository(final String author, final String repository, final Context context) {
        view.showLoading(true);
        subscription = interactor.getPulls(author, repository)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<List<PullRequest>>() {
                    @Override
                    public void onSuccess(List<PullRequest> value) {
                        view.showLoading(false);

                        if (value.size() != 0) {
                            Intent intent = new Intent(context, PullRequestActivity.class);
                            intent.putExtra("pulls", Parcels.wrap(value));
                            context.startActivity(intent);

                        }

                    }

                    @Override
                    public void onError(Throwable error) {
                        view.showLoading(false);
                        view.showMessageError();
                    }
                });

    }

    @Override
    public void detachView() {
        if (subscription != null) subscription.unsubscribe();
        view = null;

    }

    private void hideLoading(boolean isPaging) {
        if (isPaging) {
            view.showPagingLoading(false);
        } else {
            view.showLoading(false);
        }
    }
}
