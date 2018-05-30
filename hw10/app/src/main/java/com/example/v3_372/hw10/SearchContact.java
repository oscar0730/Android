package com.example.v3_372.hw10;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SearchContact extends Fragment {

    public ListView mlistView, mlistView2;

    public SearchContact(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.searchcontact, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mlistView = (ListView)getView().findViewById(R.id.listView);
        mlistView2 = (ListView)getView().findViewById(R.id.listView2);
    }
}

