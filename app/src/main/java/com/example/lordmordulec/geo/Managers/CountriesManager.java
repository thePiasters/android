package com.example.lordmordulec.geo.Managers;

import android.content.Context;
import android.content.res.Resources;

import com.example.lordmordulec.geo.Activities.ContinentActivity;
import com.example.lordmordulec.geo.R;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Created by lordmordulec on 13.03.18.
 */

public class CountriesManager {

    static boolean ifsort;



    public InputStream getCountries(Context context)
    {
        ContinentActivity cA = new ContinentActivity();
        String kontynent = cA.getKontynent();
        final Resources resources = context.getResources();


        switch (kontynent) {
            case "Afryka":
                return resources.openRawResource(R.raw.afryka);

            case "Azja":
                return resources.openRawResource(R.raw.azja);
            case "Ameryka Północna":
                return resources.openRawResource(R.raw.amerykan);
            case "Ameryka Południowa":
                return resources.openRawResource(R.raw.amerykaw);
            case "Australia":
                return resources.openRawResource(R.raw.australia);
            case "Europa":
                return resources.openRawResource(R.raw.europa);


        }
        return null;

    }
    public Map<String,String> loadCountries( Context context)
    {
        Map<String,String> panstwa = new HashMap<>();


        InputStream inputStream = getCountries(context);

        try	{
            Scanner scan	=	new	Scanner(new InputStreamReader(inputStream));
            while	(scan.hasNextLine())
            {

                StringTokenizer st = new StringTokenizer(scan.nextLine(),"-");
                if(st.hasMoreElements())
                    panstwa.put(st.nextToken(),st.nextToken());
            }
        }	catch	(Exception e)	{
            e.printStackTrace();
        }
        if(ifsort)
        {
            TreeMap<String, String> treeMap = new TreeMap<>(panstwa);
            return treeMap;
        }

        return panstwa;

    }
    public ArrayList<String> answearsToArray(int counter, Context context)
    {
        Random generator = new Random();
        Map<String,String> countriesMap =loadCountries(context);

        ArrayList<String> nazwyPanstw =new ArrayList<String>(countriesMap.keySet());
        ArrayList<String> stolice = new ArrayList<String>(countriesMap.values());
        ArrayList<String> ans = new ArrayList<>();
        ans.add(stolice.get(counter));


        while(ans.size() != 4)
        {
            int val = generator.nextInt(stolice.size());

            if(!ans.contains(countriesMap.get(nazwyPanstw.get(val)))) {
                ans.add(countriesMap.get(nazwyPanstw.get(val)));
            }

        }
        Collections.shuffle(ans);
        return ans;
    }
    public boolean isIfsort() {
        return ifsort;
    }

    public void setIfsort(boolean ifsort) {
        CountriesManager.ifsort = ifsort;
    }
}
