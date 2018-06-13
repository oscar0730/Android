package com.example.v3_372.hw6;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainFragment extends Fragment {

    public enum GameResultType {
        TYPE_1, TYPE_2, HIDE
    }

    // 所屬的 Activity 必須實作以下介面中的callback方法
    public interface CallbackInterface {
        public void updateGameResult(int iCountSet,
                                     int iCountPlayerWin,
                                     int iCountComWin,
                                     int iCountDraw);
        public void enableGameResult(GameResultType type);
    };

    private CallbackInterface mCallback;
    private ImageView mImgRollingDice;
    private ImageButton mImgBtnDice;
    private TextView mTxtDiceResult;

    // 新增統計遊戲局數和輸贏的變數
    private int miCountSet = 0,
                miCountPlayerWin = 0,
                miCountComWin = 0,
                miCountDraw = 0;
    private Button mBtnShowResult, mBtnHiddenResult;

    private static class StaticHandler extends Handler {
        private final WeakReference<MainFragment> mActivity;

        public StaticHandler(MainFragment activity) {
            mActivity = new WeakReference<MainFragment>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainFragment activity = mActivity.get();
            if (activity == null) return;

            int iRand = (int)(Math.random()*6 + 1);

            String s = activity.getString(R.string.result);
            activity.mTxtDiceResult.setText(s + iRand);
            switch (iRand) {
                case 1:
                    activity.mImgBtnDice.setImageResource(R.drawable.dice01);
                    activity.mTxtDiceResult.setText("判定輸贏：user wins");
                    activity.miCountPlayerWin++;
                    activity.miCountSet++;
                    activity.mImgRollingDice.setImageResource(R.drawable.dice01);
                    break;
                case 2:
                    activity.mImgBtnDice.setImageResource(R.drawable.dice02);
                    activity.mTxtDiceResult.setText("判定輸贏：user wins");
                    activity.miCountPlayerWin++;
                    activity.miCountSet++;
                    activity.mImgRollingDice.setImageResource(R.drawable.dice02);
                    break;
                case 3:
                    activity.mImgBtnDice.setImageResource(R.drawable.dice03);
                    activity.mTxtDiceResult.setText("判定輸贏：draw");
                    activity.miCountDraw++;
                    activity.miCountSet++;
                    activity.mImgRollingDice.setImageResource(R.drawable.dice03);
                    break;
                case 4:
                    activity. mImgBtnDice.setImageResource(R.drawable.dice04);
                    activity.mTxtDiceResult.setText("判定輸贏：draw");
                    activity.miCountDraw++;
                    activity.miCountSet++;
                    activity.mImgRollingDice.setImageResource(R.drawable.dice04);
                    break;
                case 5:
                    activity.mImgBtnDice.setImageResource(R.drawable.dice05);
                    activity.mTxtDiceResult.setText("判定輸贏：computer wins");
                    activity.miCountComWin++;
                    activity.miCountSet++;
                    activity.mImgRollingDice.setImageResource(R.drawable.dice05);
                    break;
                case 6:
                    activity. mImgBtnDice.setImageResource(R.drawable.dice06);
                    activity.mTxtDiceResult.setText("判定輸贏：computer wins");
                    activity.miCountComWin++;
                    activity.miCountSet++;
                    activity.mImgRollingDice.setImageResource(R.drawable.dice06);
                    break;
            }
            activity.mCallback.updateGameResult(activity.miCountSet, activity.miCountPlayerWin, activity.miCountComWin, activity.miCountDraw);
        }
    }

    public final StaticHandler mHandler = new StaticHandler(this);

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (CallbackInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    "must implement GameFragment.CallbackInterface.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 利用inflater物件的inflate()方法取得介面佈局檔，並將最後的結果傳回給系統
       return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 必須先呼叫getView()取得程式畫面物件，然後才能呼叫它的
        // findViewById()取得介面物件
        mTxtDiceResult = (TextView) getView().findViewById(R.id.txtDiceResult);
        mImgBtnDice = (ImageButton) getView().findViewById(R.id.imgBtnDice);
        mImgRollingDice = (ImageView) getView().findViewById(R.id.imgRollingDice);

        // 以下介面元件是在另一個Fragment中，因此必須呼叫所屬的Activity
        // 才能取得這些介面元件
/*
        mEdtCountSet = (EditText) getActivity().findViewById(R.id.edtCountSet);
        mEdtCountPlayerWin = (EditText) getActivity().findViewById(R.id.edtCountPlayerWin);
        mEdtCountComWin = (EditText) getActivity().findViewById(R.id.edtCountComWin);
        mEdtCountDraw = (EditText) getActivity().findViewById(R.id.edtCountDraw);
*/

        mImgBtnDice.setOnClickListener(imgBtnDiceOnClick);

        mBtnShowResult = (Button) getView().findViewById(R.id.btnShowResult);
        mBtnHiddenResult = (Button) getView().findViewById(R.id.btnHiddenResult);

        mBtnShowResult.setOnClickListener(btnShowResultOnClick);
        mBtnHiddenResult.setOnClickListener(btnHiddenResultOnClick);
    }

    private View.OnClickListener imgBtnDiceOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            // Decide computer play.
            Resources res = getResources();
            final AnimationDrawable animDraw =
                    (AnimationDrawable) res.getDrawable(R.drawable.anim_roll_dice);
            mImgRollingDice.setImageDrawable(animDraw);
            animDraw.start();

            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    animDraw.stop();
                    mHandler.sendMessage(mHandler.obtainMessage());
                }
            }).start();
//            mEdtCountSet.setText(String.valueOf(miCountSet));
        }
    };

    private View.OnClickListener btnShowResultOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mCallback.enableGameResult(GameResultType.TYPE_1);
        }
    };

    private View.OnClickListener btnHiddenResultOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mCallback.enableGameResult(GameResultType.HIDE);
        }
    };
}
