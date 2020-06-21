package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.myapplication.Adapter.ProductsAdapter;
import com.example.myapplication.Adapter.TaskAdapter;
import com.example.myapplication.Pag.Conctans;
import com.example.myapplication.Pag.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskGroupActivity extends AppCompatActivity {

    Button Back, AddTask;
    ImageView ImageGroup;
    TextView NameGroup, SizeGroup;
    RecyclerView ListTask;
    List<Task> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_group);

        ListTask = findViewById(R.id.listGroupTask);
        ListTask.setHasFixedSize(true);
        ListTask.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        loadProducts();
        AddTask = findViewById(R.id.Add_Task);
        Back = findViewById(R.id.B_Back_to_group1);
        ImageGroup = findViewById(R.id.imageGroups1);
        NameGroup = findViewById(R.id.nameGroups1);
        SizeGroup = findViewById(R.id.size_group1);
        Glide.with(ImageGroup)
                .load(GroupActivity.Ph)
                .into(ImageGroup);
        NameGroup.setText(ProductsAdapter.NameGR.toString());
        SizeGroup.setText(GroupActivity.Size + " уч.");
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent integer = new Intent(getApplicationContext(), GroupActivity.class);
                integer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity(integer);
            }
        });

        if(LoginActivity.ID.equals(GroupActivity.IdAdmin)){
            AddTask.setVisibility(View.VISIBLE);
        }

        AddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent integer = new Intent(getApplicationContext(), AddTaskGroup.class);
                integer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity(integer);
            }
        });
    }

    private void loadProducts() {
        StringRequest stringRequest = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, Conctans.URL_SELECT_GROUP_TASK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("tagconvertstr", "[" + response + "]");
                            JSONObject jsonobject = new JSONObject(response);
                            JSONArray array = jsonobject.getJSONArray("task_group");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject product = array.getJSONObject(i);
                                productList.add(new Task(
                                        product.getString("Title"),
                                        product.getString("Texts")
                                ));
                                System.out.println(productList);
                            }
                            System.out.println(array.length() + " leng");
                            TaskAdapter adapter = new TaskAdapter(TaskGroupActivity.this, productList);
                            ListTask.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                System.out.println("NameGroup: " + ProductsAdapter.NameGR);
                String id = LoginActivity.ID.toString();
                params.put("NameGroup", ProductsAdapter.NameGR);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
