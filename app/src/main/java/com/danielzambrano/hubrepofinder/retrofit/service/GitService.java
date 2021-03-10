package com.danielzambrano.hubrepofinder.retrofit.service;

import com.danielzambrano.hubrepofinder.model.Repository;
import com.danielzambrano.hubrepofinder.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitService {

    @GET("users/{user}/repos")
    Call<List<Repository>> searchRepos(@Path("user") String user);

    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);
}
