package com.example.olio_ohjelmointi_harkkatyo;

import java.util.ArrayList;

public class StateDataStorage {
    private static StateDataStorage stateDataStorage = null;
    private ArrayList<StateData> stateData = new ArrayList<StateData>();


    private StateDataStorage(){

    }

    public static StateDataStorage getInstance(){
        if (stateDataStorage == null) {
            stateDataStorage = new StateDataStorage();
        }
        return stateDataStorage;
    }
    public ArrayList<StateData> getStateData() {
        return stateData;
    }

    public void addStateData(StateData stateData){
        this.stateData.add(stateData);
    }
}
