package com.danielzambrano.hubrepofinder.retrofit;

import com.danielzambrano.hubrepofinder.retrofit.service.GitService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GithubRetroFit {

    private final GitService gitService;

    public GithubRetroFit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitService = retrofit.create(GitService.class);
    }

    public GitService getGitService() {
        return gitService;
    }
}
