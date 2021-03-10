package com.danielzambrano.hubrepofinder.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.danielzambrano.hubrepofinder.R;
import com.danielzambrano.hubrepofinder.model.Repository;

import java.text.Format;
import java.text.SimpleDateFormat;

public class RepoDetailActivity extends AppCompatActivity {

    private TextView name;
    private TextView stargazers;
    private TextView forks;
    private TextView description;
    private TextView language;
    private TextView creation;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);

        Bundle bundle = getIntent().getExtras();
        Repository repository = (Repository) bundle.getSerializable("repository");
        loadViews();
        bindTextViews(repository);

        button.setOnClickListener( v -> {
            Uri uri = Uri.parse(repository.getUrl());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

    }
    private void loadViews(){
        name = findViewById(R.id.activity_repo_detail_name);
        stargazers = findViewById(R.id.activity_repo_detail_stargazers);
        forks = findViewById(R.id.activity_repo_detail_forks);
        description = findViewById(R.id.activity_repo_detail_text_description);
        language = findViewById(R.id.activity_repo_detail_text_language);
        creation = findViewById(R.id.activity_repo_detail_text_creation);
        button = findViewById(R.id.activity_repo_detail_button);
    }

    private void bindTextViews(Repository repository) {
        name.setText(repository.getName());
        stargazers.setText(String.valueOf(repository.getStargazers()));
        forks.setText(String.valueOf(repository.getForks()));
        if(repository.getDescription() != null){
            description.setText(repository.getDescription());
        }else{
            description.setText(R.string.activity_repo_detail_sem_descricao);
        }
        language.setText(repository.getLanguage());

        Format formatter = new SimpleDateFormat("dd/MM/yyy");
        creation.setText(formatter.format(repository.getCreationDate()));

    }


}