package com.example.v3_372.hw10;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

public class AddContact extends Fragment{

    public EditText mName, mNumber;
    private Spinner mType;
    public String type;

    public AddContact(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.addcontact, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mName=(EditText)getView().findViewById(R.id.Name);
        mNumber=(EditText)getView().findViewById(R.id.Number);
        mType=(Spinner)getView().findViewById(R.id.spnType);

        mType.setOnItemSelectedListener(spnTypeOnItemClick);
    }

    private AdapterView.OnItemSelectedListener spnTypeOnItemClick=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            type=parent.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}

