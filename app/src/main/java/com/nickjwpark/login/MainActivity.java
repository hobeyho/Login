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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtBoxId;
    EditText txtBoxPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtBoxId = (EditText) findViewById(R.id.txtBoxId);
        txtBoxPassword = (EditText) findViewById(R.id.txtBoxPassword);

        //가져오는 부분
        final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String defaultValue = "";
        String name = sharedPref.getString("name", defaultValue);
        String password = sharedPref.getString("password", defaultValue);


        txtBoxId.setText(name);
        txtBoxPassword.setText(password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                String name = txtBoxId.getText().toString();
                String password = txtBoxPassword.getText().toString();

                if(name.equals("nick") && password.equals("park")){

                    //저장하는 부분
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("name", name);
                    editor.putString("password", password);
                    editor.commit();


                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    launchLoginActivity(name);
                } else if (!name.equals("nick")){
                    Toast.makeText(getApplicationContext(), "Wrong ID!", Toast.LENGTH_SHORT).show();
                } else if (!password.equals("park")){
                    Toast.makeText(getApplicationContext(), "Wrong Password!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }

//                Toast.makeText(getApplicationContext(), "ID: " + name, Toast.LENGTH_SHORT).show();
//                launchLoginActivity();
            }
        });
    }

    public void launchLoginActivity(String name){
        Activity fromActivity = this;
        Class toActivity = ActivityLogin.class;
        Intent intent = new Intent(fromActivity,toActivity);
        intent.putExtra("name", name);
        startActivity(intent);

    }

}
