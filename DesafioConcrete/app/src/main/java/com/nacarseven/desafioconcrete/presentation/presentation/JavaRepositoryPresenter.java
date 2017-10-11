package com.nacarseven.desafioconcrete.presentation.presentation;

import com.nacarseven.desafioconcrete.presentation.data.entities.Repository;
import com.nacarseven.desafioconcrete.presentation.domain.JavaRepositoryInteractor;
import com.nacarseven.desafioconcrete.presentation.presenters.BasePresenter;

import java.util.List;

import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by nacarseven on 10/10/17.
 */

public class JavaRepositoryPresenter implements BasePresenter {

    private boolean noMoreItems;
    private int currentPage = 1;
    private List<Repository> repositories;
    private JavaRepositoryView view;
    private JavaRepositoryInteractor interactor;


    public JavaRepositoryPresenter(JavaRepositoryView view) {
        this.view = view;
        interactor = new JavaRepositoryInteractor();
    }

    public void getRepositories(final boolean nextPage) {
        if (nextPage) {
            currentPage++;
            view.showPagingLoading(true);
        } else {
            currentPage = 1;
            noMoreItems = false;
            view.showLoading(true);
        }

        interactor.getRepositories(currentPage)
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

    @Override
    public void detachView() {

    }

    private void hideLoading(boolean isPaging) {
        if (isPaging) {
            view.showPagingLoading(false);
        } else {
            view.showLoading(false);
        }
    }
}
