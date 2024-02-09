package com.example.listviewapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list_of_items;
    EditText item;
    Button button_add;
    LinearLayout layout;
//    String list[]={"item","item2","item3"};

    ArrayList<String> arrayList = new ArrayList<>();

    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_of_items = findViewById(R.id.listView);
        item = findViewById(R.id.add_item);
        button_add = findViewById(R.id.add_button);

        arrayList = FileHandler.ReadData(this);
        layout = findViewById(R.id.linear_layout);


        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);

        list_of_items.setAdapter(arrayAdapter);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getText().toString().equals(""))
                {
                    Snackbar snackbar = Snackbar.make(layout, "Cannot add empty item", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else {
                    arrayList.add(item.getText().toString());

                    arrayAdapter.notifyDataSetChanged();

                    item.setText("");
                    FileHandler.writeData(arrayList, getApplicationContext());
                }
            }
        });

        list_of_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                arrayAdapter.notifyDataSetChanged();
            }
        });

//        list_of_items.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//                arrayList.remove(position);
//                arrayAdapter.notifyDataSetChanged();
//                return true;
//            }
//        });





    }
}