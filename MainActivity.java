package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbMale;
    private RadioButton rbFemale;
    private EditText etAge;
    private EditText etFeet;
    private EditText etInches;
    private EditText etWeight;
    private Button btCalc;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        onClick();
    }

    private void onClick() {

        btCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Button is Clicked!!",Toast.LENGTH_LONG).show();
                String ageText = etAge.getText().toString();
                int age = Integer.parseInt(ageText);
                double bmiResult = calculateBMI();
                if(age >= 18)
                {
                    displayResult(bmiResult);
                }
                else{
                    displayGuidance(bmiResult);
                }
            }
        });
    }

    private void findViews() {
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);
        etAge = findViewById(R.id.et_age);
        etFeet = findViewById(R.id.et_feet);
        etInches = findViewById(R.id.et_inches);
        etWeight = findViewById(R.id.et_weight);
        btCalc = findViewById(R.id.bt_calculate);
        tvResult = findViewById(R.id.tv_result);
    }

    private double calculateBMI() {
        String feetText = etFeet.getText().toString();
        String inchesText = etInches.getText().toString();
        String weightText = etWeight.getText().toString();

        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet*12) + inches;
        double heightInMs2 = (totalInches*0.0254)*(totalInches*0.0254);

        return weight/heightInMs2;

    }

    private void displayResult(double bmi) {
        DecimalFormat dm = new DecimalFormat("0.00");
        String bmiResult = dm.format(bmi);

        String fullResult;

        if(bmi < 18.5){
            fullResult = bmiResult + "!!  Oops!! You are underweight!!";
        }
        else if(bmi > 25){
            fullResult = bmiResult + "!!  Oops!! You are overweight!!";
        }
        else{
            fullResult = bmiResult + "!!  Wow!! You have the perfect weight!!";
        }
        tvResult.setText(fullResult);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDmFormatter = new DecimalFormat("0.00");
        String bmiTextRes = myDmFormatter.format(bmi);

        String fullResStr;

        if(rbMale.isChecked()){
            fullResStr = bmiTextRes + "!!   Since you are under 18, please consult your doctor for a healthy BMI Range for boys!!";
        }
        else if(rbFemale.isChecked()){
            fullResStr = bmiTextRes + "!!   Since you are under 18, please consult your doctor for a healthy BMI Range for girls!!";
        }
        else{
            fullResStr = bmiTextRes + "!!   Since you are under 18, please consult your doctor for a healthy BMI Range!!";
        }
        tvResult.setText(fullResStr);
    }
}
