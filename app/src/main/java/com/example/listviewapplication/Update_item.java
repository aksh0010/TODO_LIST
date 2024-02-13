package com.example.listviewapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Update_item extends AppCompatActivity {

    Button update_button; // Button to update the item
    EditText update_item; // EditText to input the updated item
    ArrayList<String> arrayList = new ArrayList<>(); // ArrayList to store the list items
    ArrayAdapter arrayAdapter; // ArrayAdapter to bind the ArrayList to the ListView
    LinearLayout layout; // Layout reference


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_item);

        update_button= findViewById(R.id.update_item_btn_update_button); // Initializing the update button
        update_item = findViewById(R.id.update_item_et_update_item); // Initializing the update item EditText
        arrayList = FileHandler.ReadData(this); // Reading data from the file
        layout =  findViewById(R.id.linear_layout_update_item_xml); // Initializing layout reference
        arrayList = FileHandler.ReadData(this); // Reading data from the file
        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);

        update_item.setText(getIntent().getStringExtra("text_to_update")); // Setting the text to be updated

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Checking if the update item EditText is empty
                if(update_item.getText().toString().equals("")) {
                    // Displaying a Snackbar if the update item EditText is empty
                    Snackbar snackbar = Snackbar.make(layout, "Cannot add empty item", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    // Retrieving the text and position of the item to be updated
                    String text = getIntent().getStringExtra("text_to_update");
                    int pos= getIntent().getIntExtra("positon",-1);
                    arrayList.set(pos,update_item.getText().toString()); // Updating the item in the ArrayList
                    arrayAdapter.notifyDataSetChanged(); // Notifying the adapter about the data change

                    Log.d("UPDATE_BUTTON", pos + " |||| "+text); // Logging the update action
                    update_item.setText(""); // Clearing the update item EditText
                    FileHandler.writeData(arrayList, getApplicationContext()); // Writing data to the file

                    Log.d("UPDATE_BUTTON", "After Writing new arraylist" + " " +arrayList); // Logging the update action
                    Intent intent_update_to_main = new Intent(Update_item.this, MainActivity.class);
                    startActivity(intent_update_to_main); // Navigating back to the MainActivity
                }
            }
        });
    };
}
