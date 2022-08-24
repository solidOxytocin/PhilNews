package com.example.philnews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.philnews.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class NewsDetail extends AppCompatActivity {
    NewsHeadlines headlines;
    TextView txtDetailTitle,txtDetailAuthor, txtDetailTime,txtDetailDetail,txtDetailContent;
    ImageView imgDetailImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        //Initalizing views
        headlines= (NewsHeadlines) getIntent().getSerializableExtra("data");
        txtDetailTitle=findViewById(R.id.txtDetailTitle);
        txtDetailAuthor=findViewById(R.id.txtDetailAuthor);
        txtDetailTime=findViewById(R.id.txtDetailTime);
        txtDetailDetail=findViewById(R.id.txtDetailDetail);
        txtDetailContent=findViewById(R.id.txtDetailContent);
        imgDetailImg=findViewById(R.id.imgDetailImg);

        //Set Text and image from the news Data From main
        txtDetailTitle.setText(headlines.getTitle());
        txtDetailAuthor.setText(headlines.getAuthor());
        txtDetailTime.setText(headlines.getPublishedAt());
        txtDetailDetail.setText(headlines.getDescription());
        txtDetailContent.setText(headlines.getContent());
        Picasso.get().load(headlines.getUrlToImage()).into(imgDetailImg);


    }
}