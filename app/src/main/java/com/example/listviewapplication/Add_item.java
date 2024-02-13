package com.example.listviewapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Add_item extends AppCompatActivity {

    Button add_button; // Button to add the item
    EditText edittext; // EditText to input the new item
    ArrayList<String> arrayList = new ArrayList<>(); // ArrayList to store the list items
    ArrayAdapter arrayAdapter; // ArrayAdapter to bind the ArrayList to the ListView
    LinearLayout layout; // Layout reference
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        add_button= findViewById(R.id.add_item_btn_addbutton); // Initializing the add button
        edittext = findViewById(R.id.add_item_et_additem); // Initializing the EditText
        arrayList = FileHandler.ReadData(this); // Reading data from the file
        layout =  findViewById(R.id.linear_layout_add_item_xml); // Initializing layout reference
        arrayList = FileHandler.ReadData(this); // Reading data from the file
        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);
        display = findViewById(R.id.add_item_tv_display_text);
        if(getIntent().getStringExtra("operation").equals("add_new_item")){

        add_button.setHint("Enter your item");
        display.setHint("Add your item to list");
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Checking if the EditText is empty
                if(edittext.getText().toString().equals("")) {
                    // Displaying a Snackbar if the EditText is empty
                    Snackbar snackbar = Snackbar.make(layout, "Cannot add empty item", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    // Adding the item to the ArrayList
                    arrayList.add(edittext.getText().toString());

                    arrayAdapter.notifyDataSetChanged(); // Notifying the adapter about the data change

                    edittext.setText(""); // Clearing the EditText
                    FileHandler.writeData(arrayList, getApplicationContext()); // Writing data to the file
                    Intent intent_additem_to_main = new Intent(Add_item.this, MainActivity.class);

                    startActivity(intent_additem_to_main); // Navigating back to the MainActivity

                }
            }
        });

    } else if (getIntent().getStringExtra("operation").equals("update_item") ) {
        add_button.setHint("Edit your item");
        display.setHint("Update the item");
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Checking if the update item EditText is empty
                if(edittext.getText().toString().equals("")) {
                    // Displaying a Snackbar if the update item EditText is empty
                    Snackbar snackbar = Snackbar.make(layout, "Cannot add empty item", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    // Retrieving the text and position of the item to be updated
                    String text = getIntent().getStringExtra("text_to_update");
                    int pos= getIntent().getIntExtra("positon",-1);
                    arrayList.set(pos,edittext.getText().toString()); // Updating the item in the ArrayList
                    arrayAdapter.notifyDataSetChanged(); // Notifying the adapter about the data change

                    add_button.setText(""); // Clearing the update item EditText
                    FileHandler.writeData(arrayList, getApplicationContext()); // Writing data to the file

                    Intent intent_update_to_main = new Intent(Add_item.this, MainActivity.class);
                    startActivity(intent_update_to_main); // Navigating back to the MainActivity
                }
            }
        });
    }

    };
}
