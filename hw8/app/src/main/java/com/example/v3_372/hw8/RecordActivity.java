package com.example.v3_372.hw8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by V3-372 on 2018/5/16.
 */

public class RecordActivity extends AppCompatActivity {
    private ArrayList _year, _month, _day, _type, _money, _record=new ArrayList();
    private ListView _listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_record);

        Bundle bundle =this.getIntent().getExtras();
        _year= bundle.getStringArrayList("year");
        _month= bundle.getStringArrayList("month");
        _day= bundle.getStringArrayList("day");
        _type= bundle.getStringArrayList("type");
        _money= bundle.getStringArrayList("money");

        _listView = (ListView) findViewById(R.id.listView);

        for(int i=0;i<_year.size();i++)
            _record.add("項目" + Integer.toString(i) + "   " + _year.get(i) + "/" + _month.get(i) + "/" + _day.get(i) + "   " + _type.get(i) + "   " + _money.get(i));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, _record );
        _listView.setAdapter(arrayAdapter);
    }
}
