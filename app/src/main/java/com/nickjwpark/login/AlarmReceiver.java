package com.nickjwpark.login;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Nick on 2/27/16.
 */
public class AlarmReceiver extends BroadcastReceiver {
//Import: BroadcastReceiver, Intent, Context, PowerManager
// NotificationManager, PendingIntent, Uri, RingtoneManager, NotificationCompat (v4)

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        SharedPreferences sharedPref = context.getSharedPreferences("com.nickjwpark.login", Context.MODE_PRIVATE);
        String defaultValue = "";

        String [] values = new String [18];
        for(int i=0; i<values.length; i++){
            String schedule = sharedPref.getString(""+i, defaultValue);
            values[i] = schedule;
            Log.d("atest", "schedules: "+schedule);
        }
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY) - 6;
        Log.d("atest", ""+hour);
        if(!values[hour].equals("")) {


            long when = System.currentTimeMillis();
            NotificationManager notificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);

            Intent notificationIntent = new Intent(context, ActivitySchedule.class); //Activity to open
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
                    context).setSmallIcon(R.drawable.ic_launcher)
                    .setContentTitle("" + (hour+6) + "시!")
                    .setContentText(values[hour] + " 시간이에요!").setSound(alarmSound)
                    .setAutoCancel(true).setWhen(when)
                    .setContentIntent(pendingIntent)
                    .setVibrate(new long[]{1000});
            notificationManager.notify(1, mNotifyBuilder.build());


            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock screenLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
            screenLock.acquire(5000);

        }
    }

}