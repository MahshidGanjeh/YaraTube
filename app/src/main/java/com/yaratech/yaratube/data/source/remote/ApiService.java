package com.yaratech.yaratube.data.source.remote;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.DetailedProduct;
import com.yaratech.yaratube.data.model.GoogleLogin;
import com.yaratech.yaratube.data.model.Login;
import com.yaratech.yaratube.data.model.PostComment;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.Profile;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.data.model.User;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    //we get the 10 products to show in recycler and then
    //we load more 10 products
    //limit is the number of products which we want to display
    //offset is the number where the response is started from
    @GET("listproducts/{category_id}")
    Call<List<Product>> getProductsByCategoryId(@Path("category_id") int categoryId,
                                                @Query("limit") int limit,
                                                @Query("offset") int offset);

    //product comments by product id
    @GET("comment/{product_id}")
    Call<List<Comment>> getProductCommentByProductId(@Path("product_id") int productId);

    //product details by product id
    @GET("product/{product_id}?device_os=ios")
    Call<DetailedProduct> getDetailedProductByProductId(@Path("product_id") int productId);

    //mobile login step one
    //send phoneNumber and get verification code
    @POST("mobile_login_step1/" + STORE_ID)
    @FormUrlEncoded
    Call<Login> postPhoneNumber(@Field("mobile") String mobileNumber,
                                @Field("device_id") String deviceId,
                                @Field("device_model") String deviceModel,
                                @Field("device_os") String deviceOs
    );

    //mobile login step two
    //send verification code and get some info including token
    @POST("mobile_login_step2/" + STORE_ID)
    @FormUrlEncoded
    Call<User> postVerificationCode(@Field("mobile") String mobileNumber,
                                    @Field("device_id") String deviceId,
                                    @Field("verification_code") String verificationCode,
                                    @Field("nickname") String nickName
    );

    //send comment
    @POST("comment/{product_id}")
    @FormUrlEncoded
    Call<PostComment> postComment(@Field("title") String title,
                                  @Field("score") int score,
                                  @Field("comment_text") String commentText,
                                  @Path("product_id") int productId,
                                  //to check if the user is logged in(so he can post comment)
                                  // we send token
                                  @Header("Authorization") String token
    );

    //google login
    //send info that we get from google login result to the server
    //and get some other info including token
    @POST("login_google/" + STORE_ID)
    @FormUrlEncoded
    Call<GoogleLogin> postGoogleLoginResult(@Field("token_id") String tokenId,
                                            @Field("device_id") String deviceId,
                                            @Field("device_os") String deviceOs,
                                            @Field("device_model") String deviceModel
    );

    //send profile fields to the server
    @POST("profile")
    @FormUrlEncoded
    Call<Profile> postProfileFields(@Field("nickname") String name,
                                    @Field("gender") String gender,
                                    @Field("date_of_birth") String dateOfBirth,
                                    @Header("Authorization") String token
    );

    //upload profile image
    //send image file and get string avatar
    @Multipart
    @POST("profile")
    Call<Profile> uploadAvatar(@Part MultipartBody.Part avatar,
                               @Header("Authorization") String token);


}
