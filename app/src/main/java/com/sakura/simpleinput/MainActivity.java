package com.sakura.simpleinput;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout mSettingLayout;
    private ConstraintLayout mExplainLayout;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSettingLayout = findViewById(R.id.main_input_method_setting_layout);
        mExplainLayout = findViewById(R.id.main_input_method_explain_layout);
        mTitle = findViewById(R.id.main_input_method_title_tv);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "font.ttf");
        mTitle.setTypeface(typeFace);
        mSettingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mExplainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ExplainActivity.class));
            }
        });
    }
}
