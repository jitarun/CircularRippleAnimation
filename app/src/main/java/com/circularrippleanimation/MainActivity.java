package com.circularrippleanimation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button buttonImage;
    private Button mActivityFinishBtn;
    private Button mChangeBtn;
    ProgressBar mProgressBar;

    private ImageView mLogoBtnIv;
    boolean isContentVisible = true;
    LinearLayout mContentLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button1);
        buttonImage = (Button) findViewById(R.id.button2);
        mActivityFinishBtn = (Button) findViewById(R.id.activity_finish_btn);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mChangeBtn = (Button) findViewById(R.id.change_btn);
        mLogoBtnIv = (ImageView) findViewById(R.id.logoBtn_iv);
        mContentLayout = (LinearLayout) findViewById(R.id.content_layout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircularAnimUtil.startActivity(MainActivity.this, EmptyActivity.class, button, R.color.colorPrimary);
            }
        });

        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircularAnimUtil.startActivity(MainActivity.this, EmptyActivity.class, buttonImage, R.mipmap.home_bg);
            }
        });
        mActivityFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EmptyActivity.class);
                CircularAnimUtil.startActivityThenFinish(MainActivity.this, intent, view, R.color.colorPrimary);
            }
        });
        mChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChangeBtn.setEnabled(false);
                mProgressBar.setVisibility(View.VISIBLE);
                // 收缩按钮
                CircularAnimUtil.hide(mChangeBtn);
            }
        });

        mProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.GONE);
                mChangeBtn.setEnabled(true);
                // 伸展按钮
                CircularAnimUtil.show(mChangeBtn);
            }
        });

        mLogoBtnIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.animate().rotationBy(360);

                if (isContentVisible)
                    CircularAnimUtil.hideOther(mLogoBtnIv, mContentLayout);
                else
                    CircularAnimUtil.showOther(mLogoBtnIv, mContentLayout);

                isContentVisible = !isContentVisible;
            }
        });
    }
}
