package com.example.philnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.philnews.Models.NewsApiResponse;
import com.example.philnews.Models.NewsHeadlines;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,SelectListener {
    public RecyclerView recyclerView;
    RecAdapter adapter;
    ProgressDialog dialog;
    Toolbar toolbar;
    Button btn1,btn2,btn3,btn4,btn5,btn6;
    SearchView search_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initializing views
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(this);
        toolbar =findViewById(R.id.toolbar);
        search_bar=findViewById(R.id.search_bar);
        recyclerView=findViewById(R.id.recycler);

        //For toolbar
        toolbar.setTitle("News");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //For requesting API data
        Request manager= new Request(this);
        manager.getNewsHeadLines(listener,"general",null);

        // Dialog for loading
        dialog=new ProgressDialog(this);
        dialog.setTitle("Fetching News..");
        dialog.show();



        //Search queries
        search_bar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Searching for "+query+"...");
                dialog.show();
                //Requesting API data again but with query
                Request manager= new Request(MainActivity.this);
                manager.getNewsHeadLines(listener,"general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

    }
    //Using the OnFetchDataListener Interface
    private final OnFetchDataListener<NewsApiResponse> listener=new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onfetchData(List<NewsHeadlines> list, String message) {
            if (list.isEmpty()) {
                //Close Dialog Loading
                dialog.dismiss();
                //if no results found show a dialog
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(MainActivity.this);
                dialog2.setTitle("News not Found")
                        .setMessage("Cant find anything.")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                dialog2.create().show();



            } else {
                //news data found
                show(list);
                dialog.dismiss();

            }
        }



        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    };
    private void show(List<NewsHeadlines> list) {
        //if news data found then populate the recyler view list items with the adapter
        adapter = new RecAdapter(this,list,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(adapter);

    }

    //if a cardView has been clicked
    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        //goes to NewsDetail activity along with a data headlines
        startActivity(new Intent(this,NewsDetail.class)
                .putExtra("data",headlines));
    }

    //For the button categories
    @Override
    public void onClick(View view) {
        Button button= (Button) view;
        //Name of the button is a category
        String category= button.getText().toString();
        //Loading dialog
        dialog.setTitle("Fetching news Category: "+ category);
        dialog.show();
        //Fetch data again but with selected Category
        Request manager= new Request(this);
        //Must be all Lowercase(base from the API data)
        manager.getNewsHeadLines(listener,category.toLowerCase(Locale.ROOT),null);
    }
    //

    //For main menu lagout


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        new MenuInflater(this).inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FirebaseAuth auth= FirebaseAuth.getInstance();
        int id = item.getItemId();
        switch (id){
            case R.id.logout:
                auth.signOut();
                startActivity(new Intent(this, LoginActivity.class));
            
        }
        return super.onOptionsItemSelected(item);
    }
}