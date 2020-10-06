package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        final ListView listView = findViewById(R.id.list);
        ArrayList<MovieData> arrayList = (ArrayList<MovieData>) getIntent().getSerializableExtra("moviedata");
        CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
        listView.setAdapter(customAdapter);


    }
}
