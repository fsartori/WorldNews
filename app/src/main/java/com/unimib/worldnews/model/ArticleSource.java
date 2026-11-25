package com.unimib.worldnews.model;
/* Questa classe rappresenta la fonte di una notizia. Nel file sample_api_response.json incontreremo oggetti con una struttura di questo tipo:
"source": {
    "id": "bbc-news",
    "name": "BBC News"
}
La classe ArticleSource è un POJO (Plain Old Java Object), esattamente come le classi Country e Category che abbiamo introdotto nell'Esercitazione 6.
La novità consiste nel fatto che gli oggetti potranno essere popolati automaticamente a partire dal JSON, anziché essere costruiti manualmente.
 */
public class ArticleSource {

    private String id;
    private String name;

    public ArticleSource() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}