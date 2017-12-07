package com.example.android.fitnessapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactUs extends AppCompatActivity {

    Button subbutton;
    EditText subfb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        subbutton=(Button)findViewById(R.id.submitbutton);
        subfb = (EditText)findViewById(R.id.submitfb);
        subbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ContactUs.this,"Your Feedback has been submitted. Thank You!",Toast.LENGTH_LONG).show();


            }
        });
    }
}
