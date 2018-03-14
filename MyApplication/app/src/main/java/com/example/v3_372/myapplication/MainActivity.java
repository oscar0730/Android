package com.example.v3_372.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText eSex, eAge;
    private Button bSub;
    private TextView tSug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eSex = (EditText) findViewById(R.id.edit_sex);
        eAge = (EditText) findViewById(R.id.edit_age);
        bSub =(Button) findViewById(R.id.button);
        tSug =(TextView) findViewById(R.id.text_sug);

        bSub.setOnClickListener(buttenOnClick);
    }

    private View.OnClickListener buttenOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            String strSex = eSex.getText().toString();
            int iAge = Integer.parseInt(eAge.getText().toString());

            String strSug = getString(R.string.text_sug);
            if(strSex.equals(getString(R.string.sex_male)))
                if(iAge < 28)
                    strSug =strSug+getString(R.string.sug_not_hurry);
                else if(iAge > 33)
                    strSug=strSug+getString(R.string.sug_get_married);
                else
                    strSug=strSug+getString(R.string.sug_find_couple);
            else
            if(iAge < 25)
                strSug=strSug+getString(R.string.sug_not_hurry);
            else if(iAge > 30)
                strSug=strSug+getString(R.string.sug_get_married);
            else
                strSug=strSug+getString(R.string.sug_find_couple);
            tSug.setText(strSug);
        }
    };
}
