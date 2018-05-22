package com.example.v3_372.hw9;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int MENU_MUSIC = Menu.FIRST,
            MENU_PLAY_MUSIC = Menu.FIRST + 1,
            MENU_STOP_PLAYING_MUSIC = Menu.FIRST + 2,
            MENU_ABOUT = Menu.FIRST + 3,
            MENU_EXIT = Menu.FIRST + 4;

    private Spinner SpinnerType;
    private EditText EdtDate;
    private EditText EdtMoney;
    private DatePicker DatePicker;
    private Button BtnInput;
    private Button BtnRecord;
    private ArrayList _year, _month, _day, _type, _money;
    private String selectType;
    private RelativeLayout mRelativeLayout;
    private TextView mTxtView;

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

        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        registerForContextMenu(mRelativeLayout);
        mTxtView = (TextView) findViewById(R.id.txtView);
        registerForContextMenu(mTxtView);

        SpinnerType.setOnItemSelectedListener(spnTypeOnItemSelect);
        BtnInput.setOnClickListener(btnInputOnClick);
        BtnRecord.setOnClickListener(btnRecordOnClick);

        _type=new ArrayList();
        _money=new ArrayList();
        _year=new ArrayList();
        _month=new ArrayList();
        _day=new ArrayList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subMenu = menu.addSubMenu(0, MENU_MUSIC, 0, "背景音樂")
                .setIcon(android.R.drawable.ic_media_ff);
        subMenu.add(0, MENU_PLAY_MUSIC, 0, "播放背景音樂");
        subMenu.add(0, MENU_STOP_PLAYING_MUSIC, 1, "停止播放背景音樂");
        menu.add(0, MENU_ABOUT, 1, "關於這個程式...")
                .setIcon(android.R.drawable.ic_dialog_info);
        menu.add(0, MENU_EXIT, 2, "結束")
                .setIcon(android.R.drawable.ic_menu_close_clear_cancel);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_PLAY_MUSIC:
                Intent intent = new Intent(MainActivity.this, MediaPlayService.class);
                startService(intent);
                return true;
            case MENU_STOP_PLAYING_MUSIC:
                intent = new Intent(MainActivity.this, MediaPlayService.class);
                stopService(intent);
                return true;
            case MENU_ABOUT:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("關於這個程式")
                        .setMessage("hw9 選單程式")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton("確定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub
                                    }
                                })
                        .show();

                return true;
            case MENU_EXIT:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v == mRelativeLayout) {
            if (menu.size() == 0) {
                SubMenu subMenu = menu.addSubMenu(0, MENU_MUSIC, 0, "背景音樂");
                subMenu.add(0, MENU_PLAY_MUSIC, 0, "播放背景音樂");
                subMenu.add(0, MENU_STOP_PLAYING_MUSIC, 1, "停止播放背景音樂");
                menu.add(0, MENU_ABOUT, 1, "關於這個程式...");
                menu.add(0, MENU_EXIT, 2, "結束");
            }
        }
        else if (v == mTxtView) {
            menu.add(0, MENU_ABOUT, 1, "關於這個程式...");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        onOptionsItemSelected(item);
        return super.onContextItemSelected(item);
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


