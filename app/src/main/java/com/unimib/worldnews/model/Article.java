package com.unimib.worldnews.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/* Questa classe è particolarmente interessante, perché svolge una duplice funzione:
- Rappresenta un articolo deserializzato dal file JSON mediante Gson.
- Rappresenta un'entità persistente del database SQLite gestito da Room.
In particolare:
@Entity:Indica che la classe corrisponde a una tabella del database.
@PrimaryKey(autoGenerate = true): Definisce una chiave primaria il cui valore viene generato automaticamente.
@Embedded(prefix = "source_"): Incorpora gli attributi di un altro oggetto nelle colonne della stessa tabella.
Due attributi aggiuntivi rispetto al JSON
- uid: identificatore interno utilizzato per la persistenza nel database.
- liked: informazione relativa alle preferenze dell'utente, inizialmente false.
E' un buon esempio di come il modello dei dati di un'applicazione possa arricchire le informazioni ricevute da un servizio esterno.

Inoltre, Room rappresenta il valore booleano liked in SQLite mediante un intero (0 oppure 1).
 */
@Entity
public class Article {

    @PrimaryKey(autoGenerate = true)
    private long uid;

    @Embedded(prefix = "source_")
    private ArticleSource source;

    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
    private boolean liked;

    public Article() {}

    public ArticleSource getSource() {
        return source;
    }

    public void setSource(ArticleSource source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public boolean getLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}