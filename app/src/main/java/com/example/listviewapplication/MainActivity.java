package com.example.listviewapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list_of_items;
//    EditText item;
    FloatingActionButton button_add;
    LinearLayout layout;
//    String list[]={"item","item2","item3"};
    int selectedItemPosition=-1;
    ArrayList<String> arrayList = new ArrayList<>();

    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_of_items = findViewById(R.id.listView);
//        item = findViewById(R.id.add_item);
        button_add = findViewById(R.id.fab_add_item);

        arrayList = FileHandler.ReadData(this);
        layout = findViewById(R.id.linear_layout);


        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);

        list_of_items.setAdapter(arrayAdapter);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_second_to_main = new Intent(MainActivity.this, Add_item.class);

                startActivity(intent_second_to_main);
//
//                if(item.getText().toString().equals(""))
//                {
//                    Snackbar snackbar = Snackbar.make(layout, "Cannot add empty item", Snackbar.LENGTH_LONG);
//                    snackbar.show();
//                }else if(selectedItemPosition != -1) {
//                        String updatedText = item.getText().toString();
//                        // Update the item in the list at the selected position
//                        arrayList.set(selectedItemPosition, updatedText);
//                        arrayAdapter.notifyDataSetChanged();
//                        FileHandler.writeData(arrayList, getApplicationContext());
//
//                        // Clear the EditText and reset the selected position
//                        item.setText("");
//                        selectedItemPosition = -1;
//
//                } else {
//                    arrayList.add(item.getText().toString());
//
//                    arrayAdapter.notifyDataSetChanged();
//
//                    item.setText("");
//                    FileHandler.writeData(arrayList, getApplicationContext());
//
//                }
            }
        });

        list_of_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                arrayAdapter.notifyDataSetChanged();
                FileHandler.writeData(arrayList, getApplicationContext());
            }
        });

//        list_of_items.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                // Retrieve the text of the item long-pressed
//                String selectedItem = arrayList.get(position);
//                // Set the text of the EditText to the selected item for editing
//                item.setText(selectedItem);
//                //updating our selecteditemposition to current
//                selectedItemPosition = position;
//                return true;
//            }
//        });





    }
}