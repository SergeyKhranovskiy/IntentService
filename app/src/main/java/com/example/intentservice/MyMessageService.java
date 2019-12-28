package com.example.intentservice;

import android.app.IntentService;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyMessageService extends IntentService {


    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 101;
    public static final String CHANNEL_ID = "Cat channel";

    public MyMessageService() {
        super("MyMessageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        synchronized (this) {
            try {
                wait(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String text = intent.getStringExtra(CHANNEL_ID);
        showText(text);
    }

    private void showText(final String text) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(MyMessageService.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_adb_black_24dp)
                        .setContentTitle("Напоминание")
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .setContentText("Пора покормить кота")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        createChanel(notificationManager);
        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    public static void createChanel(NotificationManager manager) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    }

}
