package com.sakura.simpleinput.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.sakura.simpleinput.R;

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
                clickAnZHuangAction();
            }
        });
        mAdviseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
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
    @SuppressLint("WrongConstant")
    public void clickAnZHuangAction() {
        if (isActivate()) {
            ((InputMethodManager) getSystemService("input_method")).showInputMethodPicker();
            return;
        }
        toInputMethodSetting();

    }

    private void toInputMethodSetting() {
        Intent intent = new Intent("android.settings.INPUT_METHOD_SETTINGS");
        Toast.makeText(this, "您尚未激活该软件，快去激活吧\n", Toast.LENGTH_SHORT).show();
        MainActivity.this.startActivity(intent);
    }

    @SuppressLint("WrongConstant")
    public boolean isActivate() {
        for (InputMethodInfo inputMethodInfo : ((InputMethodManager) getSystemService("input_method")).getEnabledInputMethodList()) {
            if (getPackageName().equals(inputMethodInfo.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
