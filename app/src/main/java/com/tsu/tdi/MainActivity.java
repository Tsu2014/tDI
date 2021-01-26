package com.tsu.tdi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tsu.injectlib.InjectEvent;
import com.tsu.injectlib.InjectLayout;
import com.tsu.injectlib.InjectManager;
import com.tsu.injectlib.InjectView;
import com.tsu.injectlib.OnClick;
import com.tsu.injectlib.OnLongClick;
import com.tsu.tdi.rxjava.Observable;
import com.tsu.tdi.rxjava.Observer;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


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

    @InjectView(R.id.main_webview1)
    WebView webView;
    OkHttpClient client = new OkHttpClient.Builder().readTimeout(5 , TimeUnit.SECONDS).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectManager.inject(this);

    }

    @OnClick({R.id.main_button1 , R.id.main_button2})
    public void action1(){
        Toast.makeText(MainActivity.this , "test1" , Toast.LENGTH_SHORT).show();

    }

    @OnLongClick({R.id.main_button2})
    public boolean action2(){
        Toast.makeText(MainActivity.this , "LongClick" , Toast.LENGTH_SHORT).show();
        return false;
    }

    @OnClick(R.id.main_button3)
    public void create(){
        Observable.create(new Observable<String>(){

            @Override
            public void subscribe(Observer<String> observer) {
                for(int i = 0 ;i<10;i++){
                    observer.onNext("value : "+i);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                observer.onComplete();
            }
        }).flatMap().subscribe(new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.d(TAG , "onNext C : "+s);
            }

            @Override
            public void onComplete() {
                Log.d(TAG , "onComplete C !!!");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

