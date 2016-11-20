package com.main.mygym.api;

import com.main.mygym.api.response.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by barikos on 13.06.16.
 */
public interface ExercisesService {

    @GET("api/v1/application/gym_exercises/category")
    Call<CategoryResponse> getCategories();

}
