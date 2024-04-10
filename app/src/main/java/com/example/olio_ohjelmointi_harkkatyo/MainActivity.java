package com.example.olio_ohjelmointi_harkkatyo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.olio_ohjelmointi_harkkatyo.fragments.StateDataFragment;
import com.example.olio_ohjelmointi_harkkatyo.fragments.StateDataNotFoundFragment;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private EditText searchBar;
    private Button searchButton;
    private TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        searchBar = findViewById(R.id.editTextSearchBar);
        searchButton = findViewById(R.id.buttonSearch);
        testText = findViewById(R.id.testText);
    }


    public void onSearchButtonClick(View view) {
        //Log.d("TEST", "Nappula toimii");

        final Boolean[] dataFound = {false};

        Context context = this;
        DataRetriver dataRetriver = new DataRetriver();
        String stateName = searchBar.getText().toString();
        ExecutorService service = Executors.newSingleThreadExecutor();

        service.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<StateData> populationData = dataRetriver.getStateData(context, stateName);

                if (populationData == null) {
                    return;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String s = "";
                        for (StateData data : populationData) {
                            s = s + data.getYear() + ": " + data.getPopulation() + "\n";
                        }
                        testText.setText(s);


                    }
                });
                dataFound[0] = true;

                Log.d("TEST", "Data haettu");
            }
        });

        Fragment fragment;

        if(dataFound[0]){
            fragment = new StateDataFragment();
        } else {
            fragment = new StateDataNotFoundFragment();
        }


    }
}