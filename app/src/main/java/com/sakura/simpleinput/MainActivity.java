package com.sakura.simpleinput;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout mSettingLayout;
    private ConstraintLayout mExplainLayout;
    private ConstraintLayout mAdviseLayout;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSettingLayout = findViewById(R.id.main_input_method_setting_layout);
        mExplainLayout = findViewById(R.id.main_input_method_explain_layout);
        mAdviseLayout = findViewById(R.id.main_input_method_advise_layout);
        mTitle = findViewById(R.id.main_input_method_title_tv);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "font.ttf");
        mTitle.setTypeface(typeFace);
        mSettingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mAdviseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://github.com/zzsakurazz/AndroidSimpleInput/issues");
                intent.setData(content_url);
                startActivity(intent);
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
