package com.example.philnews;

import com.example.philnews.Models.NewsHeadlines;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {
    void onfetchData(List<NewsHeadlines> list, String message);
    void onError(String message);
}
