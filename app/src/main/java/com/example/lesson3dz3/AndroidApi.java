package com.example.lesson3dz3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AndroidApi {

    @GET("posts") // на получение ресурса
    Call<List<Post>> getPosts();

    @PUT("posts/{id}") // на обновление/изменения ресурса
    Call<Post> updatePost(
            @Path("id") String id
    );

    @DELETE("posts/{id}") // на удаление ресурса
    Call<Object> deletePost();

    @POST("posts") // на создание ресурса
    Call<Post> createPost(
            @Body Post post
    );
}
