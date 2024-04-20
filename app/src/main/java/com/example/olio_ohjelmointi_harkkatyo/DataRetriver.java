package com.example.olio_ohjelmointi_harkkatyo;
import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class DataRetriver {
    private HashMap<String, String> stateNamesToCodes = new HashMap<String, String>();
    private static DataRetriver dataRetriver = null;

    private DataRetriver(){

    }

    private void generateStateNamesToCodesTable(){
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode areas = null;

        try {
            areas = objectMapper.readTree(new URL("https://statfin.stat.fi/PxWeb/api/v1/en/StatFin/synt/statfin_synt_pxt_12dy.px"));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //System.out.println(areas.toPrettyString());

        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        for (JsonNode node : areas.get("variables").get(1).get("values")) {
            values.add(node.asText());
        }
        for (JsonNode node : areas.get("variables").get(1).get("valueTexts")) {
            keys.add(node.asText());
        }

        for(int i = 0; i < keys.size(); i++) {
            stateNamesToCodes.put(keys.get(i), values.get(i));
        }
    }

    public static DataRetriver getInstance(){
        if (dataRetriver == null) {
            dataRetriver = new DataRetriver();
            dataRetriver.generateStateNamesToCodesTable();

        }
        return dataRetriver;
    }

    private JsonNode dataQuery(Context context, String stateCode, URL url, int queryFile){
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            JsonNode jsonInputString = objectMapper.readTree(context.getResources().openRawResource(queryFile));

            ((ObjectNode) jsonInputString.get("query").get(0).get("selection")).putArray("values").add(stateCode);

            byte[] input = objectMapper.writeValueAsBytes(jsonInputString);
            OutputStream os = con.getOutputStream();
            os.write(input, 0, input.length);

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();

            String line = null;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }

            return objectMapper.readTree(response.toString());

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void getStateData(Context context, String stateName){

        ObjectMapper objectMapper = new ObjectMapper();

        String stateCode = stateNamesToCodes.get(stateName);

        URL populationUrl = null;
        URL workPlaceUrl = null;
        URL emplymentUrl = null;

        try {
            populationUrl = new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/fi/StatFin/synt/statfin_synt_pxt_12dy.px");
            workPlaceUrl = new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/fi/StatFin/tyokay/statfin_tyokay_pxt_125s.px");
            emplymentUrl = new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/fi/StatFin/tyokay/statfin_tyokay_pxt_115x.px");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        JsonNode workPlaceData = dataQuery(context, stateCode, workPlaceUrl, R.raw.work_place_query);
        //Log.d("TEST", workPlaceData.toPrettyString());

        JsonNode populationData = dataQuery(context, stateCode, populationUrl, R.raw.population_query);
        //Log.d("TEST", populationData.toPrettyString());

        JsonNode populationChangeData = dataQuery(context, stateCode, populationUrl, R.raw.population_change_query);

        JsonNode employmentData = dataQuery(context, stateCode, emplymentUrl, R.raw.employment_query);
        //Log.d("TEST", employmentData.toPrettyString());

        ArrayList<String> years = new ArrayList<>();
        ArrayList<String> population = new ArrayList<>();
        ArrayList<String> populationChange = new ArrayList<>();
        ArrayList<String> workPlace = new ArrayList<>();
        ArrayList<String> employment = new ArrayList<>();

        for (JsonNode node : populationData.get("dimension").get("Vuosi").get("category").get("label")) {
            years.add(node.asText());
        }
        for (JsonNode node : populationData.get("value")) {
            population.add(node.asText());
        }
        for (JsonNode node : populationChangeData.get("value")){
            populationChange.add(node.asText());
        }
        for (JsonNode node : workPlaceData.get("value")) {
            workPlace.add(node.asText());
        }
        for (JsonNode node : employmentData.get("value")) {
            employment.add(node.asText());
        }

        StateDataStorage.getInstance().getStateData().clear();
        for(int i = 0; i < years.size(); i++) {
            StateDataStorage.getInstance().addStateData(new StateData(Integer.parseInt(years.get(i)), Integer.parseInt(population.get(i)), Integer.parseInt(populationChange.get(i)), workPlace.get(i), employment.get(i)));
        }





    }
}
