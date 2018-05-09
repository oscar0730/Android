package com.example.v3_372.hw7;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int[] diceImg = new int[]{
            R.drawable.dice01, R.drawable.dice02, R.drawable.dice03,
            R.drawable.dice04, R.drawable.dice05, R.drawable.dice06
    };
    private Button btnRun, btnResult;
    private ImageView mImgDice;
    private int miGameCount = 0, miCountP_Win = 0, miCountC_Win = 0, miCountDraw = 0;
    private Intent link = new Intent();
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRun = (Button) findViewById(R.id.btnRun);
        btnResult = (Button) findViewById(R.id.btnShowResult);
        mImgDice = (ImageView) findViewById(R.id.imgDice);

        link.setClass(this, ResultActivity.class);
        btnRun.setOnClickListener(btnOnclick);
        btnResult.setOnClickListener(btnOnclick);
    }

    public void resultDice() {//大小輸贏
        int num = (int) (Math.random() * 6);
        mImgDice.setImageDrawable(getResources().getDrawable(diceImg[num]));
        miGameCount ++;
        String output;
        switch (num / 2) {
            case 0: //玩家贏
                output = getString(R.string.playerWin);
                Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
                miCountP_Win ++;
                break;
            case 1: // 平手
                output = getString(R.string.playerDraw);
                Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
                miCountDraw ++;
                break;
            case 2: // 完家輸
                output = getString(R.string.playerLose);
                Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
                miCountC_Win ++;
                break;
        }
    }

    private View.OnClickListener btnOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.btnShowResult:
                    bundle.putInt("miCountSet",miGameCount);
                    bundle.putInt("miCountPlayerWin",miCountP_Win);
                    bundle.putInt("miCountComWin",miCountC_Win);
                    bundle.putInt("miCountDraw",miCountDraw);
                    link.putExtras(bundle);
                    startActivity(link);
                    break;

                case R.id.btnRun:
                    //動畫播放
                    Resources res = getResources();
                    final AnimationDrawable animDraw = (AnimationDrawable) res.getDrawable(R.drawable.anim_roll_dice);
                    mImgDice.setImageDrawable(animDraw);
                    animDraw.start();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animDraw.stop();
                            resultDice();
                        }
                    }, 2000);
                    break;
            }
        }
    };
}

