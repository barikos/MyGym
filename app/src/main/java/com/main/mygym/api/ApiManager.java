package com.main.mygym.api;

import com.main.mygym.Const;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by barikos on 14.06.16.
 */
public class ApiManager {
    private static Retrofit sRetrofit = null;

    public static ExercisesService getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ExercisesService.class);
    }

}
