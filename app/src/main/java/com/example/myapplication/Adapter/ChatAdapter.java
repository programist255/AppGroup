package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Pag.Chat;
import com.example.myapplication.GroupChatActivity;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<Chat> productListChat;
    private Context mCts;

    public ChatAdapter(Context mCts, List<Chat> productListChat) {

        this.productListChat = productListChat;
        this.mCts = mCts;
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCts);
        View view = inflater.inflate(R.layout.chat_group, parent, false);
        return new ChatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {

        Chat chat = productListChat.get(position);
        holder.showMessage.setText(chat.getMessage());
        holder.showUser.setText(chat.getSender());
    }
    @Override
    public int getItemCount() {
        return productListChat.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView showMessage, showUser;
        public ViewHolder(View itemView) {
            super(itemView);
            showMessage = itemView.findViewById(R.id.textUser);
            showUser = itemView.findViewById(R.id.nameUser);
        }
    }
}
