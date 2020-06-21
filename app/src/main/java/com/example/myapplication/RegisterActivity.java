package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "myProgect";
    private EditText InputFirst_Name, InputName, InputLogin, InputPassword;
    private Button InputRegister, InputB_Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        InputFirst_Name = findViewById(R.id.FirstName);
        InputName = findViewById(R.id.Name);
        InputLogin = findViewById(R.id.Login);
        InputPassword = findViewById(R.id.Password);
        InputRegister = findViewById(R.id.B_Register);
        InputB_Login = findViewById(R.id.Log);
        InputB_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent integer = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(integer);
            }
        });
        InputRegister.setOnClickListener(this);
    }
    void message(String text){
        AlertDialog.Builder mess = new AlertDialog.Builder(RegisterActivity.this);
        mess.setMessage(text);        mess .setCancelable(false);
        mess.setNegativeButton("ok!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                Log.d(TAG, "click");
            }
        });
        mess.show();
    }
    private void registerUser() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait.. ");
        final String First_Name = InputFirst_Name.getText().toString().trim();
        final String Name = InputName.getText().toString().trim();
        final String Login = InputLogin.getText().toString().trim();
        final String Password = InputPassword.getText().toString().trim();
        if(First_Name.length() < 1 && Name.length() < 1 && Login.length() < 1 && Password.length() < 1){
            message("Enter data!");
        }
        else if (First_Name.length() < 3 && Name != "" &&  Login != "" && Password != "") {
            message("Enter first name!");
        }else if(First_Name != "" && Name.length() < 3 &&  Login != "" && Password != "") {
            message("Enter name!");
        }else if(First_Name != ""  && Name != "" &&  Login.length() < 4 && Password != ""){
            message("Enter login!");
        }else if(First_Name != ""  && Name != "" &&  Login != "" && Password.length() < 4){
            message("Enter password!");
        }else{
            progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, Conctans.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("1")) {
                            progressDialog.dismiss();
                            Log.i("tagconvertstr", "[" + response + "]");
                            Toast.makeText(getApplicationContext(), "Register write", Toast.LENGTH_LONG).show();
                            Intent integer = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(integer);
                        } else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("First_Name", First_Name);
                params.put("Name", Name);
                params.put("Login", Login);
                params.put("Password", Password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }}
    @Override
    public void onClick(View view) {
        if(view == InputRegister) registerUser();
    }
}
