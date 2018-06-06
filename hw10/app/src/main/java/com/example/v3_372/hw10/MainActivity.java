package com.example.v3_372.hw10;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import android.support.design.widget.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String DB_FILE = "friends.db",
            DB_TABLE = "friends";
    private SQLiteDatabase mFriendDb;
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    private  String name, number, type;
    private ArrayList contact=new ArrayList(), searchContact=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actBar = getSupportActionBar();
        actBar.setDisplayShowTitleEnabled(true);
        actBar.setDisplayShowHomeEnabled(true);

        DbOpenHelper friendDbOpenHelper =
                new DbOpenHelper(getApplicationContext(), DB_FILE, null, 1);
        mFriendDb = friendDbOpenHelper.getWritableDatabase();

        Cursor cursor = mFriendDb.rawQuery(
                "select DISTINCT tbl_name from sqlite_master where tbl_name = '" +
                        DB_TABLE + "'", null);

        if(cursor != null) {
            if(cursor.getCount() == 0)	// 沒有資料表，要建立一個資料表。
                mFriendDb.execSQL("CREATE TABLE " + DB_TABLE + " (" +
                        "_id INTEGER PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "sex TEXT," +
                        "address TEXT);");

            cursor.close();
        }

        mSectionsPagerAdapter = new SectionsPagerAdapter(
                getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFriendDb.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchView searchView = (SearchView)menu.findItem(R.id.menuItemSearch).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Cursor c = mFriendDb.query(true, DB_TABLE, new String[]{"name", "sex",
                        "address"}, 	null, null, null, null, null, null);
                Cursor s = mFriendDb.query(true, DB_TABLE, new String[]{"name", "sex",
                        "address"}, "name=" + "\"" + query
                        + "\"", null, null, null, null, null);
                if (c == null)
                    Toast.makeText(MainActivity.this, "讀取失敗", Toast.LENGTH_LONG).show();
                else if (c.getCount() == 0)
                    Toast.makeText(MainActivity.this, "沒有資料", Toast.LENGTH_LONG).show();
                else {
                    c.moveToFirst();
                    contact.add(c.getString(0) + c.getString(1)  + c.getString(2));
                    while (c.moveToNext())
                        contact.add(c.getString(0) + c.getString(1)  + c.getString(2));
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, contact);
                    ((SearchContact) getSupportFragmentManager().getFragments().get(1)).mlistView.setAdapter(arrayAdapter);

                    if (s.getCount() == 0)
                        Toast.makeText(MainActivity.this, "沒有找到聯絡人", Toast.LENGTH_LONG).show();
                    else {
                        s.moveToFirst();
                        searchContact.add(s.getString(0) + s.getString(1) + s.getString(2));
                        while (s.moveToNext())
                            searchContact.add(s.getString(0) + s.getString(1) + s.getString(2));
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, searchContact);
                        ((SearchContact) getSupportFragmentManager().getFragments().get(1)).mlistView2.setAdapter(arrayAdapter2);
                    }
                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contact.clear();
                searchContact.clear();
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemAdd:
                name=((AddContact) getSupportFragmentManager().getFragments().get(0)).mName.getText().toString();
                number=((AddContact) getSupportFragmentManager().getFragments().get(0)).mNumber.getText().toString();
                type=((AddContact) getSupportFragmentManager().getFragments().get(0)).type;
                ContentValues newRow = new ContentValues();
                newRow.put("name", name);
                newRow.put("sex", number);
                newRow.put("address", type);
                mFriendDb.insert(DB_TABLE, null, newRow);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new AddContact();
                    break;
                case 1:
                    fragment = new SearchContact();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Add";
                case 1:
                    return "Search";
                default:
                    return null;
            }
        }
    }
}

