package com.main.mygym.api;

import com.main.mygym.Const;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by barikos on 14.06.16.
 */
public class ApiClient {
    private static Retrofit sRetrofit = null;

    public static Retrofit getClient(){
        if (sRetrofit == null){
            sRetrofit = new Retrofit.Builder()
                            .baseUrl(Const.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        }
        return sRetrofit;
    }
}
