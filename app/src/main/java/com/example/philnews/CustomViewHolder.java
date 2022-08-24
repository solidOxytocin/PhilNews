package com.example.philnews;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView txtTitle,txtSource;
    ImageView imgHeadline;
    CardView cardView;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        //initializing views from headline_list_items
        txtTitle= itemView.findViewById(R.id.txtTitle);
        txtSource= itemView.findViewById(R.id.txtSource);
        imgHeadline= itemView.findViewById(R.id.imgHeadline);
        cardView= itemView.findViewById(R.id.cardView);

    }
}
