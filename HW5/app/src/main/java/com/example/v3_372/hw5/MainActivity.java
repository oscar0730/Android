package com.example.v3_372.hw5;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {

    private GridView mGridView;
    private ImageSwitcher mImgSwitcher;

    private Integer[] miImgArr = {
            R.drawable.img01, R.drawable.img02, R.drawable.img03,
            R.drawable.img04, R.drawable.img05, R.drawable.img06,
            R.drawable.img07, R.drawable.img08, R.drawable.dog1,
            R.drawable.dog2,  R.drawable.dog3,  R.drawable.dog4};

    private Integer[] miThumbImgArr = {
            R.drawable.img01th, R.drawable.img02th, R.drawable.img03th,
            R.drawable.img04th, R.drawable.img05th, R.drawable.img06th,
            R.drawable.img07th, R.drawable.img08th, R.drawable.dog1,
            R.drawable.dog2,   R.drawable.dog3,  R.drawable.dog4};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImgSwitcher = (ImageSwitcher) findViewById(R.id.imgSwitcher);

        mImgSwitcher.setFactory(this);	// 主程式類別必須implements ViewSwitcher.ViewFactory
        mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

        ImageAdapter imgAdap = new ImageAdapter(this, miThumbImgArr);

        mGridView = (GridView)findViewById(R.id.gridView);
        mGridView.setAdapter(imgAdap);
        mGridView.setOnItemClickListener(gridViewOnItemClick);
    }

    @Override
    public View makeView() {
        ImageView v = new ImageView(this);
        v.setBackgroundColor(0xFF000000);
        v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        v.setLayoutParams(new ImageSwitcher.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        v.setBackgroundColor(Color.WHITE);
        return v;
    }



    private AdapterView.OnItemClickListener gridViewOnItemClick = new
            AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent,
                                        View v,
                                        int position,
                                        long id) {

                    Animation alpha_in = new AlphaAnimation(0.0f,1.0f);
                    alpha_in.setDuration(3000);
                    Animation alpha_out = new AlphaAnimation(1.0f,0.0f);
                    alpha_out.setDuration(2000);

                    Animation scale_in = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,50,50);
                    scale_in.setStartOffset(2500);
                    scale_in.setDuration(3000);
                    Animation scale_out = new ScaleAnimation(1.0f,0.0f,1.0f,0.0f,50,50);
                    scale_out.setDuration(3000);

                    Animation rotate_in = new RotateAnimation(0,360,50,50);
                    rotate_in.setStartOffset(2500);
                    rotate_in.setDuration(3000);
                    Animation rotate_out = new RotateAnimation(0,360,50,50);
                    rotate_out.setDuration(3000);

                    AnimationSet scale_rotate_in = new AnimationSet(false);
                    scale_rotate_in.addAnimation(scale_in);
                    scale_rotate_in.addAnimation(rotate_in);
                    AnimationSet scale_rotate_out = new AnimationSet(false);
                    scale_rotate_out.addAnimation(scale_out);
                    scale_rotate_out.addAnimation(rotate_out);

                    Animation trans_in = new TranslateAnimation(0,0,-250,0);
                    trans_in.setDuration(3000);
                    Animation trans_out = new TranslateAnimation(0,0,0,350);
                    trans_out.setDuration(3000);

                    AnimationSet scale_rotate_trans_in= new AnimationSet(false);
                    scale_rotate_trans_in.addAnimation(scale_in);
                    scale_rotate_trans_in.addAnimation(rotate_in);
                    scale_rotate_trans_in.addAnimation(trans_in);
                    AnimationSet scale_rotate_trans_out= new AnimationSet(false);
                    scale_rotate_trans_out.addAnimation(scale_out);
                    scale_rotate_trans_out.addAnimation(rotate_out);
                    scale_rotate_trans_out.addAnimation(trans_out);

                    switch ((int)(Math.random()*4 + 1)) {
                        case 1:
                            mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.alpha_in));
                            mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.alpha_out));
                            break;
                        case 2:
                            mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.trans_in));
                            mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.trans_out));
                            break;
                        case 3:
                            mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.scale_rotate_in));
                            mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.scale_rotate_out));
                            break;
                        case 4:
                            mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.scale_rotate_trans_in));
                            mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.scale_rotate_trans_out));
                            break;
                        case 5:
                            mImgSwitcher.setInAnimation(alpha_in);
                            mImgSwitcher.setOutAnimation(alpha_out);
                            break;
                        case 6:
                            mImgSwitcher.setInAnimation(scale_rotate_in);
                            mImgSwitcher.setOutAnimation(scale_rotate_out);
                            break;
                        case 7:
                            mImgSwitcher.setInAnimation(scale_rotate_trans_in);
                            mImgSwitcher.setOutAnimation(scale_rotate_trans_out);
                            break;
                        case 8:
                            mImgSwitcher.setInAnimation(trans_in);
                            mImgSwitcher.setOutAnimation(trans_out);
                    }

                    mImgSwitcher.setImageResource(miImgArr[position]);
                }
            };
}

