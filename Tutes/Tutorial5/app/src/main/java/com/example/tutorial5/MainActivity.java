package com.example.tutorial5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editId,editName,editAddress,editAge ,editPosition,askid;
    Button addbutton;
    Button viewbutton;
    Button delbutton;
    Button updatebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = (DatabaseHelper) new DatabaseHelper(this);
        editId = (EditText)findViewById(R.id.editempid);
        editName = (EditText)findViewById(R.id.editname);
        editAddress = (EditText)findViewById(R.id.editaddress);
        editAge = (EditText)findViewById(R.id.editage);
        editPosition = (EditText)findViewById(R.id.editposition);
        addbutton = (Button)findViewById(R.id.addbutton);
        viewbutton = (Button)findViewById(R.id.viewbutton);
        delbutton = (Button)findViewById(R.id.delbutton);
        askid = (EditText)findViewById(R.id.askid);
        updatebutton = (Button)findViewById(R.id.updatebutn);
        AddData();
        viewAll();
        DeleteData();
        UpdateData();
    }

    public  void AddData() {
        addbutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editId.getText().toString(),
                                editName.getText().toString(),
                                editAddress.getText().toString(),
                                editAge.getText().toString() ,
                                editPosition.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void viewAll() {
        viewbutton .setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("address :" + res.getString(2) + "\n");
                            buffer.append("age :" + res.getString(3) + "\n\n");
                            buffer.append("Position :" + res.getString(4) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }
    public void DeleteData() {
        delbutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(askid.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void UpdateData() {
        updatebutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(askid.getText().toString(),
                                editName.getText().toString(),
                                editAddress.getText().toString(),editAge.getText().toString(),editPosition.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    }