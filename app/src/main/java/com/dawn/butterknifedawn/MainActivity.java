package com.dawn.butterknifedawn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindString(R.string.app_name)
    String appName;
    @BindColor(R.color.black)
    int black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @Nullable @BindView(R.id.tv_content2) TextView tvContent2;
    @Optional @OnClick(R.id.tv_content3)
    void clickContents(){
        // TODO
    }
}
