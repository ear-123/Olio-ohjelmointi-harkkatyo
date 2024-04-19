package com.example.olio_ohjelmointi_harkkatyo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class StateDataHolder extends RecyclerView.ViewHolder {
    TextView textYear,textPopulation, textPopulationChange, textWorkPlace, textEmployment;

    public StateDataHolder(@NonNull View itemview) {
        super(itemview);
        textYear = itemview.findViewById(R.id.textViewYear);
        textPopulation = itemview.findViewById(R.id.textViewPopulation);
        textPopulationChange = itemview.findViewById(R.id.textViewPopulationChange);
        textWorkPlace = itemview.findViewById(R.id.textViewWorkPlace);
        textEmployment = itemview.findViewById(R.id.textViewEmployment);
    }


}