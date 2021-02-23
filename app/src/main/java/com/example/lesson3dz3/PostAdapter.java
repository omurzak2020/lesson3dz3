package com.example.lesson3dz3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    List<Post> list = new ArrayList<>();

    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fragment ,parent,false);

        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.onbind(list.get(position));
    }

    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Post> body) {
        list.addAll(body);
        notifyDataSetChanged();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_list);
        }

        public void onbind(Post post) {
            textView.setText(post.getTitle());
        }
    }
}
