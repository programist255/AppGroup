package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.Adapter.ChatAdapter;
import com.example.myapplication.Adapter.ProductsAdapter;
import com.example.myapplication.Pag.Chat;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupChatActivity extends AppCompatActivity {

    private RecyclerView listChat;
    public Button Search;
    public EditText text;
    FirebaseUser fuser;
    Intent intent;
    DatabaseReference reference;
    ChatAdapter chatAdapter;
    List<Chat> mChat;
    RecyclerView recyclerView;
    public ImageView PhotoGroup;
    public TextView NameGroup, SizeGroup;
    public Button Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        Back = findViewById(R.id.B_Back_to_group);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent integer = new Intent(getApplicationContext(), GroupActivity.class);
                integer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity(integer);
            }
        });
        PhotoGroup = findViewById(R.id.imageGroups);
        NameGroup = findViewById(R.id.nameGroups);
        SizeGroup = findViewById(R.id.size_group);
        Glide.with(PhotoGroup)
                                    .load(GroupActivity.Ph)
                                    .into(PhotoGroup);
        NameGroup.setText(ProductsAdapter.NameGR.toString());
        SizeGroup.setText(GroupActivity.Size + " уч.");
        listChat = findViewById(R.id.listGroupChat);
        Search = findViewById(R.id.search_mesage);
        text = findViewById(R.id.mesage);
        recyclerView = findViewById(R.id.listGroupChat);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        final String userid = LoginActivity.NAME;
        final String Gr = ProductsAdapter.NameGR;
        Search.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String msg = text.getText().toString();
                if(!msg.equals("")){
                    sendMessage(userid, Gr, msg);
                    text.getText().clear();
                }else {
                    Toast.makeText(GroupChatActivity.this, "Error, enter text", Toast.LENGTH_LONG).show();
                }
            }
        });
        intent = getIntent();
        readMessage(Gr, userid);
    }


    private void sendMessage(String sender, String receiver, String message){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> params = new HashMap<>();
        params.put("sender", sender);
        params.put("receiver", receiver);
        params.put("message", message);
        reference.child("Chats").push().setValue(params);
    }

    private void readMessage(final String Gr, final String usarid){
        mChat = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                        if (chat.getReceiver().equals(Gr)) {
                            mChat.add(chat);
                        }
                        chatAdapter = new ChatAdapter(GroupChatActivity.this, mChat);
                        recyclerView.setAdapter(chatAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    }
