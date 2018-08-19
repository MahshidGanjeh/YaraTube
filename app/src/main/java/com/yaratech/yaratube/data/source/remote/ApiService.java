package com.yaratech.yaratube.data.source.remote;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.DetailedProduct;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.Store;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.yaratech.yaratube.util.AppConstants.STORE_ID;

public interface ApiService {

    //mainpage content
    @GET("store/" + STORE_ID)
    Call<Store> getStore();

    //vertical list of categories
    @GET("category/" + STORE_ID + "/463")
    Call<List<Category>> getCategories();

    //products of a category by category id (Grid product page)
    @GET("listproducts/{category_id}")
    Call<List<Product>> getProductsByCategoryId(@Path("category_id") int categoryId);

    //product comments by product id
    @GET("comment/{product_id}")
    Call<List<Comment>> getProductCommentByProductId(@Path("product_id") int productId);

    //product details by product id
    @GET("product/{product_id}")
    Call<DetailedProduct> getDetailedProductByProductId(@Path("product_id") int productId);


}
