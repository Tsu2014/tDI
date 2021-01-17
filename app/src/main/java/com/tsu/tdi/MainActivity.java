package com.tsu.tdi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tsu.injectlib.InjectEvent;
import com.tsu.injectlib.InjectLayout;
import com.tsu.injectlib.InjectManager;
import com.tsu.injectlib.InjectView;
import com.tsu.injectlib.OnClick;
import com.tsu.injectlib.OnLongClick;


@InjectLayout(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    TextView textView1;
    @InjectView(R.id.main_button1)
    Button button1;
    @InjectView(R.id.main_button2)
    Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Log.d(TAG , "inject -- before");
        InjectManager.inject(this);
        Log.d(TAG , "inject -- after");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this , "test1" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.main_button1})
    void action1(){
        Toast.makeText(MainActivity.this , "test1" , Toast.LENGTH_SHORT).show();
    }

    @OnLongClick({R.id.main_button2})
    boolean action2(){
        Toast.makeText(MainActivity.this , "LongClick" , Toast.LENGTH_SHORT).show();
        return false;
    }
}

