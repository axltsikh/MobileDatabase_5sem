package com.example.databaseeleven.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.databaseeleven.DataBaseHelper;
import com.example.databaseeleven.R;

public class secondQuery extends Fragment {

    SQLiteDatabase db;
    DataBaseHelper helper;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_second_query, container, false);
        helper=new DataBaseHelper(rootView.getContext());
        Filler();
        return rootView;
    }
    public void Filler(){
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(rootView.getContext(),
                R.array.facultys, android.R.layout.simple_spinner_item);
        Spinner spinner=rootView.findViewById(R.id.SecondQuerySpinner);
        spinner.setAdapter(adapter);
        Button button=rootView.findViewById(R.id.SecondQuery);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secondQuery();
            }
        });
    }
    public void secondQuery(){
        TextView text=rootView.findViewById(R.id.SecondResultTextView);
        TextView begin=rootView.findViewById(R.id.BeginSecondEditText);
        TextView end=rootView.findViewById(R.id.EndSecondEditText);
        Spinner spinner=rootView.findViewById(R.id.SecondQuerySpinner);
        db=helper.getWritableDatabase();
        Cursor cursor=helper.secondQuery(db,spinner.getSelectedItem().toString(),
                begin.getText().toString(),end.getText().toString());
        text.setText("");
        while (cursor.moveToNext()) {
            text.append(cursor.getString(0) + " Средняя оценка: " + cursor.getString(1) + "\n");
        }
    }
}