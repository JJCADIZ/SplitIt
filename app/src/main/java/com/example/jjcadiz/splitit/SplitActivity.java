package com.example.jjcadiz.splitit;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;
import com.dd.CircularProgressButton;


public class SplitActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //EDIT TEXT
        final EditText edtBill = (EditText) findViewById(R.id.et_bill);



        //CIRCULAR BUTTON
        final CircularProgressButton SplitButton = (CircularProgressButton) findViewById(R.id.btnSplit);
        SplitButton.setIndeterminateProgressMode(true);
        SplitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SplitButton.setProgress(100);
                if (edtBill.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(), "EMPTY FIELD",
                            Toast.LENGTH_LONG).show();
                }else{
                    PassBill();
                    Toast.makeText(getApplicationContext(), "Bill Splitted, go Back"
                            , Toast.LENGTH_SHORT).show();
                }


                }
            }
        );



    }

    public void PassBill(){
        final EditText edtBill = (EditText) findViewById(R.id.et_bill);
        SharedPreferences Billing = getApplicationContext().getSharedPreferences("Bill", MODE_PRIVATE);
        SharedPreferences.Editor editor = getSharedPreferences("Bill", MODE_PRIVATE).edit();

        float Bill = Float.parseFloat(edtBill.getText().toString());
        editor.putFloat("Bill",Bill);
        editor.apply();

    }

    /*
    @Override
    public void onBackPressed(){
        final EditText edtBill = (EditText) findViewById(R.id.et_bill);
        if(edtBill.getText().length() == 0){
            SharedPreferences Billing = getApplicationContext().getSharedPreferences("Bill", MODE_PRIVATE);
            SharedPreferences.Editor editor = getSharedPreferences("Bill", MODE_PRIVATE).edit();

            editor.putFloat("Bill", 0);
            editor.apply();
        }else{
            PassBill();
        }
    }
    */





}
