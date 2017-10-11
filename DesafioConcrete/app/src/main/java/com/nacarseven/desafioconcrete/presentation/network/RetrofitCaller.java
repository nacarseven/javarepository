package com.nacarseven.desafioconcrete.presentation.network;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nacarseven on 10/10/17.
 */

public class RetrofitCaller {

        /**
         * Method to make api calls using retrofit library
         */

        public static <T> void callApi(Call<T> caller, final OnApiCallListener<T> listener) {
            caller.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Call<T> call, Response<T> response) {
                    if (response.isSuccessful()) {
                        listener.onApiCallSuccess(response.body());
                    } else {
                        try {
                            listener.onApiCallError(response.code(), response.errorBody().string());
                        } catch (IOException e) {
                            listener.onApiCallError(0, "error to read response");
                        }
                    }
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    listener.onApiCallError(0, t.getMessage());
                }
            });
        }

        /**
         * Interface to handle the possible behaviors on an api call
         */
        public interface OnApiCallListener<T> {
            void onApiCallSuccess(T response);

            void onApiCallError(int code, String error);
        }

}
