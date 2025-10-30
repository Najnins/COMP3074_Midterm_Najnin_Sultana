package com.example.midterm_najnin_sultana;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ListView historyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("History");
        setContentView(R.layout.activity_history);

        historyListView = findViewById(R.id.historyListView);

        // Convert LinkedHashSet to ArrayList to preserve generation order
        ArrayList<Integer> historyList = new ArrayList<>(MainActivity.historySet);

        // Adapter to show numbers
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                historyList
        );

        historyListView.setAdapter(adapter);
    }
}
