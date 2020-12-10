package com.manoranjan.newshunt1.Service;

import com.manoranjan.newshunt1.Response.AddNewsREsponse;
import com.manoranjan.newshunt1.Response.CategoryResponse;
import com.manoranjan.newshunt1.Response.LoginResponse;
import com.manoranjan.newshunt1.Response.NewsResponse;
import com.manoranjan.newshunt1.Response.SignupResponse;
import com.manoranjan.newshunt1.Response.SubCategoryResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface ApiService {
    @FormUrlEncoded
    @POST
    Call<LoginResponse> login(
            @Url String url,
            @Field("username") String username,
            @Field("password") String password);

    @POST()
    @Multipart
    Call<SignupResponse> createUser(
            @Url String url,
            @Part("name") RequestBody firstname,
            @Part("phone") RequestBody phone,
            @Part("email") RequestBody email,
            @Part("aadhar") RequestBody aadhar,
            @Part("pincode") RequestBody pincode,
            @Part("password") RequestBody password,
            @Part("confirm_password") RequestBody confirm_password,
            @Part("country") RequestBody country,
            @Part("state") RequestBody state,
            @Part("district") RequestBody district,
            @Part("block") RequestBody block,
            @Part MultipartBody.Part aadharFront,
            @Part MultipartBody.Part aadharBack);


    @GET
    Call<CategoryResponse> getcategory1(
            @Url String url
    );

    @GET
    Call<NewsResponse> get_news(
            @Url String url
    );
    //get-news?category=4&page=1'

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("get-category")
    Call<CategoryResponse> getcategory(@Header("Authorization") String auth);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("get-subcategory")
    Call<SubCategoryResponse> getsubcategory(@Header("Authorization") String auth, @Query("category_id") String category_id);

    @GET("news-add")
    @Multipart
    Call<AddNewsREsponse> add_news(@Header("Authorization") String auth,
                                   @Part("title") RequestBody title,
                                   @Part("content") RequestBody content,
                                   @Part("category") RequestBody category,
                                   @Part("news_type") RequestBody news_type,
                                   @Part("subcategory") RequestBody subcategory,
                                   @Part("country") RequestBody country,
                                   @Part("state") RequestBody state,
                                   @Part("district") RequestBody district,
                                   @Part("block") RequestBody block,
                                   @Part("video") RequestBody video,
                                   @Part("meta_title") RequestBody meta_title,
                                   @Part("meta_description") RequestBody meta_description,
                                   @Part MultipartBody.Part image,
                                   @Part MultipartBody.Part bannerimage);


    @POST("news-add")
    @FormUrlEncoded
    Call<AddNewsREsponse> add_newsnimg(
            @Header("Authorization") String auth,
            @Field("title") String title,
            @Field("content") String content,
            @Field("category") String category,
            @Field("news_type") String news_type,
            @Field("subcategory") String subcategory,
            @Field("country") String country,
            @Field("state") String state,
            @Field("district") String district,
            @Field("block") String block,
            @Field("video") String video,
            @Field("meta_title") String meta_title,
            @Field("meta_description") String meta_description);
}
   /* @POST()
    @Multipart
    Call<OtherReponse> upload_assignment(@Url String url,
                                         @Part("access_token") RequestBody access_token,
                                         @Part("firstname") RequestBody firstname,
                                         @Part("email") RequestBody category,
                                         @Part("mobile") RequestBody title,
                                         @Part MultipartBody.Part image);*/