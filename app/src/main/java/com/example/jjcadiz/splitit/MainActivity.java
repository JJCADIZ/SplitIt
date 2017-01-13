package com.example.jjcadiz.splitit;

import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
 float Total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        //FAB ICON
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SplitActivity.class);
                startActivity(intent);

            }
        });

        //onCreateValidate();
        DisplayBill();


        //LAYOUT
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    public void StartSplit (View view){
        Intent split = new Intent(this, SplitActivity.class);
        startActivity(split);
    }

    public void DisplayBill(){

        SharedPreferences All = getApplicationContext().getSharedPreferences("Total", MODE_PRIVATE);
        Total = All.getFloat("Total",0);

        SharedPreferences Billing = getApplicationContext().getSharedPreferences("Bill", MODE_PRIVATE);
        SharedPreferences.Editor editor = getSharedPreferences("Bill", MODE_PRIVATE).edit();
        float Total2 = Billing.getFloat("Bill", 0);
        editor.clear();
        editor.commit();
        float TotalSplit = 0;
        Total = Total + Total2;
        TotalSplit = Total/2;

        SharedPreferences.Editor display = getSharedPreferences("Total", MODE_PRIVATE).edit();

        display.putFloat("Total", Total);
        display.apply();

        final TickerView tickerView = (TickerView) findViewById(R.id.ticker_bill);
        tickerView.setCharacterList(TickerUtils.getDefaultNumberList());
        tickerView.setCharacterList(TickerUtils.getDefaultListForUSCurrency());

        final TickerView tickerMon = (TickerView) findViewById(R.id.tickerMon);
        tickerMon.setCharacterList(TickerUtils.getDefaultNumberList());
        tickerMon.setCharacterList(TickerUtils.getDefaultListForUSCurrency());


        final TickerView tickerCes = (TickerView) findViewById(R.id.tickerCes);
        tickerCes.setCharacterList(TickerUtils.getDefaultNumberList());
        tickerCes.setCharacterList(TickerUtils.getDefaultListForUSCurrency());


        tickerView.setText("$".concat(String.valueOf(Total)));
        tickerCes.setText("$".concat(String.valueOf(TotalSplit)));
        tickerMon.setText("$".concat(String.valueOf(TotalSplit)));
    }

    public void DisplayZero(){
        final TickerView tickerView = (TickerView) findViewById(R.id.ticker_bill);
        tickerView.setCharacterList(TickerUtils.getDefaultNumberList());
        tickerView.setCharacterList(TickerUtils.getDefaultListForUSCurrency());

        tickerView.setText("$0");
    }



    @Override
    public void onResume(){
        super.onResume();
        DisplayBill();

    }

    @Override
    public void onDestroy(){

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
            Intent history =new Intent(this, ExpenseActivity.class);
            startActivity(history);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}


