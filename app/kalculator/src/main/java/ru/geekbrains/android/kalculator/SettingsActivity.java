package ru.geekbrains.android.kalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private MaterialCheckBox systemDarkMode;
    private SwitchMaterial darkSwitch;

//    Записывается инфа о темной/светлых режимах
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

//        Возврат к главной активности по иконке слева сверху
        findViewById(R.id.back).setOnClickListener((v) -> this.finish());

        settings = getSharedPreferences(MainActivity.APP_PREFERENCES, MODE_PRIVATE);
        systemDarkMode = findViewById(R.id.system_dark_mode);
        darkSwitch = findViewById(R.id.dark_light_switch);

        systemDarkMode.setOnCheckedChangeListener(this);
        darkSwitch.setOnCheckedChangeListener(this);

        /*Если активити запускается впервые, смотрим на файл и выставляем dark/light mode и положения переключателей*/
        if(savedInstanceState == null) {
            checkSettings(settings);
        }

    }

    private void checkSettings(SharedPreferences settings) {
        int night_mode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
        if(settings.contains(MainActivity.APP_PREFERENCES_CUSTOM_DARK_MODE)) {
            if(settings.getBoolean(MainActivity.APP_PREFERENCES_CUSTOM_DARK_MODE, false)) {
                night_mode = AppCompatDelegate.MODE_NIGHT_YES;
                darkSwitch.setChecked(true);
            } else {
                night_mode = AppCompatDelegate.MODE_NIGHT_NO;
            }
        }
        AppCompatDelegate.setDefaultNightMode(night_mode);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch(buttonView.getId()) {
            case R.id.system_dark_mode:
                changeDarkCheckbox();
                break;
            case R.id.dark_light_switch:
                changeDarkSwitch();
        }
    }

    private void changeDarkCheckbox() {
        if (systemDarkMode.isChecked()) {
            darkSwitch.setEnabled(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            settings.edit().remove(MainActivity.APP_PREFERENCES_CUSTOM_DARK_MODE).apply();
        } else {
            darkSwitch.setEnabled(true);
            if(darkSwitch.isChecked()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                settings.edit().putBoolean(MainActivity.APP_PREFERENCES_CUSTOM_DARK_MODE, true).apply();
            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                settings.edit().putBoolean(MainActivity.APP_PREFERENCES_CUSTOM_DARK_MODE, false).apply();
            }
        }
    }

    private void changeDarkSwitch() {
//        Это необходимая проверка условия выхода из метода, иначе система зациклиться
        if(systemDarkMode.isChecked())
            return;

        if(darkSwitch.isChecked()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            settings.edit().putBoolean(MainActivity.APP_PREFERENCES_CUSTOM_DARK_MODE, true).apply();
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            settings.edit().putBoolean(MainActivity.APP_PREFERENCES_CUSTOM_DARK_MODE, false).apply();
        }
    }
}