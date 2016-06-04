package com.nickjwpark.login;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivitySchedule extends ListActivity {

    String [] values = new String [18];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_schedule);
        SharedPreferences sharedPref = this.getSharedPreferences("com.nickjwpark.login", Context.MODE_PRIVATE);
        String defaultValue = "";

//        String [] values = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        for(int i=0; i<values.length; i++){
            String schedule = sharedPref.getString(""+i, defaultValue);
            values[i] = ""+ (i+6) + ":00 - " + schedule;
        }
/*        values[0] = "6:00";
        values[1] = "7:00";
        values[2] = "8:00";
        values[3] = "9:00";
        values[4] = "10:00";
        values[5] = "11:00";
        values[6] = "12:00";
        values[7] = "13:00";
        values[8] = "14:00";
        values[9] = "15:00";
        values[10] = "16:00";
        values[11] = "17:00";
        values[12] = "18:00";
        values[13] = "19:00";
        values[14] = "20:00";
        values[15] = "21:00";
        values[16] = "22:00";
        values[17] = "23:00";*/

//        String [] values = {"0", "1", "2", "3"};
//        ArrayList<Integer> arr = new ArrayList(1);
//        arr.add(5);
//        arr.add(3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_SHORT).show();

        final SharedPreferences sharedPref = this.getSharedPreferences("com.nickjwpark.login", Context.MODE_PRIVATE);

        final int pos = position;
        final String[] list = new String[] {"잠", "숙제", "학교", "휴식", "학원", "밥"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a schedule")
                .setItems(list, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(""+pos, list[which]);
                        editor.commit();

                        //Import: Intent, PendingIntent, AlarmManager
                        Calendar calendar = Calendar.getInstance(); //Calendar
                        int next_hour = calendar.get(Calendar.HOUR_OF_DAY)+1;
                        calendar.set(Calendar.HOUR_OF_DAY, next_hour);
                        calendar.set(Calendar.MINUTE, 1);
                        calendar.set(Calendar.SECOND, 0);
                        Intent intent1 = new Intent(ActivitySchedule.this, AlarmReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(ActivitySchedule.this, 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                        AlarmManager am = (AlarmManager) ActivitySchedule.this.getSystemService(ActivitySchedule.this.ALARM_SERVICE);
                        am.cancel(pendingIntent); // cancel any existing alarms
                        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 60, pendingIntent);

                        finish();
                        startActivity(getIntent());
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
