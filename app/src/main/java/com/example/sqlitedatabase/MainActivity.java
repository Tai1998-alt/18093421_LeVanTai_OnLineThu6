package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnInsert, btnDelete, btnCancel;
    TextView editName;
    ArrayAdapter adapter;
    ArrayList arrayList;
    ArrayList arrayListDelete;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList();
        arrayListDelete = new ArrayList();
        listView = findViewById(R.id.listView);
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        editName = findViewById(R.id.editName);
        RooDatabase room= RooDatabase.getInstance(this);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,getName());
        listView.setAdapter(adapter);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                room.daoUser().InsertUser(new User(editName.getText().toString()));
                getName();
                adapter.notifyDataSetChanged();
                editName.setText("");
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                room.daoUser().DeleteUser((User) arrayListDelete.get(index));
                getName();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_SHORT).show();
            }
        });
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               index = position;
           }
       });
       btnCancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
    }
    public ArrayList getName()
    {
        arrayList.clear();
        arrayListDelete.clear();
        for (Iterator<com.example.sqlitedatabase.User> iterator = RooDatabase.getInstance(this).daoUser().getList().iterator(); iterator.hasNext(); ) {
            User user = (User) iterator.next();
            arrayList.add(user.getId()+"."+user.getName());
            arrayListDelete.add(user);
        }
        return arrayList;
    }
}