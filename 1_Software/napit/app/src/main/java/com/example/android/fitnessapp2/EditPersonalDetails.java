package com.example.android.fitnessapp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class EditPersonalDetails extends AppCompatActivity {


    private Button saveDetails;
    private EditText detailsName, detailsEmail, detailsAge, detailsHeight, detailsWeight, detailsLocation;
    private String name = "";
    private String email = "";
    private String gender="";
    private String age = "";
    private String weight = "";
    private String height = "";
    private String location = "";
    RadioButton male,female;
    String whichChecked;
    private SharedPreferences mpreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_details);

        male=(RadioButton)findViewById(R.id.radio_male);
        female=(RadioButton)findViewById(R.id.female);
        detailsName = (EditText)findViewById(R.id.edit_name);
        detailsEmail = (EditText)findViewById(R.id.edit_email);
        detailsAge = (EditText)findViewById(R.id.edit_age);
        detailsHeight = (EditText) findViewById(R.id.edit_height);
        detailsWeight = (EditText) findViewById(R.id.edit_weight);
        detailsLocation = (EditText) findViewById(R.id.edit_location);
        mpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = mpreferences.edit();


        saveDetails = (Button)findViewById(R.id.saveDetails);
        saveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = detailsName.getText().toString();
                email = detailsEmail.getText().toString();
                gender=whichChecked;
                age = detailsAge.getText().toString();
                height = detailsHeight.getText().toString();
                weight = detailsWeight.getText().toString();
                location = detailsLocation.getText().toString();

                editor.putString("Name",name);
                editor.putString("detailsEmail",email);
                editor.putString("detailsGender",gender);
                editor.putString("detailsAge",age);
                editor.putString("detailsHeight",height);
                editor.putString("detailsWeight",weight);
                editor.putString("detailsLocation",location);
                editor.commit();

                Intent intent = new Intent();
                intent.putExtra("detailsName", name);
                intent.putExtra("detailsEmail", email);
                intent.putExtra("detailsGender",gender);
                intent.putExtra("detailsAge", age);
                intent.putExtra("detailsHeight", height);
                intent.putExtra("detailsWeight", weight);
                intent.putExtra("detailsLocation", location);
                setResult(RESULT_OK, intent);
                //Toast.makeText(EditPersonalDetails.this, "Intent Sent!" + name, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
    public void onRadioButtonClicked(View view) {

        if(male.isChecked())
        {
            whichChecked = "Male";

        }
        else
        {
            whichChecked="Female";
        }


    }

}
