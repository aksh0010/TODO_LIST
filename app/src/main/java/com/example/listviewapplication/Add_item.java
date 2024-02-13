package com.example.listviewapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Add_item extends AppCompatActivity {

    Button add_button;
    EditText edittext;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    LinearLayout layout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        add_button= findViewById(R.id.add_item_btn_addbutton);
        edittext = findViewById(R.id.add_item_et_additem);
        arrayList = FileHandler.ReadData(this);
        layout =  findViewById(R.id.linear_layout_add_item_xml);
//        layout = findViewById(R.id.linear_layout);
        arrayList = FileHandler.ReadData(this);
        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edittext.getText().toString().equals(""))
                {
                    Snackbar snackbar = Snackbar.make(layout, "Cannot add empty item", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    arrayList.add(edittext.getText().toString());

                    arrayAdapter.notifyDataSetChanged();

                    edittext.setText("");
                    FileHandler.writeData(arrayList, getApplicationContext());
                    Intent intent_additem_to_main = new Intent(Add_item.this, MainActivity.class);

                    startActivity(intent_additem_to_main);

                }


            }
        });


        };






}