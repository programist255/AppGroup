package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.ProductsAdapter;
import com.example.myapplication.Pag.Conctans;
import com.example.myapplication.Pag.Group;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView numberList;
    public Button add_group, searchGroup;
    public EditText text;
    List<Group> productList;
    List<Group> newlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberList = findViewById(R.id.listGroup);
        add_group = findViewById(R.id.ib_add_group);
        add_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent integer = new Intent(getApplicationContext(), NewGroupActivity.class);
                startActivity(integer);
            }
        });
        numberList.setHasFixedSize(true);
        numberList.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        loadProducts();
        searchGroup = findViewById(R.id.search_group);
        text = findViewById(R.id.text_search_group);
        searchGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchGroup();

            }
        });
        newlist = new ArrayList<>();
    }

    private void searchGroup() {
        final String Text = text.getText().toString();
        productList.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, Conctans.URL_SEARCH_GROUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.i("tagconvertstr", "[" + response + "]");
                            JSONObject jsonobject = new JSONObject(response);
                            JSONArray array = jsonobject.getJSONArray("prog_group");

                            if(array.length() > 0) {
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject product = array.getJSONObject(i);
                                    productList.add(new Group(
                                            product.getString("NameGroup"),
                                            product.getString("Photo")
                                    ));
//                                System.out.println(productList);
                                }
                            }
                            System.out.println(array.length() + " leng");
                            ProductsAdapter adapter = new ProductsAdapter(MainActivity.this, productList);
                            numberList.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "No group named "+ Text + " found!!!", Toast.LENGTH_LONG).show();
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
                System.out.println(Text);
                params.put("Text", Text);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void loadProducts() {
        StringRequest stringRequest = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, Conctans.URL_SELECT_GROUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("tagconvertstr", "[" + response + "]");

                            JSONObject jsonobject = new JSONObject(response);
                            JSONArray array = jsonobject.getJSONArray("prog_group");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject product = array.getJSONObject(i);
                                productList.add(new Group(
                                        product.getString("NameGroup"),
                                        product.getString("Photo")
                                ));
                                System.out.println(productList);
                            }
                            System.out.println(array.length() + " leng");
                            ProductsAdapter adapter = new ProductsAdapter(MainActivity.this, productList);
                            numberList.setAdapter(adapter);
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
                System.out.println("Id: " + LoginActivity.ID);
                String id = LoginActivity.ID.toString();
                params.put("Id", id);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
