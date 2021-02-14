package ru.geekbrains.android.homeworks.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startK = findViewById(R.id.start_calc);
        startK.setOnClickListener((v) -> {
            startActivity(new Intent("com.kopyrin.vasily.intent.calc"));
        });
    }
}