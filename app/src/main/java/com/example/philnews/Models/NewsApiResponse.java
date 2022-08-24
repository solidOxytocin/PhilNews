package com.example.philnews.Models;

import com.example.philnews.Models.NewsHeadlines;

import java.io.Serializable;
import java.util.List;

public class NewsApiResponse implements Serializable {
    //Getter Setter for status,totalResults, NewsHeadline Object
    String status;
    int totalResults;
    List<NewsHeadlines> articles;

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

    public List<NewsHeadlines> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsHeadlines> articles) {
        this.articles = articles;
    }
}
