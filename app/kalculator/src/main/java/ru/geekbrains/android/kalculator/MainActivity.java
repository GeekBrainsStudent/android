package ru.geekbrains.android.kalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String KEY_CALCULATOR = "key_calc";

    private Calculator mCalculator;
    private EditText display;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelable(KEY_CALCULATOR, mCalculator);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        инициализация display
        initViews();
//        восстанавливаем mCalculator, если есть что
        Calculator calc = null;
        if(savedInstanceState != null)
            calc = savedInstanceState.getParcelable(KEY_CALCULATOR);
        if( calc != null) {
            mCalculator = calc;
        } else {
            mCalculator = new Calculator();
        }
        mCalculator.setDisplay(display);
//        Устанавливаем листнеры на все кнопки
        setButtonsListener();
    }

    private void initViews() {
        display = findViewById(R.id.display);
    }

    private void setButtonsListener() {
        findViewById(R.id.mr).setOnClickListener(this);
        findViewById(R.id.mc).setOnClickListener(this);
        findViewById(R.id.backspace).setOnClickListener(this);
        findViewById(R.id.c).setOnClickListener(this);
        findViewById(R.id.mplus).setOnClickListener(this);
        findViewById(R.id.mminus).setOnClickListener(this);
        findViewById(R.id.radic).setOnClickListener(this);
        findViewById(R.id.percent).setOnClickListener(this);
        findViewById(R.id.seven).setOnClickListener(this);
        findViewById(R.id.eight).setOnClickListener(this);
        findViewById(R.id.nine).setOnClickListener(this);
        findViewById(R.id.divide).setOnClickListener(this);
        findViewById(R.id.fourth).setOnClickListener(this);
        findViewById(R.id.five).setOnClickListener(this);
        findViewById(R.id.six).setOnClickListener(this);
        findViewById(R.id.multiply).setOnClickListener(this);
        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
        findViewById(R.id.minus).setOnClickListener(this);
        findViewById(R.id.zero).setOnClickListener(this);
        findViewById(R.id.dot).setOnClickListener(this);
        findViewById(R.id.calc).setOnClickListener(this);
        findViewById(R.id.plus).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.mr:
                mCalculator.pushMemRead();
                break;
            case R.id.mc:
                mCalculator.pushMemClear();
                break;
            case R.id.backspace:
                mCalculator.pushBackspace();
                break;
            case R.id.c:
                mCalculator.pushClear();
                break;
            case R.id.mplus:
                mCalculator.pushMemPositive();
                break;
            case R.id.mminus:
                mCalculator.pushMemNegative();
                break;
            case R.id.radic:
                mCalculator.pushRadic();
                break;
            case R.id.percent:
                mCalculator.pushPercent();
                break;
            case R.id.calc:
                mCalculator.pushCalc();
                break;
            case R.id.plus:
            case R.id.minus:
            case R.id.multiply:
            case R.id.divide:
                mCalculator.pushOperation(((Button) v).getText().toString());
                break;
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.fourth:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
            case R.id.zero:
            case R.id.dot:
                mCalculator.pushNumber(((Button) v).getText().toString());
                break;
        }
    }
}