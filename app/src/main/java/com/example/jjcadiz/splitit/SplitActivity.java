package com.example.jjcadiz.splitit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SplitActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

float Total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TICKER
        final TickerView tickerMon = (TickerView) findViewById(R.id.tickerMon);
        tickerMon.setCharacterList(TickerUtils.getDefaultNumberList());
        tickerMon.setCharacterList(TickerUtils.getDefaultListForUSCurrency());
        tickerMon.setText("$0");

        final TickerView tickerCes = (TickerView) findViewById(R.id.tickerCes);
        tickerCes.setCharacterList(TickerUtils.getDefaultNumberList());
        tickerCes.setCharacterList(TickerUtils.getDefaultListForUSCurrency());
        tickerCes.setText("$0");

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
                    Compute();
                }


                }
            }
        );



    }

    public void Compute(){

        //SHARED PREFERENCE
        SharedPreferences Billing = getApplicationContext().getSharedPreferences("Bill", MODE_PRIVATE);

        //CES TICKER
        final TickerView tickerCes = (TickerView) findViewById(R.id.tickerCes);
        tickerCes.setCharacterList(TickerUtils.getDefaultNumberList());
        tickerCes.setCharacterList(TickerUtils.getDefaultListForUSCurrency());

        //MON TICKER
        final TickerView tickerMon = (TickerView) findViewById(R.id.tickerMon);
        tickerMon.setCharacterList(TickerUtils.getDefaultNumberList());
        tickerMon.setCharacterList(TickerUtils.getDefaultListForUSCurrency());

        EditText edtBill = (EditText) findViewById(R.id.et_bill);
        float Bill = Float.parseFloat(edtBill.getText().toString());
        float SplitBill;

        SplitBill = (Bill/2);

        tickerCes.setText("$".concat(String.valueOf(SplitBill)));
        tickerMon.setText("$".concat(String.valueOf(SplitBill)));

        //Set Shared Preferences
        SharedPreferences.Editor editor = getSharedPreferences("Bill", MODE_PRIVATE).edit();
        editor.putFloat("Bill",Bill);
        editor.apply();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
