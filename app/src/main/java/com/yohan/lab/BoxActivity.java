package com.yohan.lab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BoxActivity extends AppCompatActivity {

    @BindView(R.id.textView_box)
    TextView box;
    @OnClick(R.id.button_plus)
    public void doBig(){
        box.setWidth(box.getWidth()+10);
        box.setHeight(box.getHeight()+10);
    }
    @OnClick(R.id.button_minus)
    public void doSmall(){
        box.setWidth(box.getWidth()-10);
        box.setHeight(box.getHeight()-10);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box);
        ButterKnife.bind(this);
    }
}
