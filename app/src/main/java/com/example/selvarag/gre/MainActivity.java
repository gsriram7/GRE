package com.example.selvarag.gre;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class MainActivity extends ListActivity {

    private StudentOperations studentDBoperation;
    String temp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDBoperation = new StudentOperations(this);
        studentDBoperation.open();

        List values = studentDBoperation.getAllStudents();

        // Use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    public void addUser(View view) {

        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
        System.out.println("Insertion");

        EditText text = (EditText) findViewById(R.id.editText1);
        EditText syn = (EditText) findViewById(R.id.editText2);
        EditText expl = (EditText) findViewById(R.id.editText3);
        temp = text.getText().toString();
        Student stud = studentDBoperation.addStudent(text.getText().toString(), 18,syn.getText().toString(),expl.getText().toString());

        adapter.add(stud);

    }

    public void deleteFirstUser(View view) {

        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
        Student stud = null;
        System.out.println("Donate");

        if (getListAdapter().getCount() > 0) {
            //stud = (Student) getListAdapter().getItem(0);
            studentDBoperation.deleteStudent(temp);
            adapter.remove(stud);
        }

    }

    @Override
    protected void onResume() {
        studentDBoperation.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        studentDBoperation.close();
        super.onPause();
    }

}