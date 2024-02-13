package com.example.listviewapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list_of_items; // ListView to display the list of items
    FloatingActionButton button_add; // FloatingActionButton to add new items
    LinearLayout layout; // Layout reference
    int selectedItemPosition=-1; // Position of the selected item
    ArrayList<String> arrayList = new ArrayList<>(); // ArrayList to store the list items

    ArrayAdapter arrayAdapter; // ArrayAdapter to bind the ArrayList to the ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_of_items = findViewById(R.id.listView);
//        item = findViewById(R.id.add_item);
        button_add = findViewById(R.id.fab_add_item); // Initializing the FloatingActionButton

        arrayList = FileHandler.ReadData(this); // Reading data from the file
        layout = findViewById(R.id.linear_layout); // Initializing layout reference


        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);

        list_of_items.setAdapter(arrayAdapter); // Setting the adapter to the ListView
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Opening the Add_item activity when the FloatingActionButton is clicked
                Intent intent_main_to_add = new Intent(MainActivity.this, Add_item.class);
                intent_main_to_add.putExtra("operation","add_new_item");
                startActivity(intent_main_to_add);
            }
        });

        list_of_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Removing the item when it is clicked
                arrayList.remove(position);
                arrayAdapter.notifyDataSetChanged();
                FileHandler.writeData(arrayList, getApplicationContext()); // Writing data to the file
            }
        });

        list_of_items.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve the text of the item long-pressed
                String selectedItem = arrayList.get(position);
                // Opening the Update_item activity with the selected item's text and position
                Intent intent_main_to_update = new Intent(MainActivity.this, Add_item.class);
                intent_main_to_update.putExtra("operation","update_item");
                intent_main_to_update.putExtra("text_to_update",selectedItem);
                intent_main_to_update.putExtra("positon",position);
                startActivity(intent_main_to_update);
                // Setting the selected item position for further reference
                selectedItemPosition = position;
                return true;
            }
        });





    }
}
