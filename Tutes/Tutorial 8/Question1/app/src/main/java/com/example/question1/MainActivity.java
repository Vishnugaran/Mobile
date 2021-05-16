package com.example.question1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> friendsNameList = new ArrayList<>();
    private ListView listView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        displayListView();
    }

    private void displayListView() {
        getFriendsNameToList();
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friendsNameList);
        listView.setAdapter((ListAdapter) adapter);

    }
    private void getFriendsNameToList() {
        String[] friendsNameArray = getResources().getStringArray(R.array.friendsNames);
        for (String optionalModule : friendsNameArray) {
            friendsNameList.add(optionalModule);
        }
    }
}