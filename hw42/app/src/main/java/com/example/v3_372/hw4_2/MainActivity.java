package com.example.v3_372.hw4_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtComPlay, mTxtResult;
    private Button mBtnScissors, mBtnStone, mBtnPaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtComPlay = (TextView)findViewById(R.id.txtComPlay);
        mTxtResult = (TextView)findViewById(R.id.txtResult);
        mBtnScissors = (Button)findViewById(R.id.btnScissors);
        mBtnStone = (Button)findViewById(R.id.btnStone);
        mBtnPaper = (Button)findViewById(R.id.btnPaper);

        mBtnScissors.setOnClickListener(btnScissorsOnClick);
        mBtnStone.setOnClickListener(btnStoneOnClick);
        mBtnPaper.setOnClickListener(btnPaperOnClick);
    }

    private View.OnClickListener btnScissorsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            // 決定電腦出拳.
            int iComPlay = (int)(Math.random()*3 + 1);

            Judge outcome=new Judge();
            String ComS=" ";

            switch(iComPlay){
                case 1:
                    ComS="剪刀";
                    break;
                case 2:
                    ComS="石頭";
                    break;
                case 3:
                    ComS="布";
                    break;
            }

            mTxtComPlay.setText(ComS);
            mTxtResult.setText(outcome.getOutcome(ComS,"剪刀"));
        }

    };

    private View.OnClickListener btnStoneOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            // 決定電腦出拳.
            int iComPlay = (int)(Math.random()*3 + 1);

            Judge outcome=new Judge();
            String ComS=" ";

            switch(iComPlay){
                case 1:
                    ComS="剪刀";
                    break;
                case 2:
                    ComS="石頭";
                    break;
                case 3:
                    ComS="布";
                    break;
            }

            mTxtComPlay.setText(ComS);
            mTxtResult.setText(outcome.getOutcome(ComS,"石頭"));
        }
    };

    private View.OnClickListener btnPaperOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            // 決定電腦出拳.
            int iComPlay = (int)(Math.random()*3 + 1);

            Judge outcome=new Judge();
            String ComS=" ";

            switch(iComPlay){
                case 1:
                    ComS="剪刀";
                    break;
                case 2:
                    ComS="石頭";
                    break;
                case 3:
                    ComS="布";
                    break;
            }

            mTxtComPlay.setText(ComS);
            mTxtResult.setText(outcome.getOutcome(ComS,"布"));
        }
    };
}
