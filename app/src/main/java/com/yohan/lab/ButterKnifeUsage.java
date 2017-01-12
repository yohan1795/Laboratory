package com.yohan.lab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterKnifeUsage extends AppCompatActivity {
    @BindView(R.id.editText_string)
    EditText et_string;
    @BindString(R.string.mystring)
    String myString;

    @OnClick(R.id.button_click)
    public void onClicks(){
        Toast.makeText(ButterKnifeUsage.this, myString + et_string.getText().toString(), Toast.LENGTH_SHORT).show();
    };
    @OnClick(R.id.button2)
    public void doIt(){
        Intent intent = new Intent(ButterKnifeUsage.this, BoxActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife_usage);
        ButterKnife.bind(this);
    }
}
