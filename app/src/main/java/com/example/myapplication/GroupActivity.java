package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.myapplication.Adapter.ProductsAdapter;
import com.example.myapplication.Pag.Conctans;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class GroupActivity extends AppCompatActivity {


    public ImageView ImageGroup;
    public TextView NameAdmin, SizeUsers, NameGroup;
    private Button GroupChat, Back, Task, Exit;
    public static String Size;
    public static String Ph;
    public static String IdAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Exit = findViewById(R.id.B_Exit_Group);
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });
        Task = findViewById(R.id.B_Task_Group);
        Task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent integer = new Intent(getApplicationContext(), TaskGroupActivity.class);
                startActivity(integer);
            }
        });
        NameGroup = findViewById(R.id.nameGroups);
        NameGroup.setText(ProductsAdapter.NameGR.toString());
        Back = findViewById(R.id.B_Back_to_group);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent integer = new Intent(getApplicationContext(), MainActivity.class);
                integer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity(integer);
            }
        });
        GroupChat = findViewById(R.id.users_group_chat);
        GroupChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent integer = new Intent(getApplicationContext(), GroupChatActivity.class);
                startActivity(integer);
            }
        });
        ImageGroup = findViewById(R.id.imageGroup);
        NameAdmin = findViewById(R.id.tv_admin);
        SizeUsers = findViewById(R.id.tv_size_users);
        StringRequest stringRequest = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, Conctans.URL_SELECT_ADMIN_GROUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("tagconvertstr", "[" + response + "]");
                            JSONArray jsonobject = new JSONArray(response);
                            NameAdmin.setText("admin: " + jsonobject.getString(0).trim());
                            Glide.with(ImageGroup.getContext())
                                    .load(jsonobject.getString(1).trim())
                                    .into(ImageGroup);
                            Ph = jsonobject.getString(1).trim();
                            SizeUsers.setText(jsonobject.getString(2).trim() + " уч.");
                            Size = jsonobject.getString(2);
                            IdAdmin = jsonobject.getString(3);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String id = LoginActivity.ID.toString();
                String name = ProductsAdapter.NameGR.toString();
                System.out.println(name);
                params.put("Id", id);
                params.put("NameGroup", name);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void exit() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Conctans.URL_EXIT_GROUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("tagconvertstr", "[" + response + "]");
                        if(response.equals("1")){
                            Intent integer = new Intent(getApplicationContext(), MainActivity.class);
                            integer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                            startActivity(integer);
                            Toast.makeText(GroupActivity.this, "Came out", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(GroupActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String id = LoginActivity.ID.toString();
                String name = ProductsAdapter.NameGR.toString();
                System.out.println("NameGR: " + name);
                params.put("Id", id);
                params.put("NameGroup", name);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
}