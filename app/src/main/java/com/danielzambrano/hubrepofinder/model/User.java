package com.danielzambrano.hubrepofinder.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @Expose
    @SerializedName("login")
    private String username;
    @Expose
    @SerializedName("id")
    private long id;
    @Expose
    @SerializedName("avatar_url")
    private String avatarUrl;
    @Expose
    @SerializedName("html_url")
    private String pageUrl;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("public_repos")
    private long publicRepos;

    public User(String username, long id, String avatarUrl, String pageUrl, String name, long publicRepos) {
        this.username = username;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.pageUrl = pageUrl;
        this.name = name;
        this.publicRepos = publicRepos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(long publicRepos) {
        this.publicRepos = publicRepos;
    }
}
