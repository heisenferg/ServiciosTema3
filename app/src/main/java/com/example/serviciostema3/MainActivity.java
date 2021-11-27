package com.example.serviciostema3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

private Tester tester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tester=new Tester();
    }

    public void Arrancar (View v){
        startService(new Intent(getBaseContext(), WirelessTester.class));

    }

    public void Detener (View v){
        stopService(new Intent(getBaseContext(), WirelessTester.class));
    }
}