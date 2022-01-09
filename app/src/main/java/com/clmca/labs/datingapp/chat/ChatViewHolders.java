package com.clmca.labs.datingapp.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.clmca.labs.datingapp.R;


public class ChatViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView mChatMatchId, mChatMatchName;
    public ImageView mChatMatchImage;
    public ChatViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        mChatMatchId = (TextView) itemView.findViewById(R.id.mui_name);
        mChatMatchName = (TextView) itemView.findViewById(R.id.mui_profession);

        mChatMatchImage = (ImageView) itemView.findViewById(R.id.mui_image);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), ChatActivity.class);
        Bundle b = new Bundle();
        b.putString("matchId", mChatMatchId.getText().toString());
        intent.putExtras(b);
        view.getContext().startActivity(intent);
    }
}
