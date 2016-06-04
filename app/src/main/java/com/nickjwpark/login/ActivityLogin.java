package com.nickjwpark.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity {

    TextView txtHello;
    Button btnSchedule;
    Button btnClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_login);

        txtHello = (TextView) findViewById(R.id.txtHello);
        btnSchedule = (Button) findViewById(R.id.btnSchedule);
        btnClear = (Button) findViewById(R.id.btnClear);

        String name = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name");
        }

        txtHello.setText("Hello "+name+"!");

        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                btnSchedule.getAlpha();
//                btnSchedule.setAlpha((float)0.5);
                Toast.makeText(getApplicationContext(), "View Schedule", Toast.LENGTH_SHORT).show();
                launchScheduleActivity();
            }
        });
/*
        int age = 13;
        if(age >= 15){
            Toast.makeText(getApplicationContext(), "I am a teenager", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "I am not a teenager", Toast.LENGTH_SHORT).show();
        }


        Button btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextme = (EditText) findViewById(R.id.editTextMe);
                EditText editTextComputer = (EditText) findViewById(R.id.editTextComputer);
                String me = editTextme.getText().toString();
                String computer = editTextComputer.getText().toString();

                if(me.equals(computer)){
                    Toast.makeText(getApplicationContext(), "Draw!", Toast.LENGTH_SHORT).show();
                } else {
                    if(me.equals("scissors")){
                        if(computer.equals("rock")){
                            Toast.makeText(getApplicationContext(), "Computer won!", Toast.LENGTH_SHORT).show();
                        } else {
                            if(computer.equals("paper")){
                                Toast.makeText(getApplicationContext(), "You won!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Typo!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else{
                        if(me.equals("rock")){
                            if(computer.equals("paper")){
                                Toast.makeText(getApplicationContext(), "Computer won!", Toast.LENGTH_SHORT).show();
                            } else {
                                if(computer.equals("scissors")){
                                    Toast.makeText(getApplicationContext(), "You won!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Typo!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            if(me.equals("paper")){
                                if(computer.equals("scissors")){
                                    Toast.makeText(getApplicationContext(), "Computer won!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if(computer.equals("rock")){
                                        Toast.makeText(getApplicationContext(), "You won!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Typo!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Typo!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Someone won!", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/

    }

    public void launchScheduleActivity(){
        Activity fromActivity = this;
        Class toActivity = ActivitySchedule.class;
        Intent intent = new Intent(fromActivity,toActivity);
        startActivity(intent);

    }


}
