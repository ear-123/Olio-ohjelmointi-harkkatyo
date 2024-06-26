package com.example.olio_ohjelmointi_harkkatyo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.olio_ohjelmointi_harkkatyo.R;
import com.example.olio_ohjelmointi_harkkatyo.StateDataAdapter;
import com.example.olio_ohjelmointi_harkkatyo.StateDataStorage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StateDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StateDataFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StateDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StateDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StateDataFragment newInstance(String param1, String param2) {
        StateDataFragment fragment = new StateDataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_state_data, container, false);

        // Set data to Recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewData);


        recyclerView.setAdapter(new StateDataAdapter(this.getContext(), StateDataStorage.getInstance().getStateData()));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }

}