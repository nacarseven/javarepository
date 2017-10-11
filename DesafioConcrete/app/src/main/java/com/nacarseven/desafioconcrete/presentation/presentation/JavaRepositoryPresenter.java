package com.nacarseven.desafioconcrete.presentation.presentation;

import com.nacarseven.desafioconcrete.presentation.data.entities.Repository;
import com.nacarseven.desafioconcrete.presentation.domain.JavaRepositoryInteractor;
import com.nacarseven.desafioconcrete.presentation.presenters.BasePresenter;

import java.util.List;

import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by nacarseven on 10/10/17.
 */

public class JavaRepositoryPresenter implements BasePresenter {
    
    private JavaRepositoryView view;
    private JavaRepositoryInteractor interactor;


    public JavaRepositoryPresenter(JavaRepositoryView view) {
        this.view = view;
        interactor = new JavaRepositoryInteractor();
    }

    public void getRepositories(){
        interactor.getRepositories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<List<Repository>>() {
                    @Override
                    public void onSuccess(List<Repository> value) {

                    }

                    @Override
                    public void onError(Throwable error) {

                    }
                });
    }

    @Override
    public void detachView() {
        
    }
}
