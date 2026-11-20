
//classe che semplifica la gestione delle SharePrefenreces, cioè la memoria locale usata per
//conservare info dopo la chiusura. Nel nostro progetto servirà, tra le altre cose,
// a memorizzare il Paese e le categorie di notizie selezionati dall'utente.
package com.unimib.worldnews.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class SharedPreferencesUtils {

    private final Context context;

    public SharedPreferencesUtils(Context context) {
        this.context = context;
    }

    // writeStringData(): permette di salvare una stringa
    public void writeStringData(String sharedPreferencesFileName, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(sharedPreferencesFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }
    // writeStringSetData(): permette di salvare un insieme di stringhe
    public void writeStringSetData(String sharedPreferencesFileName, String key, Set<String> value) {
        SharedPreferences sharedPref = context.getSharedPreferences(sharedPreferencesFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(key, value);
        editor.apply();
    }
    // readStringData(): permette di recuperare una stringa
    public String readStringData(String sharedPreferencesFileName, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(sharedPreferencesFileName,
                Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }
    // readStringSetData(): permette di recuperare un insieme di  stringhe
    public Set<String> readStringSetData(String sharedPreferencesFileName, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(sharedPreferencesFileName,
                Context.MODE_PRIVATE);
        return sharedPref.getStringSet(key, null);
    }
}
