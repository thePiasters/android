package com.example.lordmordulec.geo.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lordmordulec.geo.R;

import java.util.ArrayList;
import java.util.Map;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;


public class CountriesFragment extends Fragment {

    AppCompatActivity A1;
    OnWyborOdpowiedziListener sluchaczF1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

//        Toast.makeText(countriesActivity.getContext(), "Aby zakończyć rozgrywkę przytrzymaj dłużej obrazek" ,Toast.LENGTH_LONG).show();
        return inflater.inflate(R.layout.fragment_countries, container, false);
    }


    @Override
    public void onActivityCreated(Bundle saveInstanceState)
    {
        super.onActivityCreated(saveInstanceState);



    }

    @Override
    public void onAttach(Activity context) {

        super.onAttach(context);
        try {
            A1 = (android.support.v7.app.AppCompatActivity) context;
            sluchaczF1 = (OnWyborOdpowiedziListener) context;
        }
        catch(ClassCastException e)
        {
            throw new ClassCastException(A1.toString()+ "musi implementować OnWyborOdpowiedziListener");
        }

    }



    public interface OnWyborOdpowiedziListener {
        // TODO: Update argument type and name
        void onWyborOpcji();
    }


}
