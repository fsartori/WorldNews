package com.unimib.worldnews.util;

import android.content.Context;

import com.google.gson.Gson;
import com.unimib.worldnews.model.ArticleAPIResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONParserUtils {

    public Context context;

    public JSONParserUtils(Context context) {
        this.context = context;
    }
/* La classe JSONParserUtils non è un'Activity né un Fragment. Ha quindi bisogno di ricevere un Context per accedere alle risorse dell'applicazione.
E' anche un semplice esempio di Dependency Injection attraverso il costruttore.
Il metodo getAssets() restituisce un AssetManager, attraverso il quale possiamo accedere ai file contenuti nella cartella assets.
A differenza delle risorse presenti in res, questi file non vengono identificati attraverso la classe R, ma attraverso il loro nome.

L'istruzione: return new Gson().fromJson(bufferedReader, ArticleAPIResponse.class); è l'istruzione cruciale.
Gson riceve due informazioni:
- Il contenuto JSON da interpretare.
- La classe Java nella quale trasformare tale contenuto.
Gson costruisce automaticamente anche gli oggetti annidati di tipo Article e ArticleSource.
Potremo utilizzare la classe con chioamte del tipo:

JSONParserUtils parser = new JSONParserUtils(this);

ArticleAPIResponse response =
        parser.parseJSONFileWithGSon("sample_api_response.json");

List<Article> articles = response.getArticles();

Possibili miglioramenti al codice:
- Dichiarare context come private final.
- Utilizzare il costrutto try-with-resources per chiudere automaticamente il flusso di lettura.
 */
public ArticleAPIResponse parseJSONFileWithGSon(
        String filename) throws IOException {
    InputStream inputStream =
                context.getAssets().open(filename);

        BufferedReader bufferedReader =
                new BufferedReader(
                        new InputStreamReader(inputStream));

        return new Gson().fromJson(
                bufferedReader, ArticleAPIResponse.class);
}
    //versione con try-with-resources
   /* public ArticleAPIResponse parseJSONFileWithGSon(
            String filename) throws IOException {
       try(InputStream inputStream =
                context.getAssets().open(filename);

        BufferedReader bufferedReader =
                new BufferedReader(
                        new InputStreamReader(inputStream))) {

           return new Gson().fromJson(
                   bufferedReader, ArticleAPIResponse.class);
       }
    }*/
}