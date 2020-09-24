package com.example.mychild_support4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

//For clickable Link in TextView
import android.text.*;  //SpannableString
import android.text.style.*;    //ClickableSpan
import android.text.method.LinkMovementMethod;

public class MainActivity extends AppCompatActivity {

    private Button button_Start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add Clickable textview to go to RPB Website
        TextView textRPB_LAW = (TextView)findViewById(R.id.textView2);    //Finds textview ID link
        textRPB_LAW.setMovementMethod(LinkMovementMethod.getInstance());

        //Add and Initiate Button to go to Calculating Page
        button_Start = (Button) findViewById(R.id.but_Start);
        button_Start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                open2Activity();
            }
        });
    }

    public void open2Activity(){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

}
