package com.unimib.worldnews.ui.welcome.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unimib.worldnews.R;
//import per collegare gli adapter
import android.widget.GridView;
import com.unimib.worldnews.adapter.CountryAdapter;
import com.unimib.worldnews.util.Constants;

public class PickCountryFragment extends Fragment {

    public static final String TAG = PickCountryFragment.class.getName();

    public PickCountryFragment() {
        // Required empty public constructor
    }

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pick_country, container, false);

        return view;
    }*/
    //metodo onCreateView per usare gli adapter
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_pick_country, container, false);

       CountryAdapter adapter = new CountryAdapter(
               getContext(),
               R.layout.card_country,
               Constants.generateCountryList(getContext())
       );

       GridView gridView = view.findViewById(R.id.gridView);
       gridView.setAdapter(adapter);

       return view;
   }


}