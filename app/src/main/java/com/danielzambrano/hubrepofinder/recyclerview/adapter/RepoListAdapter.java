package com.danielzambrano.hubrepofinder.recyclerview.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danielzambrano.hubrepofinder.R;
import com.danielzambrano.hubrepofinder.model.Repository;
import com.danielzambrano.hubrepofinder.ui.activity.RepoDetailActivity;
import com.danielzambrano.hubrepofinder.ui.activity.RepoListActivity;
import com.danielzambrano.hubrepofinder.ui.activity.SearchActivity;

import java.util.List;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.RepoViewHolder> {

    private final List<Repository> repos;
    private final Context context;

    public RepoListAdapter(Context context, List<Repository> repos){
        this.context = context;
        this.repos = repos;
    }

    @NonNull
    @Override
    public RepoListAdapter.RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newView = LayoutInflater.from(context).inflate(R.layout.activity_list_item, parent, false);
        return new RepoViewHolder(newView);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoListAdapter.RepoViewHolder holder, int position) {
        Repository repository = repos.get(position);
        holder.bindParameters(repository);
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }


    class RepoViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView description;

        public RepoViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.activity_list_item_repo_name);
            description = itemView.findViewById(R.id.activity_list_item_repo_description);
        }

        public void bindParameters(Repository repository){
            name.setText(repository.getName());

            description.setText(repository.getDescription() != null ? repository.getDescription() : repository.getLanguage());
            itemView.setOnClickListener( v->{
                Intent intent = new Intent(context, RepoDetailActivity.class);
                intent.putExtra("repository", repository);
                context.startActivity(intent);
            });

        }

    }
}

