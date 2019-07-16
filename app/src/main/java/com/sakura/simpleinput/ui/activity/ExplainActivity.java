package com.sakura.simpleinput.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.sakura.simpleinput.R;
import com.sakura.simpleinput.utils.StatusBarUtil;

/**
 * @author zhangzheng
 * @Date 2019-07-04 17:38
 * @ClassName ExplainActivity
 * <p>
 * Desc :
 */
public class ExplainActivity extends AppCompatActivity {

    private ImageView mBackIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);
        mBackIv = findViewById(R.id.explain_title_icon_back_iv);
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        StatusBarUtil.setColor(this, this.getColor(R.color.colorPrimary));
    }
}
