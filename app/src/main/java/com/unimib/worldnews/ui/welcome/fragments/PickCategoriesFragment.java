package com.unimib.worldnews.ui.welcome.fragments;
import android.content.Intent;
import android.util.ArraySet;
import android.widget.GridView;

import com.unimib.worldnews.adapter.CategoriesAdapter;
import com.unimib.worldnews.ui.home.HomeActivity;
import com.unimib.worldnews.util.Constants;
import com.unimib.worldnews.util.SharedPreferencesUtils;

import java.util.Set;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.unimib.worldnews.R;
import com.unimib.worldnews.model.Category;

import java.util.ArrayList;


public class PickCategoriesFragment extends Fragment {

    public static final String TAG = PickCategoriesFragment.class.getName();

    //public ArrayList<String> pickedCategories = new ArrayList<String>();
    //cambio la dichiaraizone di pickedCategories per contenene oggetti Category
    public ArrayList<Category> pickedCategories = new ArrayList<Category>();

    public ExtendedFloatingActionButton floatingActionButton;

    public PickCategoriesFragment() {}


    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_pick_categories, container, false);

        floatingActionButton = view.findViewById(R.id.floatingActionButton);

        return view;

    }*/

    // nuova versione onCreateView: visualizza le categorie attraverso il CategoriesAdapter;
    // consente di selezionarle; salva i codici delle categorie in SharedPreferences;
    // apre HomeActivity quando si preme il pulsante.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pick_categories, container, false);

        CategoriesAdapter adapter = new CategoriesAdapter(
                getContext(),
                R.layout.card_category,
                Constants.generateCategoriesList(getContext()),
                this
        );

        GridView gridView = view.findViewById(R.id.gridView);
        gridView.setAdapter(adapter);

        floatingActionButton = view.findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Set<String> categoriesCodes = new ArraySet<>();
// estrae i codici delle categorie selezionate da salvare nelle preferenze
                for (int i = 0; i < pickedCategories.size(); i++) {
                    categoriesCodes.add(pickedCategories.get(i).getCode());
                }

                SharedPreferencesUtils sharedPreferencesUtils =
                        new SharedPreferencesUtils(getContext());

                sharedPreferencesUtils.writeStringSetData(
                        Constants.SHARED_PREFERENCES_FILENAME,
                        Constants.SHARED_PREFERENCES_CATEGORIES_OF_INTEREST,
                        categoriesCodes
                );
//cambia activity e passa alla Home
                Intent intent = new Intent(getContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    //metodo che aggiunge la categoria selezionata
    public void addCategory(Category category) {
        pickedCategories.add(category);
    }
    //metodo che rimuove la categoria deselezionata
    public void removeCategory(Category category) {
        pickedCategories.remove(category);
    }

    //attivz il bottone di conferma se la lista delle categorie selezionate non è vuota
    public void tryEnableFloatingActionButton() {
        floatingActionButton.setEnabled(!pickedCategories.isEmpty());
    }
}