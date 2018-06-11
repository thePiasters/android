package com.example.lordmordulec.geo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.lordmordulec.geo.R;

import static android.graphics.Color.rgb;

public class StartAtctivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);




        Button btn1 = (Button) findViewById(R.id.finish);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();
                moveTaskToBack(true);

            }
        });


        final Intent callContinentActivity = new Intent(this,ContinentActivity.class);
        Button przycisk1= (Button) findViewById(R.id.start);
        przycisk1.setOnClickListener(new View.OnClickListener()
        {


            public void onClick(View view) {
                startActivity(callContinentActivity);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.start_menu, menu);

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        View view = findViewById(R.id.view);

        switch (item.getItemId()) {
            case R.id.black:

                view.setBackgroundColor(rgb(0, 0, 0));
                return true;
            case R.id.white:

                view.setBackgroundColor(rgb(255, 255, 255));
                return true;

            case R.id.pink:

                view.setBackgroundColor(rgb(188, 75, 122));
                return true;
            case R.id.green:

                view.setBackgroundColor(rgb(59, 214, 88));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
