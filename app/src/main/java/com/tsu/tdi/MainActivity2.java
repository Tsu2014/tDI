package com.tsu.tdi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private final String TAG = "MainActivity2";

    TextView textView;
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //InjectManager.inject(this);

    }

//    @OnClick({R.id.main_button1})
//    void onClick(){
//
//    }
//
//
//    @OnLongClick({R.id.main_button2})
//    boolean onLongClick(){
//
//    }
}