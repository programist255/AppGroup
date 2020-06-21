package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Pag.Conctans;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class NewGroupActivity extends AppCompatActivity{

    private ImageView addPhoto ;
    Bitmap bitmap;
    private Button AddNewGroup, Back;
    private EditText NameGroup;
    private EditText PasssGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        Back = findViewById(R.id.B_Back_to_group2);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent integer = new Intent(getApplicationContext(), MainActivity.class);
                integer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity(integer);
            }
        });
        NameGroup = findViewById(R.id.nameGroup);
        PasssGroup = findViewById(R.id.passGroup);
        AddNewGroup = findViewById(R.id.addNewGroup);
        AddNewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerGroup();
            }
        });
        addPhoto = findViewById(R.id.imageGroup);
        addPhoto. setOnClickListener ( new  View . OnClickListener ( )  {
            @ Override
            public  void onClick ( View view )  {
                Intent choosegal = new Intent ( Intent. ACTION_PICK , MediaStore. Images . Media . EXTERNAL_CONTENT_URI ) ;
                startActivityForResult ( choosegal, 1 ) ;
            }
        } ) ;
    }
    @ Override
    protected  void onActivityResult ( int req, int res, Intent imagereturn )
    {
        Uri filePath = null;
        try {
            if(null != imagereturn.getData() ){
                filePath = imagereturn.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(filePath);
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    addPhoto.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            super.onActivityResult(req, res, imagereturn);
}
    private void registerGroup() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait.. ");
        progressDialog.show();
        final String Namegroup = NameGroup.getText().toString().trim();
        final String Passgroup = PasssGroup.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, Conctans.URL_REGISTER_GROUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            progressDialog.dismiss();
                            Log.i("tagconvertstr", "["+response+"]");
                        Intent integer = new Intent(getApplicationContext(), MainActivity.class);
                        integer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                        startActivity(integer);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("tagconvertstr", "["+error+"]");
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String id = LoginActivity.ID.toString();
                String imagedate = getStringImage(bitmap);
                System.out.println(id);
                params.put("Photo", imagedate);
                params.put("NameGroup", Namegroup);
                params.put("PasswordGroup", Passgroup);
                params.put("Id", id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
}
