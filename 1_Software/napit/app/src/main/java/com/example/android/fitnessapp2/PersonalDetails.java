package com.example.android.fitnessapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalDetails extends AppCompatActivity {

    static final int REQ_CODE = 123;

    Button btnDetails;
    TextView textName, textEmail, textAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        textName = (TextView)findViewById(R.id.name_holder);
        textEmail = (TextView)findViewById(R.id.email_holder);
        textAge = (TextView)findViewById(R.id.age_holder);

        btnDetails = (Button) findViewById(R.id.btnDetails);
        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PersonalDetails.this, EditPersonalDetails.class);
                startActivityForResult(i, REQ_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == REQ_CODE){

            String details_name = intent.getStringExtra("detailsName");
            String details_email = intent.getStringExtra("detailsEmail");
            String details_age = intent.getStringExtra("detailsAge");

            //Toast.makeText(this, "Intent Received " + details_name, Toast.LENGTH_SHORT).show();

            textName.setText(details_name);
            textEmail.setText(details_email);
            textAge.setText(details_age);
        }
    }
}
