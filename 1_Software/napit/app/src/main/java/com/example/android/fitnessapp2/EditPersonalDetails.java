package com.example.android.fitnessapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditPersonalDetails extends AppCompatActivity {
    private Button saveDetails;
    private EditText detailsName, detailsEmail, detailsAge;
    private String name = "";
    private String email = "";
    private String age = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_details);

        detailsName = (EditText)findViewById(R.id.edit_name);
        detailsEmail = (EditText)findViewById(R.id.edit_email);
        detailsAge = (EditText)findViewById(R.id.edit_age);

        saveDetails = (Button)findViewById(R.id.saveDetails);
        saveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = detailsName.getText().toString();
                email = detailsEmail.getText().toString();
                age = detailsAge.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("detailsName", name);
                intent.putExtra("detailsEmail", email);
                intent.putExtra("detailsAge", age);
                setResult(RESULT_OK, intent);
                //Toast.makeText(EditPersonalDetails.this, "Intent Sent!" + name, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
