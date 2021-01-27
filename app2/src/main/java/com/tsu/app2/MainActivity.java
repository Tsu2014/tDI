package com.tsu.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.tsu.app2.pissa.BasePizza;
import com.tsu.app2.pissa.Pizza;
import com.tsu.app2.pissa.PizzaA;
import com.tsu.app2.pissa.PizzaB;
import com.tsu.app2.pissa.PizzaC;
import com.tsu.app2.rxjava.Emitter;
import com.tsu.app2.rxjava.Function;
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
                emitter.onComplete();
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s+" java";
            }
        }).map(new Function<String, String>() {

            @Override
            public String apply(String s) {
                return s+" Objective-C";
            }
        }).subscribeOn().observeOn().subscribe(new Observer() {
            @Override
            public void onSubscribe() {
                Log.d(TAG , "Observer onSubscribe "+Thread.currentThread().getName());
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG , "onNext : "+o+ " - "+Thread.currentThread().getName());
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
        Pizza pizza = new BasePizza();
        PizzaA p = new PizzaA(new PizzaB(new PizzaC(pizza)));
        p.show();

    }
}