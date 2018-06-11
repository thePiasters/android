package com.example.lordmordulec.geo.Activities;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lordmordulec.geo.Managers.CountriesManager;
import com.example.lordmordulec.geo.Fragments.CountriesFragment;
import com.example.lordmordulec.geo.R;

import java.util.ArrayList;
import java.util.Map;


import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

public class CountriesActivity extends AppCompatActivity implements CountriesFragment.OnWyborOdpowiedziListener {

    static int counter;
    static int correct;
    CountriesManager cM;
    static Context context;
    ProgressBar progressBar;

    public  Context getContext() {
        return context;
    }

    public  void setContext(Context context) {
        CountriesActivity.context = context;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        context = this;
        cM = new CountriesManager();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        progressBar.setMax(cM.loadCountries(this).size());


        setAnswears();
        Toast.makeText(getApplicationContext(), "Aby zakończyć rozgrywkę przytrzymaj dłużej obrazek" ,Toast.LENGTH_LONG).show();

        View textBackground = (View) findViewById(R.id.text);
        final Intent toFinal = new Intent(this, TestActivity.class);
        textBackground.setOnLongClickListener(new View.OnLongClickListener()
                                              {

                                                  @Override
                                                  public boolean onLongClick(View view) {
                                                      startActivity(toFinal);
                                                      return false;
                                                  }
                                              }
        );
    }


    public void setAnswears() {
        //ans to array

        Map<String, String> panstwa = cM.loadCountries(this);


        ArrayList<String> nazwyPanstw = new ArrayList<String>(panstwa.keySet());


        ArrayList<String> ans = cM.answearsToArray(counter, this);

        setAnsButtons(nazwyPanstw.get(counter),ans);
    }

    public void setAnsButtons(String str, ArrayList<String> ans)
    {
        TextView text = (TextView) findViewById(R.id.text);

        text.setText(str);

        Button przycisk1 = (Button) findViewById(R.id.odp1);
        przycisk1.setText(ans.get(0));
        przycisk1.setBackgroundResource(android.R.drawable.btn_default);
        przycisk1.setEnabled(true);

        Button przycisk2 = (Button) findViewById(R.id.odp2);
        przycisk2.setText(ans.get(1));
        przycisk2.setBackgroundResource(android.R.drawable.btn_default);
        przycisk2.setEnabled(true);

        Button przycisk3 = (Button) findViewById(R.id.odp3);
        przycisk3.setText(ans.get(2));
        przycisk3.setBackgroundResource(android.R.drawable.btn_default);
        przycisk3.setEnabled(true);

        Button przycisk4 = (Button) findViewById(R.id.odp4);
        przycisk4.setText(ans.get(3));
        przycisk4.setBackgroundResource(android.R.drawable.btn_default);
        przycisk4.setEnabled(true);
    }




    public void loopCountries()
    {

        final int size =cM.loadCountries(this).size()-1;
        final Intent toFinal = new Intent(this, TestActivity.class);
        Runnable r = new Runnable() {
            @Override
            public void run(){

                if(counter<size) {
                    counter++;
                    progressBar.setProgress(counter);
                    setAnswears();
                }
                else
                {
                    counter++;
                    startActivity(toFinal);
                }

            }
        };

        Handler h = new Handler();
        h.postDelayed(r, 750);

    }
    public String getTheCorrectAnswear()
    {

        ArrayList<String> stolice = new ArrayList<String>(cM.loadCountries(this).values());
        Button przycisk1= (Button) findViewById(R.id.odp1);
        Button przycisk2= (Button) findViewById(R.id.odp2);
        Button przycisk3= (Button) findViewById(R.id.odp3);
        Button przycisk4= (Button) findViewById(R.id.odp4);

        if(stolice.get(counter).equals(przycisk1.getText()))
        {
            przycisk1.setBackgroundColor(GREEN);
        }

        if(stolice.get(counter).equals(przycisk2.getText()))
        {
            przycisk2.setBackgroundColor(GREEN);
        }
        else
        if(stolice.get(counter).equals(przycisk3.getText()))
        {
            przycisk3.setBackgroundColor(GREEN);
        }
        if(stolice.get(counter).equals(przycisk4.getText()))
        {
            przycisk4.setBackgroundColor(GREEN);
        }
        przycisk1.setEnabled(false);
        przycisk2.setEnabled(false);
        przycisk3.setEnabled(false);
        przycisk4.setEnabled(false);

        return stolice.get(counter);

    }
    public void validateUsersAnswear(Button button, String correctAnswear)
    {
        if(correctAnswear.equals(button.getText()))
        {
            button.setBackgroundColor(GREEN);
            correct++;
        }
        else
        {
            button.setBackgroundColor(RED);

        }

    }


    public void odp1(View view)
    {

        Button przycisk1= (Button) findViewById(R.id.odp1);
        validateUsersAnswear(przycisk1,getTheCorrectAnswear());
        loopCountries();

    }
    public void odp2(View view)
    {
        Button przycisk2= (Button) findViewById(R.id.odp2);
        validateUsersAnswear(przycisk2,getTheCorrectAnswear());
        loopCountries();
    }
    public void odp3(View view)
    {
        Button przycisk3= (Button) findViewById(R.id.odp3);
        validateUsersAnswear(przycisk3,getTheCorrectAnswear());
        loopCountries();
    }
    public void odp4(View view)
    {
        Button przycisk4= (Button) findViewById(R.id.odp4);
        validateUsersAnswear(przycisk4,getTheCorrectAnswear());
        loopCountries();

    }
    public  int getCounter() {
        return counter;
    }

    public int getCorrect() {
        return correct;
    }

    public  void setCorrect(int correct) {
        CountriesActivity.correct = correct;
    }

    public  void setCounter(int counter) {
        CountriesActivity.counter = counter;
    }







    @Override
    public void onWyborOpcji() {
        progressBar.setProgress(counter);

    }
}
