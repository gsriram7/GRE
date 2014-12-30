package com.example.selvarag.gre;

/**
 * Created by selvarag on 26-12-2014.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class StudentOperations {

    // Database fields
    private DataBaseWrapper dbHelper;
    private String[] STUDENT_TABLE_COLUMNS = { DataBaseWrapper.WORDS_ID, DataBaseWrapper.WORD_NAME, DataBaseWrapper.WORD_AGE,DataBaseWrapper.WORD_SYN,DataBaseWrapper.WORD_EXPL};
    private SQLiteDatabase database;

    public StudentOperations(Context context) {
        dbHelper = new DataBaseWrapper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Student addStudent(String name, int age, String syn, String expl) {

        System.out.println("Entered");

        ContentValues values = new ContentValues();

        values.put(DataBaseWrapper.WORD_NAME, name);
        values.put(DataBaseWrapper.WORD_AGE,age);
        values.put(DataBaseWrapper.WORD_SYN,syn);
        values.put(DataBaseWrapper.WORD_EXPL,expl);

        long studId = database.insert(DataBaseWrapper.WORDS, null, values);

        // now that the student is created return it ...
        Cursor cursor = database.query(DataBaseWrapper.WORDS,
                STUDENT_TABLE_COLUMNS, DataBaseWrapper.WORDS_ID + " = "
                        + studId, null, null, null, null);

        cursor.moveToFirst();

        Student newComment = parseStudent(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteStudent(String comment) {
        System.out.println("1."+comment);
        comment = "Donate";
        //long id = comment.getId();
        System.out.println("Comment deleted with id: " + comment);
        database.delete(DataBaseWrapper.WORDS, DataBaseWrapper.WORD_NAME+"='" + comment+"'", null);
    }

    public List getAllStudents() {
        List students = new ArrayList();

        Cursor cursor = database.query(DataBaseWrapper.WORDS,
                STUDENT_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Student student = parseStudent(cursor);
            students.add(student);
            cursor.moveToNext();
        }

        cursor.close();
        return students;
    }

    private Student parseStudent(Cursor cursor) {
        Student student = new Student();
        student.setId((cursor.getInt(0)));
        student.setName(cursor.getString(1));
        student.setAge(cursor.getInt(2));
        student.setSyn(cursor.getString(3));
        student.setExpl(cursor.getString(4));
        return student;
    }
}