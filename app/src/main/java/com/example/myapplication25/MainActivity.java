package com.example.myapplication25;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText    et_filename,et_content;
    Spinner sp_filename;
    Button  bt_moi,bt_luu,bt_mo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText  et_filename =   (EditText)findViewById(R.id.edit_filename);
        final EditText  et_content =   (EditText)findViewById(R.id.edit_content);

        Button  bt_moi  =   (Button)findViewById(R.id.button_clear);
        bt_moi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_content.setText("");
                et_filename.setText("");
            }
        });

        final ArrayList<String> filenamelist    =   new ArrayList<>();
        filenamelist.add("Hello");

        final Spinner   sp_filename =   (Spinner)findViewById(R.id.spiner_file);
        ArrayAdapter<String>    adapter =   new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice,filenamelist);

        sp_filename.setAdapter(adapter);
        sp_filename.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                et_filename.setText(filenamelist.get(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button  bt_luu  =   (Button)findViewById(R.id.button_save);
        bt_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  filename    =   et_filename.getText().toString();
                filenamelist.add(filename);
                SharedPreferences pref=getApplicationContext().getSharedPreferences(filename,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                editor.putString("content",et_content.getText().toString());
                editor.commit();
//                try{
//                    FileOutputStream    fout    =   openFileOutput(filename, Context.MODE_PRIVATE);
//                    fout.write(et_content.getText().toString().getBytes());
//                    fout.close();
//                }
//                catch (Exception    e){
//                    Toast.makeText(MainActivity.this, "Loi luu file", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        Button  bt_mo   =   (Button)findViewById(R.id.button_open);
        bt_mo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  filename    =   et_filename.getText().toString();
 /*               StringBuffer buffer =   new StringBuffer();
                String  line    =   null;

                try{
                    FileInputStream fin =   openFileInput(filename);
                    BufferedReader  br  =   new BufferedReader(new InputStreamReader(fin));
                    while ((line    =   br.readLine())!=null)
                        buffer.append(line).append("\n");
                    et_content.setText(buffer.toString());
                }
                catch (Exception    e){

                }
            }
        });

    }*/
               SharedPreferences pref=getApplicationContext().getSharedPreferences(filename,Context.MODE_PRIVATE);
               et_content.setText(pref.getString("content",null));
}});}}