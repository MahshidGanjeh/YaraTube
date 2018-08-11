package com.yaratech.yaratube.data.remote;

import com.yaratech.yaratube.data.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    //vertical list of categories
    @GET("category/16/463")
    Call<List<Category>> getCategories();
}
