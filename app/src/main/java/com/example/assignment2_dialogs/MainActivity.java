package com.example.assignment2_dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //declaration
    private EditText editTxt;
    private Button openBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Initialization
        editTxt= findViewById(R.id.editTxt);
        openBtn= findViewById(R.id.openBtn);

        //Here we have attached listener on Open Button to perform click event
        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredText= editTxt.getText().toString().trim();

                if(!enteredText.isEmpty()){
                    showCustomDialog(enteredText);
                }else {
                    Toast.makeText(MainActivity.this,"Please Enter Some text First",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void showCustomDialog(String text) {
        //here we will inflate the custom layout

        LayoutInflater inflater= getLayoutInflater();
        View dialogView= inflater.inflate(R.layout.dialog_box,null);

        //here we will initialize the dialog box components

        final TextView txtView=dialogView.findViewById(R.id.txtView);
        RadioButton radioUpperCase=dialogView.findViewById(R.id.radioUpperCase);
        RadioButton radioLowerCase= dialogView.findViewById(R.id.radioLowerCase);
        RadioButton radioIntermediateCase= dialogView.findViewById(R.id.radioIntermediateCase);
        final CheckBox checkBox= dialogView.findViewById(R.id.checkBox);
        Button finishBtn = dialogView.findViewById(R.id.finishBtn);

        //this to set initial text in textView
        txtView.setText(text);

        //here we will set action for each Radio Button

        radioUpperCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText(text.toUpperCase());
            }
        });

        radioLowerCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtView.setText(text.toLowerCase());
            }
        });

        radioIntermediateCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              txtView.setText(Character.toUpperCase(text.charAt(0))+text.substring(1).toLowerCase());
            }
        });

        //checkbox logic for reversing the text
        //here we will set a listener on the CheckBox

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txtView.setText(new StringBuilder(text).reverse().toString()); //when the checkbox is checked this line reverses the text and sets in the TextView
                }else {
                    txtView.setText(text); // here we will reset to the original text when unchecked
                }
            }
        });


        //build the dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        final AlertDialog dialog=dialogBuilder.create();

        dialog.show();

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Toast.makeText(MainActivity.this,"Back to Home Page", Toast.LENGTH_LONG).show();


                dialog.dismiss();
            }
        });

       // dialog.show();

    }
}