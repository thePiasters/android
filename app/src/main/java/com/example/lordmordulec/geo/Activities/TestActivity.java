package com.example.lordmordulec.geo.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.lordmordulec.geo.Fragments.ResultFragment;
import com.example.lordmordulec.geo.Fragments.ResultListFragment;
import com.example.lordmordulec.geo.R;

public class TestActivity extends AppCompatActivity implements  ResultFragment.OnFragmentInteractionListener, ResultListFragment.OnFragmentInteractionListener, PopupMenu.OnMenuItemClickListener {

    private android.support.v7.view.ActionMode myAm;
    CountriesActivity countriesActivity;
    android.support.v4.app.FragmentTransaction transaction;
    ResultFragment resultFragment;

    ResultListFragment resultListFragment;
    private static final String TAG_Result = "ResultFragment";
    private static final String TAG_ResultList = "ResultListFragment";
    String kontynent;
    static SharedPreferences sharedPreferences;
    static String[] results;

    static int previous;
    static boolean viewResult;
    BottomNavigationView bottomNavigationView;
    View view;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottonNavigationSetUp();


        previous = 0;
        generalSetUp(savedInstanceState);
        sharedPreferences = getSharedPreferences("results", MODE_PRIVATE);

        view = findViewById(R.id.test);
        view.setVisibility(View.VISIBLE);

        view.setOnLongClickListener(new View.OnLongClickListener()
        {

           @Override
            public boolean onLongClick(View view) {
                if(myAm!= null)
                {

                    return false;
                }

                myAm = startSupportActionMode(mActionModeCallback);
                view.setSelected(true);
                view.setVisibility(View.GONE);
                return true;

            }
        });

    }


    private android.support.v7.view.ActionMode.Callback mActionModeCallback = new android.support.v7.view.ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(android.support.v7.view.ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_cam, menu);
            mode.setTitle("");
            return true;

        }

        @Override
        public boolean onPrepareActionMode(android.support.v7.view.ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(android.support.v7.view.ActionMode mode, MenuItem item) {
            FragmentTransaction ft  = getSupportFragmentManager().beginTransaction();
            switch(item.getItemId())
           {

               case R.id.item1:
                   ft.attach(resultFragment);
                   ft.detach(resultListFragment);





                   ft.commit();
                   break;
               case R.id.item2:

                   organizeResult();
                   setUpArray();

                   ft.detach(resultFragment);
                   ft.attach(resultListFragment);




                   ft.commit();

                   break;
           }
            return false;
        }

        @Override
        public void onDestroyActionMode(android.support.v7.view.ActionMode mode) {
            myAm = null;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //ft.detach(myResultFragment);
            ft.detach(resultListFragment);
            ft.detach(resultFragment);
            ft.commit();

            view.setVisibility(View.VISIBLE);
        }


    };



    public void organizeResult()
    {

        SharedPreferences.Editor spe =sharedPreferences.edit();

        if(countriesActivity.getCounter()!=0) {
            int res = (countriesActivity.getCorrect() * 100) / countriesActivity.getCounter();
            System.out.println("OBRCNY WYNIK DLA "+kontynent+"  "+res);



            if (checkIfHigher(res)) {
                if (countriesActivity.getCounter() != 0) {
                }
                previous = sharedPreferences.getInt(kontynent,0);
                spe.putInt(kontynent, res);
                spe.commit();
            }

        }

    }
    public void bottonNavigationSetUp()
    {
        final Intent intent = new Intent(this, ContinentActivity.class);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener(){

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                        switch(item.getItemId())
                        {
                            case R.id.exit:


                                finish();
                                moveTaskToBack(true);
                                break;

                            case R.id.onContinue:

                                startActivity(intent);
                                break;

                        }

                        return false;
                    }
                });
    }




    public void showPopup(View view)
    {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_checkable);
        popupMenu.show();
    }


    public void setUpArray()
    {


        results = new String[]{"Afryka "+Integer.toString(sharedPreferences.getInt("Afryka", 0)),"Ameryka Północna: "+Integer.toString(sharedPreferences.getInt("Ameryka Północna", 0)),
                "Ameryka Południowa: "+Integer.toString(sharedPreferences.getInt("Ameryka Południowa", 0)),"Europa: "+Integer.toString(sharedPreferences.getInt("Europa", 0)),
                "Azja: "+Integer.toString(sharedPreferences.getInt("Azja", 0)),"Australia: "+Integer.toString(sharedPreferences.getInt("Australia", 0))};

        for(int i=0; i<results.length; i++)
        {
            if( results[i].equals("0"))
            {
                results[i]= "Brak wyniku";
            }
            else
            {
                results[i] = results[i]+ " %";
            }
        }

    }

    public boolean checkIfHigher(int res) {

        if (sharedPreferences.getInt(kontynent, 0) < res) {
            return true;
        }
        return false;
    }
    public void generalSetUp(Bundle savedInstanceState)
    {
        kontynent = (new ContinentActivity()).getKontynent();
        countriesActivity = new CountriesActivity();




        resultFragment = new ResultFragment();
        resultListFragment = new ResultListFragment();




        if (savedInstanceState == null) {
            try {

                transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.container, resultFragment, TAG_Result);
                transaction.detach(resultFragment);


                transaction.add(R.id.container, resultListFragment, TAG_ResultList);

                transaction.detach(resultListFragment);

                transaction.commit();
            }
            catch (IllegalStateException e)
            {

            }

        } else {


            resultFragment =(ResultFragment) getSupportFragmentManager().findFragmentByTag(TAG_Result);
            resultListFragment = (ResultListFragment) getSupportFragmentManager().findFragmentByTag(TAG_ResultList);

        }


    }


    @Override
    public void onFragmentInteraction(boolean ifChecked) {


    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        setUpArray();
        menuItem.setChecked(!menuItem.isChecked());
        TextView tv = findViewById(R.id.wynik);

        switch (menuItem.getItemId())
        {
            case R.id.pozycja1:
                if(menuItem.isChecked())
                {
                    //Afryka

                    tv.setText(results[0]);

                }
                return true;
            case R.id.pozycja2:
                if(menuItem.isChecked())
                {
                    //Ameryka Północna
                    tv.setText(results[1]);

                }
                return true;
            case R.id.pozycja3:
                if(menuItem.isChecked())
                {
                    //Ameryka Południowa
                    tv.setText(results[2]);

                }
                return true;
            case R.id.pozycja4:
                if(menuItem.isChecked())
                {
                    //Europa
                    tv.setText(results[3]);

                }
                return true;
            case R.id.pozycja5:
                if(menuItem.isChecked())
                {
                    //Azja
                    tv.setText(results[4]);

                }
                return true;
            case R.id.pozycja6:
                if(menuItem.isChecked())
                {
                    //Australia
                    tv.setText(results[5]);

                }
                return true;
        }
        return false;
    }



    @Override
    public void onFragmentInteraction(Boolean check) {


    }



}

