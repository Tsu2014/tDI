package com.tsu.tdi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

@Lance("Debug")
public class DebugActivity extends AppCompatActivity {

    private final String TAG = "DebugActivity";
    TextView textView1;
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

    }
}