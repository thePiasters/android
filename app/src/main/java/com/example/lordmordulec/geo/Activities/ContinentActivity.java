package com.example.lordmordulec.geo.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.example.lordmordulec.geo.Managers.CountriesManager;
import com.example.lordmordulec.geo.R;

public class ContinentActivity extends Activity  {
    public static String kontynent;
    String[] kontynenty ={"Wybierz kontynent:","Afryka", "Europa", "Ameryka Południowa","Ameryka Północna", "Azja", "Australia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continent);
        registerForContextMenu(findViewById(R.id.menuOpener));



    }
    @Override
    public void onCreateContextMenu(ContextMenu cm, View v, ContextMenu.ContextMenuInfo cmi)
    {
        super.onCreateContextMenu(cm, v, cmi);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_continents, cm);

        cm.setHeaderTitle("Kontynenty: ");



    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.afryka:
                setKontynent(kontynenty[1]);
                callIntent();
                return true;
            case R.id.europa:
                setKontynent(kontynenty[2]);
                callIntent();
                return true;
            case R.id.amerykaW:
                setKontynent(kontynenty[3]);
                callIntent();
                return true;

            case R.id.amerykaN:
                setKontynent(kontynenty[4]);
                callIntent();
                return true;
            case R.id.azja:
                setKontynent(kontynenty[5]);
                callIntent();
                return true;
            case R.id.australia:
                setKontynent(kontynenty[6]);
                callIntent();
                return true;
        }
        return true;
    }


    public void openMenu(View view)
    {
        openContextMenu(view);
    }

    public void callIntent()
    {
        CountriesManager cM = new CountriesManager();
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        if(checkBox.isChecked())
        {

            cM.setIfsort(true);
        }
        else
        {
            cM.setIfsort(false);
        }


        CountriesActivity ca = new CountriesActivity();
        ca.setCorrect(0);
        ca.setCounter(0);
        final Intent intencja2 = new Intent(this,CountriesActivity.class);
        startActivity(intencja2);
    }



    public String getKontynent() {
        return kontynent;
    }

    public void setKontynent(String kontynent) {
        this.kontynent = kontynent;
    }




}
