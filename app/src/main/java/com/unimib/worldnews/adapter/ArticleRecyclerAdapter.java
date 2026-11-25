
package com.unimib.worldnews.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.unimib.worldnews.R;
import com.unimib.worldnews.database.ArticleRoomDatabase;
import com.unimib.worldnews.model.Article;

import java.util.List;
/*
Il principio è simile a quello degli Adapter delle GridView che abbiamo utilizzato nell'esercitazione precedente,
ma con una gestione più strutturata del riutilizzo delle View.

Metodo	                Responsabilità
onCreateViewHolder()	Crea una nuova View a partire dal layout XML.
onBindViewHolder()	    Associa a una View i dati di uno specifico articolo.
getItemCount()	        Restituisce il numero complessivo degli articoli.

Una View viene creata quando necessario, ma può essere associata a diversi articoli nel corso della sua esistenza.
 */
public class ArticleRecyclerAdapter extends
        RecyclerView.Adapter<ArticleRecyclerAdapter.ViewHolder> {

    private int layout;
    private List<Article> articleList;
    private boolean heartVisible;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewTitle;
        private final TextView textViewAuthor;
        private final CheckBox favoriteCheckbox;

        public ViewHolder(View view) {
            super(view);

            textViewTitle = view.findViewById(R.id.textViewTitle);
            textViewAuthor = view.findViewById(R.id.textViewAuthor);
            favoriteCheckbox = view.findViewById(R.id.favoriteButton);
        }

        public TextView getTextViewTitle() {
            return textViewTitle;
        }

        public TextView getTextViewAuthor() {
            return textViewAuthor;
        }

        public CheckBox getFavoriteCheckbox() {
            return favoriteCheckbox;
        }
    }

    public ArticleRecyclerAdapter(int layout,
                                  List<Article> articleList,
                                  boolean heartVisible) {
        this.layout = layout;
        this.articleList = articleList;
        this.heartVisible = heartVisible;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                         int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder,
                                 final int position) {

        viewHolder.getTextViewTitle()
                .setText(articleList.get(position).getTitle());

        viewHolder.getTextViewAuthor()
                .setText(articleList.get(position).getAuthor());

        viewHolder.getFavoriteCheckbox()
                .setOnCheckedChangeListener(null);

        viewHolder.getFavoriteCheckbox()
                .setChecked(articleList.get(position).getLiked());

        viewHolder.getFavoriteCheckbox()
                .setOnCheckedChangeListener(
                        new CompoundButton.OnCheckedChangeListener() {

                            @Override
                            public void onCheckedChanged(
                                    CompoundButton compoundButton, boolean b) {

                                int currentPosition =
                                        viewHolder.getAdapterPosition(); //dalla versione 1.2.0 RecyclerView, metodo sostituito da getBindingAdapterPosition()

                                if (currentPosition == RecyclerView.NO_POSITION) {
                                    return;
                                }

                                Article currentArticle =
                                        articleList.get(currentPosition);

                                currentArticle.setLiked(b);

                                ArticleRoomDatabase.getDatabase(
                                                compoundButton.getContext())
                                        .newsDao().updateArticle(currentArticle);
                            }
                        });

        viewHolder.getFavoriteCheckbox().setVisibility(
                heartVisible ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
