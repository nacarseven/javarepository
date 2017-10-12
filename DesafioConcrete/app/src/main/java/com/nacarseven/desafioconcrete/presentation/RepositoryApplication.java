package com.nacarseven.desafioconcrete.presentation;

import android.app.Application;
import android.content.Context;

/**
 * Created by Elaine on 12/10/2017.
 */

public class RepositoryApplication extends Application {

    private static Context context;

    public void onCreate(){
        super.onCreate();
        RepositoryApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return RepositoryApplication.context;
    }
}
