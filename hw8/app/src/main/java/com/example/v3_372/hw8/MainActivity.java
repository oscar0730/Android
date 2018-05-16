package com.example.v3_372.hw8;

import java.util.ArrayList;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner SpinnerType;
    private EditText EdtDate;
    private EditText EdtMoney;
    private DatePicker DatePicker;
    private Button BtnInput;
    private Button BtnRecord;
    private ArrayList _year, _month, _day, _type, _money;
    private String selectType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpinnerType=(Spinner)findViewById(R.id.spinner);
        EdtDate=(EditText)findViewById(R.id.Date);
        EdtMoney=(EditText)findViewById(R.id.Money);
        DatePicker=(DatePicker)findViewById(R.id.datePicker);
        BtnInput=(Button)findViewById(R.id.btnInput);
        BtnRecord=(Button)findViewById(R.id.btnRecord);

        SpinnerType.setOnItemSelectedListener(spnTypeOnItemSelect);
        BtnInput.setOnClickListener(btnInputOnClick);
        BtnRecord.setOnClickListener(btnRecordOnClick);

        _type=new ArrayList();
        _money=new ArrayList();
        _year=new ArrayList();
        _month=new ArrayList();
        _day=new ArrayList();
    }

    private AdapterView.OnItemSelectedListener spnTypeOnItemSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            selectType=adapterView.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private Button.OnClickListener btnInputOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            _type.add(selectType);
            _year.add(Integer.toString(DatePicker.getYear()));
            _month.add(Integer.toString(DatePicker.getMonth()+1));
            _day.add(Integer.toString(DatePicker.getDayOfMonth()));
            _money.add(EdtMoney.getText().toString());

            Toast toast=Toast.makeText(MainActivity.this, EdtMoney.getText().toString(), Toast.LENGTH_LONG);
            toast.show();
        }
    };

    private Button.OnClickListener btnRecordOnClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, RecordActivity.class);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("type", _type);
            bundle.putStringArrayList("year", _year);
            bundle.putStringArrayList("month", _month);
            bundle.putStringArrayList("day", _day);
            bundle.putStringArrayList("money", _money);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
}

