package com.example.afinal;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

public class MyApp extends Application {
    public static final String CHANNEL_ID = "CHANNEL WARNING";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name_channel = getString(R.string.channel_name);
            String description_channel = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel_temp = new NotificationChannel(CHANNEL_ID, name_channel, importance);
            channel_temp.setDescription(description_channel);
            channel_temp.enableVibration(true);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel_temp);
                Log.e("Tag","Tạo thông báo");
            }
        }
    }

}
