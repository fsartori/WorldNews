
package com.unimib.worldnews.ui.home;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.unimib.worldnews.R;

//import per il collegamento e la navigazione dei fragment di ricerca notizie.
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
//imprt per decidere quale versione del metodo bottomNavigationVie() utilizzare
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
/*
Vogliamo fare in modo che il titolo della Toolbar cambi automaticamente quando selezioniamo un Fragment:
- Toolbar: componente grafico definito nel layout XML.
- ActionBar: componente gestito dall'Activity, che offre funzionalità come la gestione del titolo, dei menu e della navigazione.
Attraverso setSupportActionBar(toolbar) comunichiamo all'Activity che vogliamo utilizzare la nostra Toolbar come ActionBar.
 */
        // sincornizzazione dei fragment secondo quanto stablito dalla Navigation
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //visualizza il nome dell'app nella toolbar
        NavHostFragment navHostFragment = (NavHostFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);

        NavController navController = navHostFragment.getNavController();

        BottomNavigationView bottomNavigationView =
                findViewById(R.id.bottomNavigation);

        NavigationUI.setupWithNavController(
                bottomNavigationView,
                navController
        );
        /*
        Quando l'utente seleziona una voce della BottomNavigationView, il NavController gestisce il cambiamento della destinazione.
        L'ActionBar viene aggiornata automaticamente utilizzando l'attributo android:label della destinazione nel Navigation Graph.
         */
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(
                        R.id.preferenceNewsFragment,
                        R.id.popularNewsFragment,
                        R.id.searchNewsFragment,
                        R.id.favoriteNewsFragment
                ).build(); //collega l'ActionBar al NavController

        NavigationUI.setupActionBarWithNavController(
                this, navController, appBarConfiguration);
    }
}
