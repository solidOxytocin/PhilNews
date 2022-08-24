package com.example.philnews;

import android.content.Context;
import android.widget.Toast;

import com.example.philnews.Models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Request {
    Context context;


    //For getting the HTTP API to Java using Retrofit
    Retrofit retrofit= new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //Managing using calling the API
    public void getNewsHeadLines(OnFetchDataListener listener, String category, String query){
        getNewsApi getNewsApi= retrofit.create(getNewsApi.class);
        Call<NewsApiResponse> call= getNewsApi.callHeadlines("ph",category,query,context.getString(R.string.api_key));

        try{
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                    }
                    //setting the interface fetching news from API
                    listener.onfetchData(response.body().getArticles(),response.message());
                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                    listener.onError("Request Failed!");
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Request(Context context) {
        this.context = context;
    }


    //Calling the API
    public interface getNewsApi{
        @GET("top-headlines")
        Call<NewsApiResponse> callHeadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String api_key
        );


    }
}
