package com.example.rss_feeds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tvTitle;
    ListView listView;
    String[] newsPapers = {"THANH NIÊN", "VNEXPRESS", "DANTRI"};
    ArrayAdapter<String> adapter;



    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tvTitle);
        listView = findViewById(R.id.listView);

        tvTitle.setText("NEWS APP");

        adapter = new ArrayAdapter<>(this, R.layout.list_item, newsPapers);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedNewspaper = newsPapers[position];
                Intent intent = new Intent(MainActivity.this, ChannelsActivity.class);
                intent.putExtra("NEWSPAPER_NAME", selectedNewspaper);
                startActivity(intent);
            }
        });
    }
}