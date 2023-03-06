package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvNum;
    SharedPreferences mPreferences;
    Button btnBlack, btnRed, btnBlue, btnGreen, btnCount, btnReset;
    private String sharedPrefiles = "com.example.android.hellosharedprefs";
    int count_num;
    int CurrentColor;

    public void assignId(Button btn, int id){
        btn = (Button) findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt("count", count_num);
        preferencesEditor.putInt("color", CurrentColor);
        preferencesEditor.clear();
        preferencesEditor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNum = (TextView)findViewById(R.id.tv_num);


        assignId(btnBlack, R.id.btn_black);
        assignId(btnRed, R.id.btn_red);
        assignId(btnBlue, R.id.btn_blue);
        assignId(btnGreen, R.id.btn_green);
        assignId(btnCount, R.id.btn_count);
        assignId(btnReset, R.id.btn_reset);

        mPreferences = getSharedPreferences(sharedPrefiles, MODE_PRIVATE);
        if(savedInstanceState == null){
            count_num = mPreferences.getInt("count", 0);
            tvNum.setText(String.format("%s", count_num));

            CurrentColor = mPreferences.getInt("color", CurrentColor);
            tvNum.setBackgroundColor(ContextCompat.getColor(this, CurrentColor));
        }else{

        }
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        count_num = Integer.parseInt(tvNum.getText().toString());

        if(btn.getId() == R.id.btn_black){
            tvNum.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
            CurrentColor = R.color.black;
        }
        if(btn.getId() == R.id.btn_red){
            tvNum.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
            CurrentColor = R.color.red;
        }
        if(btn.getId() == R.id.btn_blue){
            tvNum.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
            CurrentColor = R.color.blue;
        }
        if(btn.getId() == R.id.btn_green){
            tvNum.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
            CurrentColor = R.color.green;
        }
        if(btn.getId() == R.id.btn_count){
            count_num += 1;
            tvNum.setText(String.valueOf(count_num));
        }

    }
}