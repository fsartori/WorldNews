
package com.unimib.worldnews.ui.home.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unimib.worldnews.R;
import com.unimib.worldnews.adapter.ArticleRecyclerAdapter;
import com.unimib.worldnews.database.ArticleRoomDatabase;
import com.unimib.worldnews.model.Article;
import com.unimib.worldnews.model.ArticleAPIResponse;
import com.unimib.worldnews.util.Constants;
import com.unimib.worldnews.util.JSONParserUtils;

import java.io.IOException;
import java.util.List;

/*
Il Fragment dovrà leggere il file JSON, memorizzare gli articoli nel database Room, recuperarli e visualizzarli attraverso la RecyclerView.
 */
public class PreferenceNewsFragment extends Fragment {

    public static final String TAG =
            PreferenceNewsFragment.class.getName();

    public PreferenceNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_preference_news,
                container, false);

        RecyclerView recyclerView =
                view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(view.getContext())); //Definisce la disposizione lineare verticale degli articoli.

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(
                        recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL); //Permette di inserire un separatore grafico tra gli elementi della RecyclerView senza modificare il layout delle singole card.

        recyclerView.addItemDecoration(dividerItemDecoration);

        JSONParserUtils jsonParserUtils =
                new JSONParserUtils(getContext());

        try {
            ArticleRoomDatabase database =
                    ArticleRoomDatabase.getDatabase(getContext()); //introduciamo la variabile database per evitare di ripetere la chiamata a getDatabase().

            // Esercitazione 7: caricamento del JSON soltanto
            // se il database è vuoto, per evitare duplicazioni
            // e preservare gli articoli preferiti.
            if (database.newsDao().getAll().isEmpty()) {  //introduciamo il controllo isEmpty() per leggere il JSON e inserire gli articoli soltanto quando il database è vuoto.

                ArticleAPIResponse response =
                        jsonParserUtils.parseJSONFileWithGSon(
                                Constants.SAMPLE_JSON_FILENAME);

                database.newsDao().insertAll(response.getArticles());
            }

            List<Article> articleList =
                    database.newsDao().getAll();
/*
Istanziazione dell'adapter: I tre parametri corrispondono esattamente a quelli del costruttore che abbiamo implementato
durante l'implementazione di ArticleRecyclerAdapter.java
Parametro	            Significato
R.layout.card_article	Layout della singola notizia.
articleList	            Lista degli articoli recuperati dal database.
true	                Il cuore dei preferiti deve essere visibile.
 */
            ArticleRecyclerAdapter adapter =
                    new ArticleRecyclerAdapter(
                            R.layout.card_article,
                            articleList, true);

            recyclerView.setAdapter(adapter);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return view;
    }
}
