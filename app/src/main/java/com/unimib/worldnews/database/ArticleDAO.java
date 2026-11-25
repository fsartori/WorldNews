package com.unimib.worldnews.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.unimib.worldnews.model.Article;

import java.util.List;
/*
Il design pattern DAO permette di separare la logica applicativa dalla logica di accesso ai dati.
Annotazione	  Funzione
@Dao	      Identifica l'interfaccia di accesso ai dati.
@Query	      Permette di specificare direttamente un'istruzione SQL.
@Insert	      Inserisce una o più entità.
@Update	      Aggiorna un'entità esistente.
@Delete    	  Elimina un'entità esistente.

Room verifica anche la validità delle query SQL in fase di compilazione.
Attraverso l'interfaccia, dichiariamo che cosa vogliamo fare, senza specificare come implementarlo.
Durante la compilazione, l'annotation processor di Room genera automaticamente una classe che implementa l'interfaccia ArticleDAO.
A questo serve la dipendenza inserita precedentemente nel file Gradle: annotationProcessor("androidx.room:room-compiler:${roomVersion}")
 */
@Dao
public interface ArticleDAO {

    @Query("SELECT * FROM Article")
    List<Article> getAll();

    @Query("SELECT * FROM Article WHERE liked = 1")
    List<Article> getLiked();

    @Insert(onConflict = OnConflictStrategy.REPLACE) // in caso di conflitto durante l'inserimento di una notizia, Room usa la strategia di sostituzione SQLite
    void insert(Article... articles); //i ... indicano un numero variabile di parametri (varargs). Il metodo insert potrà essere invocato in vari modi

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Article> articles);

    @Update
    void updateArticle(Article article);

    @Delete
    void delete(Article user);

    @Query("DELETE from Article WHERE liked = 0")
    void deleteCached();

    @Query("DELETE from Article")
    void deleteAll();
}