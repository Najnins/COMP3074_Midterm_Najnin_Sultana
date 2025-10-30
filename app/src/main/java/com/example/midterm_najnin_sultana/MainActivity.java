package com.example.midterm_najnin_sultana;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    EditText inputNumber;
    Button btnGenerate, btnHistory;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> tableItems = new ArrayList<>();
    static Set<Integer> historySet = new LinkedHashSet<>(); // Shared history

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("timesTable");
        setContentView(R.layout.activity_main);

        inputNumber = findViewById(R.id.inputNumber);
        btnGenerate = findViewById(R.id.btnGenerate);
        btnHistory = findViewById(R.id.btnHistory);
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tableItems);
        listView.setAdapter(adapter);

        btnGenerate.setOnClickListener(v -> generateTable());
        btnHistory.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HistoryActivity.class)));

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String item = tableItems.get(position);

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Delete Row?")
                    .setMessage("Do you want to delete:\n" + item + "?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        tableItems.remove(position);
                        adapter.notifyDataSetChanged();
                      Toast.makeText(MainActivity.this, "Deleted: " + item, Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    private void generateTable() {
        String input = inputNumber.getText().toString().trim();
        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        int number = Integer.parseInt(input);
        tableItems.clear();
        for (int i = 1; i <= 10; i++) {
            tableItems.add(number + " × " + i + " = " + (number * i));
        }
        adapter.notifyDataSetChanged();
        historySet.add(number);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.clearAll) {
            if (tableItems.isEmpty()) {
                Toast.makeText(this, "Nothing to clear!", Toast.LENGTH_SHORT).show();
                return true;
            }

            new AlertDialog.Builder(this)
                    .setTitle("Clear All?")
                    .setMessage("Do you want to delete all rows?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        tableItems.clear();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "All rows cleared", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
