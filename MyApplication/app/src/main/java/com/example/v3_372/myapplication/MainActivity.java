package com.example.v3_372.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {


    private RadioGroup mRadGrp;
    private RadioGroup mSpnSex;
    private CheckBox mChkBoxMusic, mChkBoxSing, mChkBoxDance, mChkBoxTravel,
                        mChkBoxReading, mChkBoxWriting,mChkBoxClimbing,
                        mChkBoxSwim,mChkBoxEating,mChkBoxDrawing;
    private Button mBtnOK;
    private TextView mTxtHobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRadGrp = (RadioGroup) findViewById(R.id.radGrpAge);
        mChkBoxMusic =(CheckBox)findViewById(R.id.chkboxmusic);
        mChkBoxSing =(CheckBox)findViewById(R.id.chkboxsing);
        mChkBoxDance =(CheckBox)findViewById(R.id.chkboxdance);
        mChkBoxTravel =(CheckBox)findViewById(R.id.chkboxtravel);
        mChkBoxReading =(CheckBox)findViewById(R.id.chkboxreadingg);
        mChkBoxWriting =(CheckBox)findViewById(R.id.chkboxwriting);
        mChkBoxClimbing =(CheckBox)findViewById(R.id.chkboxclimbing);
        mChkBoxSwim =(CheckBox)findViewById(R.id.chkboxswim);
        mChkBoxEating =(CheckBox)findViewById(R.id.chkboxeating);
        mChkBoxDrawing =(CheckBox)findViewById(R.id.chkboxdrawing);
        mBtnOK = (Button) findViewById(R.id.btnOK);
        mTxtHobby = (TextView) findViewById(R.id.txtHobby);

        mBtnOK.setOnClickListener(getBtnOKOnClick);
}

    private View.OnClickListener getBtnOKOnClick =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String s =getString(R.string.hobby);

            if(mChkBoxMusic.isChecked())
                s += mChkBoxMusic.getText().toString();

            if(mChkBoxSing.isChecked())
                s += mChkBoxSing.getText().toString();

            if(mChkBoxDance.isChecked())
                s += mChkBoxDance.getText().toString();

            if(mChkBoxTravel.isChecked())
                s += mChkBoxTravel.getText().toString();

            if(mChkBoxReading.isChecked())
                s += mChkBoxReading.getText().toString();

            if(mChkBoxWriting.isChecked())
                s += mChkBoxWriting.getText().toString();

            if(mChkBoxClimbing.isChecked())
                s += mChkBoxClimbing.getText().toString();

            if(mChkBoxSwim.isChecked())
                s += mChkBoxSwim.getText().toString();

            if(mChkBoxEating.isChecked())
                s += mChkBoxEating.getText().toString();

            if(mChkBoxDrawing.isChecked())
                s += mChkBoxDrawing.getText().toString();

            mTxtHobby.setText(s);
        }
    };



}
