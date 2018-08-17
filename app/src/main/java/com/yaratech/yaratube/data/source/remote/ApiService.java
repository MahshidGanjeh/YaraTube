package com.yaratech.yaratube.data.source.remote;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.Store;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface  ApiService {

    //mainpage content
    @GET("store/16")
    Call<Store> getStore();

    //vertical list of categories
    @GET("category/16/463")
    Call<List<Category>> getCategories();

    //product list of a category by category id
    @GET("listproducts/{category_id}")
    Call<List<Product>> getProductsByCategoryId(@Path("category_id") int categoryId);

}
