package com.example.lordmordulec.geo.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.PopupMenu;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lordmordulec.geo.Activities.ContinentActivity;
import com.example.lordmordulec.geo.Activities.CountriesActivity;
import com.example.lordmordulec.geo.Activities.TestActivity;
import com.example.lordmordulec.geo.R;


public class ResultListFragment extends Fragment  {
    ContinentActivity continentActivity;

    TextView textView;
    private OnFragmentInteractionListener mListener;
    TestActivity testActivity;
    TextView []textViews;
    int fragment_result_list;

    public ResultListFragment() {

        // Required empty public constructor
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            Activity a = getActivity();
            if(a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        testActivity = new TestActivity();
        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_result_list, container, false);




        getTextViewForResult(view);

        textViews = new TextView[]{view.findViewById(R.id.Afryka), view.findViewById(R.id.Europa),
                view.findViewById(R.id.AmerykaW), view.findViewById(R.id.AmerykaN),view.findViewById(R.id.Azja),
                view.findViewById(R.id.Australia)};



        return view;
    }






    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    public void getTextViewForResult(View view)
    {
        continentActivity = new ContinentActivity();
        String checkKontynent = continentActivity.getKontynent();
        switch(checkKontynent)
        {
            case("Afryka"):

                textView = (TextView) view.findViewById(R.id.Afryka);
                break;

            case("Ameryka Północna"):
                textView = (TextView) view.findViewById(R.id.AmerykaN);
                break;
            case("Ameryka Południowa"):
                textView = (TextView) view.findViewById(R.id.AmerykaW);
                break;

            case("Australia"):
                textView = (TextView) view.findViewById(R.id.Australia);
                break;

            case("Azja"):
                textView = (TextView) view.findViewById(R.id.Azja);
                break;

            case("Europa"):
                textView = (TextView) view.findViewById(R.id.Europa);
                break;

        }

    }
    @Override
    public void onActivityCreated(Bundle saveInstanceState)
    {
        super.onActivityCreated(saveInstanceState);


    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(boolean ifChecked);
    }
}
