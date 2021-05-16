package com.example.question2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelStore;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> optionalModulesList = new ArrayList<>();
    private ListView listView;
    private ListViewAdapter adapter;
    private String sharedPrefFile = "com.example.question2";
    private ArrayList<Boolean> checkboxesStatus = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView=findViewById(R.id.listView);
        displayListView();
    }

    private void displayListView() {
        getOptionalModulesToList();
        adapter=new ListViewAdapter(optionalModulesList,this);
        listView.setAdapter((ListAdapter) adapter);
        SharedPreferences preferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        for (int i=0; i<optionalModulesList.size(); i++) {
            checkboxesStatus.add(preferences.getBoolean(String.format("checkbox %s",i), false));
        }

    }

    private void getOptionalModulesToList() {
        String[] optionalModulesArray = getResources().getStringArray(R.array.optionalModules);
        for (String optionalModule : optionalModulesArray) {
            optionalModulesList.add(optionalModule);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        for (int i=0; i<checkboxesStatus.size(); i++) {
            preferencesEditor.putBoolean(String.format("checkbox %s",i), checkboxesStatus.get(i));
        }
        preferencesEditor.apply();
    }

    private class ListViewAdapter extends ArrayAdapter<String> {

        private List<String> optionalModulesList = new ArrayList<>();
        private Context context;

        public ListViewAdapter(List<String> optionalModulesList,Context context){
            super(context,R.layout.item_layout,optionalModulesList);
            this.context=context;
            this.optionalModulesList=optionalModulesList;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater=((Activity)context).getLayoutInflater();
            View row=inflater.inflate(R.layout.item_layout,parent,false);
            TextView module=row.findViewById(R.id.module);
            module.setText(optionalModulesList.get(position));
            CheckBox checkBox =row.findViewById(R.id.checkbox);
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

                checkboxesStatus.set(position, isChecked);

            });
            checkBox.setChecked(checkboxesStatus.get(position));
            Log.d("MainActivity",String.valueOf(checkboxesStatus));
            return row;
        }
    }
}