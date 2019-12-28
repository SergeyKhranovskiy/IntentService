package com.example.intentservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 101;
    private static String CHANNEL_ID = "Cat channel";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, MyMessageService.class);
        intent.putExtra(MyMessageService.CHANNEL_ID, getResources().getString(R.string.response));
        startService(intent);
    }
}
