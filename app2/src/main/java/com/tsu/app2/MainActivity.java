package com.tsu.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.tsu.app2.rxjava.Emitter;
import com.tsu.app2.rxjava.Observable;
import com.tsu.app2.rxjava.ObservableOnSubscribe;
import com.tsu.app2.rxjava.Observer;
import com.tsu.injectlib.InjectLayout;
import com.tsu.injectlib.InjectManager;
import com.tsu.injectlib.InjectView;
import com.tsu.injectlib.OnClick;


@InjectLayout(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    @InjectView(R.id.main_textview1)
    TextView textView1;
    @InjectView(R.id.main_button1)
    Button button1;
    @InjectView(R.id.main_button2)
    Button button2;
    @InjectView(R.id.main_button3)
    Button button3;
    @InjectView(R.id.main_button4)
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectManager.inject(this);

    }

    @OnClick({R.id.main_button1})
    public void action1(){
        Log.d(TAG , "action1");
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(Emitter<String> emitter) throws Exception {
                emitter.onNext("hello!");
            }
        }).subscribe(new Observer() {
            @Override
            public void onSubscribe() {
                Log.d(TAG , "onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG , "onNext : "+o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @OnClick({R.id.main_button2})
    public void action2(){
        Log.d(TAG , "action2");
    }
}