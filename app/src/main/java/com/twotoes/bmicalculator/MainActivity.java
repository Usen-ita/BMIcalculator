package com.twotoes.bmicalculator;

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
    //class variables also are called fields
    private TextView resultText;
    private Button calculateButton;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText textAge;
    private EditText textFeet;
    private EditText textInches;
    private EditText textWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViews();

        //button algorithm
        setupButtonClickListener();

    }

    private void findViews(){
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        textAge = findViewById(R.id.edit_text_age);
        textFeet = findViewById(R.id.edit_text_feet);
        textInches = findViewById(R.id.edit_text_inches);
        textWeight = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               double bmiResult =  calculateBMI();

                String ageText = textAge.getText().toString();
                int age = Integer.parseInt(ageText);
                if(age >= 18){
                    displayResult(bmiResult);
                }else {
                    displayGuidance(bmiResult);
                }


            }
        });
    }



    private double calculateBMI() {

    String inchesText = textInches.getText().toString();
    String feetText = textFeet.getText().toString();
    String weightText = textWeight.getText().toString();

    //converting the number 'Strings' into 'int' variables

    int inches = Integer.parseInt(inchesText);
    int feet = Integer.parseInt(feetText);
    int weight = Integer.parseInt(weightText);

    int totalInches = (feet * 12) + inches;

    //Height in meters is the inches multiplied by 0.0254
    double heightInMeters = totalInches * 0.0254;

    //BMI formula = weight in kg divided by height in meters squared
        return weight / (heightInMeters * heightInMeters);

    }

    private void displayResult(double bmi){
        DecimalFormat myDecimalFormatter =  new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);


        String fullResultString;
        if(bmi < 18.5){
            //Display underweight
            fullResultString = bmiTextResult + " - You are underweight";
        }else if(bmi > 25){
            //Display overweight
            fullResultString = bmiTextResult + " - You are overweight";
        }else{
            //Display healthy
            fullResultString = bmiTextResult + " - You are healthy";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter =  new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);
        String fullResultString;
        if (maleButton.isChecked()){
            //Display boy guidance
            fullResultString = bmiTextResult + "- As you are under 18, please consult with your doctor for the healthy range for boys.";
        }else if (femaleButton.isChecked()){
            //Display girl guidance
            fullResultString = bmiTextResult + "- As you are under 18, please consult with your doctor for the healthy range for girls.";
        }else{
            //Display general guidance
            fullResultString = bmiTextResult + "- As you are under 18, please consult with your doctor for the healthy range.";
        }
        resultText.setText(fullResultString);
    }

}