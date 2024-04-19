package com.example.olio_ohjelmointi_harkkatyo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StateDataAdapter extends RecyclerView.Adapter<StateDataHolder> {
    private Context context;
    private ArrayList<StateData> stateData;


    public StateDataAdapter(Context context, ArrayList<StateData> stateData){
        this.context = context;
        this.stateData = stateData;
    }

    @NonNull
    @Override
    public StateDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StateDataHolder(LayoutInflater.from(context).inflate(R.layout.activity_state_data_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StateDataHolder holder, int position) {
        holder.textYear.setText(String.valueOf(stateData.get(position).getYear()));
        holder.textPopulation.setText(String.valueOf(stateData.get(position).getPopulation()));
        holder.textPopulationChange.setText(String.valueOf(stateData.get(position).getPopulationChange()));
        holder.textWorkPlace.setText(stateData.get(position).getWorkPlace());
        holder.textEmployment.setText(stateData.get(position).getEmployment());
    }

    @Override
    public int getItemCount() {
        return stateData.size();
    }
}
