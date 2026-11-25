package com.unimib.worldnews.model;

import java.util.List;
/* La classe rappresenta una struttura JSON di questo tipo (esempio semplificato):
{
    "status": "ok",
    "totalResults": 2,
    "articles": [
        {
            "source": {
                "id": "bbc-news",
                "name": "BBC News"
            },
            "title": "First article"
        },
        {
            "source": {
                "id": "ansa",
                "name": "ANSA"
            },
            "title": "Second article"
        }
    ]
}
Quali classi saranno persistenti?
Classe	            Deserializzata da Gson	Persistenza con Room
ArticleAPIResponse	Sì	                      No
Article	            Sì	                      Entità autonoma (@Entity)
ArticleSource	    Sì	                      Incorporata in Article (@Embedded)

Non abbiamo infatti alcun interesse a memorizzare nel database l'intera risposta del servizio: vogliamo memorizzare i singoli articoli.
 */
public class ArticleAPIResponse {

    private String status;
    private int totalResults;
    private List<Article> articles;

    public ArticleAPIResponse() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}