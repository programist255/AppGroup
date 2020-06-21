package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Adapter.ProductsAdapter;
import com.example.myapplication.Adapter.TaskAdapter;

public class ShowTaskActivity extends AppCompatActivity {

    TextView Title, Text, NameGr;
    Button Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);
        NameGr = findViewById(R.id.NameGroups2);
        Title = findViewById(R.id.TitleTask2);
        Text = findViewById(R.id.TextTask2);
        Back = findViewById(R.id.B_Back_to_group4);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent integer = new Intent(getApplicationContext(), TaskGroupActivity.class);
                integer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity(integer);
            }
        });
        Title.setText(TaskAdapter.Title1);
        Text.setText(TaskAdapter.Text1);
    }
}
