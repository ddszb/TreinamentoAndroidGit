package com.danielzambrano.hubrepofinder.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.danielzambrano.hubrepofinder.R;
import com.danielzambrano.hubrepofinder.model.Repository;
import com.danielzambrano.hubrepofinder.model.User;
import com.danielzambrano.hubrepofinder.recyclerview.adapter.RepoListAdapter;
import com.danielzambrano.hubrepofinder.retrofit.GithubRetroFit;
import com.danielzambrano.hubrepofinder.retrofit.service.GitService;
import com.danielzambrano.hubrepofinder.ui.transformation.CircleTransform;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RepoListActivity extends AppCompatActivity {

    private RepoListAdapter adapter;
    private Context context;

    private TextView userLogin;

    private int AVATAR_SIZE = 200;
    List<Repository> repos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);
        context = this;

        Bundle bundle = getIntent().getExtras();
        User user = (User) bundle.getSerializable("user");

        TextView userName = findViewById(R.id.activity_repo_list_user_name);
        TextView userLogin = findViewById(R.id.activity_repo_list_user_login);
        ImageView userAvatar = findViewById(R.id.activity_repo_list_user_avatar);


        if(user.getName() != null){
            userName.setText(user.getName());
            userLogin.setText(user.getUsername());
        } else{
            userName.setText(user.getUsername());
            userLogin.setText("");
        }

        Picasso.get()
                .load(user.getAvatarUrl())
                .resize(AVATAR_SIZE, AVATAR_SIZE)
                .transform(new CircleTransform())
                .into(userAvatar);

        searchUserRepos(user.getUsername());
        if (!repos.isEmpty()){
            configRecylerView(repos);
        }

    }

    private void configRecylerView(List<Repository> repos) {
        RecyclerView repoList = findViewById(R.id.activity_repo_list_view) ;
        configAdapter(repos, repoList);
    }

    private void configAdapter(List<Repository> repos, RecyclerView repoList) {
        adapter = new RepoListAdapter(this, repos);
        repoList.setAdapter(adapter);
    }


    private void searchUserRepos(String user) {
        GitService service = new GithubRetroFit().getGitService();
        Call<List<Repository>> call = service.searchRepos(user);


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Response<List<Repository>> response = call.execute();
                    repos = response.body();
                    System.out.println(response.body());
                } catch (IOException e) {
                    Toast.makeText(context, R.string.activity_search_user_not_found, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        configRecylerView(repos);
                    }
                });

            }
        }).start();
    }
}