package com.clmca.labs.datingapp.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.clmca.labs.datingapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ChatUserAdapter extends RecyclerView.Adapter<ChatUserAdapter.MyViewHolder> {
    List<ChatUsers> chatUsersList;
    Context context;

    public ChatUserAdapter(List<ChatUsers> chatUsersList, Context context) {
        this.chatUsersList = chatUsersList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.matched_user_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChatUsers chatUsers = chatUsersList.get(position);
        holder.name.setText(chatUsers.getName());
        holder.profession.setText(chatUsers.getBio());
        if (chatUsers.getProfileImageUrl() != null) {
            Picasso.get().load(chatUsers.getProfileImageUrl()).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return chatUsersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView name, profession;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.mui_image);
            name = itemView.findViewById(R.id.mui_name);
            profession = itemView.findViewById(R.id.mui_profession);
        }
    }
}
