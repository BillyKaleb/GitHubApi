package com.kaleb.githubapi;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.kaleb.githubapi.data.model.User;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<User> myList;

    public RecyclerAdapter(List<User> myList) {
        this.myList = myList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide
                .with(holder.imageView.getContext())
                .load(myList.get(position).getAvatarUrl())
                .into(holder.imageView);
        holder.textView.setText(myList.get(position).getLogin());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public void addList(List<User> users){
        this.myList.addAll(users);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView textView;
        AppCompatImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textRecycler);
            imageView = itemView.findViewById(R.id.imageRecycler);
        }
    }

    public void cleanList(){
        myList.clear();
    }

}
