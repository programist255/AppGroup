package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.ProductsAdapter;
import com.example.myapplication.Pag.Conctans;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AddTaskGroup extends AppCompatActivity {

    private EditText Title, Text;
    TextView NameGr;
    Button SendTask, Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_group);

        NameGr = findViewById(R.id.NameGroups3);
        NameGr.setText(ProductsAdapter.NameGR);

        Back = findViewById(R.id.B_Back_to_group3);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent integer = new Intent(getApplicationContext(), TaskGroupActivity.class);
                integer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity(integer);
            }
        });

        Title = findViewById(R.id.TitleTask);
        Text = findViewById(R.id.TextTask);
        SendTask = findViewById(R.id.SendTask);

        SendTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Send();
            }
        });

    }

    private void Send(){
        final String Titles = Title.getText().toString().trim();
        final String Texts = Text.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, Conctans.URL_SEND_GROUP_TASK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                                progressDialog.dismiss();
                        Log.i("tagconvertstr", "["+response+"]");
                        Intent integer = new Intent(getApplicationContext(), TaskGroupActivity.class);
                        integer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                        startActivity(integer);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("tagconvertstr", "["+error+"]");
//                                progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
//                        String id = LoginActivity.ID.toString();
//                        System.out.println(id);
                params.put("NameGroup", ProductsAdapter.NameGR);
                params.put("Title", Titles);
                params.put("Texts", Texts);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);

    }
}
