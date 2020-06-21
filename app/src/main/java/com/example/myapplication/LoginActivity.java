package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Pag.Conctans;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.nio.file.Files.write;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText login, password;
    private Button log, reg;
    private static final String TAG = "myProgect";
    public static String ID;
    public static String NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.Login);
        password = findViewById(R.id.Password);
        log = findViewById(R.id.loginBut);
        reg = findViewById(R.id.B_Register);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent integer = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(integer);
            }
        });
        log.setOnClickListener(this);
    }
    private void loginUser() {
        final String logi = login.getText().toString();
        final String pass = password.getText().toString();
        if (logi.length() < 1 && pass.length() < 1) {
            message("Enter your login and password!");
        } else if (logi != "" && pass.length() < 1) {
            message("Enter your password!");
        } else if (logi.length() < 1 && pass != "") {
            message("Enter your login!");
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setTitle("Please Wait.. ");
            progressDialog.show();
            final String Login = login.getText().toString().trim();
            final String Password = password.getText().toString().trim();
            StringRequest stringRequest = new StringRequest(
                    Request.Method.DEPRECATED_GET_OR_POST, Conctans.URL_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("tagconvertstr", "[" + response + "]");
                            String bol = "";
                            try {
                                JSONArray jsonobject = new JSONArray(response);
                                String Id = jsonobject.getString(0).trim();
                                bol = jsonobject.getString(1).trim();
                                String name = jsonobject.getString(2).trim();
                                    ID = Id;
                                    NAME = name;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            System.out.println(bol);
                            if (bol.equals("1")) {
                                progressDialog.dismiss();
                                Log.i("tagconvertstr", "[" + response + "]");
                                Intent integer = new Intent(getApplicationContext(), MainActivity.class);
                                integer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
                                startActivity(integer);
                                finish();
                            } else
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("tagconvertstr", "[" + error + "]");
                            progressDialog.dismiss();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Login", Login);
                    params.put("Password", Password);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(stringRequest);
        }
    }
    void message(String text){
        AlertDialog.Builder mess = new AlertDialog.Builder(LoginActivity.this);
        mess.setMessage(text);        mess .setCancelable(false);
        mess.setNegativeButton("ok!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                Log.d(TAG, "click");
            }
        });
        mess.show();
    }
    @Override
    public void onClick(View view) {
        if(view == log) loginUser();
    }
}
