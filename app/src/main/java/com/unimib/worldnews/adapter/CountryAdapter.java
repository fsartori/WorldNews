
package com.unimib.worldnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.google.android.material.card.MaterialCardView;
import com.unimib.worldnews.R;
import com.unimib.worldnews.model.Country;
import com.unimib.worldnews.util.Constants;
import com.unimib.worldnews.util.SharedPreferencesUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;

//CountryAdapter collega la lista di oggetti Country alle schede visualizzate nell'interfaccia:
//mostra il nome e l'immagine di ogni Paese;
//quando l'utente seleziona una scheda, salva il codice del Paese nelle SharedPreferences;
//passa alla schermata di selezione delle categorie.
public class CountryAdapter extends ArrayAdapter<Country> {

    private int layout;
    private ArrayList<Country> countriesList;

    public CountryAdapter(@NonNull Context context, @NonNull int layout, @NonNull ArrayList<Country> countriesList) {
        super(context, layout, countriesList); //layout fa riferimento alla card_country.xml
        this.layout = layout;
        this.countriesList = countriesList;
    }

    //in getView avviene tutto: convertView contiene la view, per ogni country crea la card e fa
    // ogni operazione tradizionale (titolo, immagine...
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(layout, parent, false);

        TextView title = convertView.findViewById(R.id.textView); //ref a testo
        ImageView imageView = convertView.findViewById(R.id.imageView); //ref a immagine

        title.setText(countriesList.get(position).getName());
        imageView.setImageDrawable(countriesList.get(position).getImage()); //position dà la posizione della card nell'ArrayList

        MaterialCardView cardView = (MaterialCardView) convertView;
        //codice per getire la navigazione in caso di click su una country, in accordo al nav_graph
        //pick_country -> pick_categories
        /*cardView.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_pickCountryFragment_to_pickCategoriesFragment);
        });*/
        //versione con le SharedPreferences: prendde le costanti definite e la posizione nell'ArrayList
        cardView.setOnClickListener(view -> {
            SharedPreferencesUtils sharedPreferencesUtils = new SharedPreferencesUtils(getContext());

            sharedPreferencesUtils.writeStringData(Constants.SHARED_PREFERENCES_FILENAME,
                    Constants.SHARED_PREFERENCES_COUNTRY_OF_INTEREST,
                    countriesList.get(position).getCode());

            Navigation.findNavController(view).navigate(R.id.action_pickCountryFragment_to_pickCategoriesFragment);
        });



        return convertView;
    }
}

