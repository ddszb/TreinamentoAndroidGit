package com.danielzambrano.hubrepofinder.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.danielzambrano.hubrepofinder.R;
import com.danielzambrano.hubrepofinder.model.User;
import com.danielzambrano.hubrepofinder.retrofit.GithubRetroFit;
import com.danielzambrano.hubrepofinder.retrofit.service.GitService;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private Context context;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.context = this;

        TextInputLayout username = findViewById(R.id.activity_search_input);
        Button button = findViewById(R.id.activity_search_button);


        ProgressBar loading = getProgressBar();

        button.setOnClickListener(v -> {
            loading.setVisibility(View.VISIBLE);
            searchUser(Objects.requireNonNull(username.getEditText()).getText().toString(), loading);
        });
    }

    private void searchUser(String username, ProgressBar progressBar){

        GitService service = new GithubRetroFit().getGitService();
        Call<User> call = service.getUser(username);
        new Thread(new Runnable() {
            @Override
            public void run() {

                boolean userFound = false;
                try {
                    Response<User> response  = call.execute();
                    if(response.code() == 200){
                        userFound = true;
                        user = response.body();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(userFound) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(SearchActivity.this, RepoListActivity.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                        }
                    });
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Usuário não encontrado.", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }


            }
        }).start();

    }

    private ProgressBar getProgressBar(){
        ProgressBar loading = findViewById(R.id.activity_search_progress_bar);
        loading.setVisibility(View.GONE);
        return loading;
    }
}