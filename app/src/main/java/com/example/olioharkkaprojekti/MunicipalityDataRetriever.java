package com.example.olioharkkaprojekti;

import android.content.Context;

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

public class MunicipalityDataRetriever {

    public ArrayList<MunicipalityData> getData(Context context, String municipality) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectMapper objectMapper1 = new ObjectMapper();
        ObjectMapper objectMapper2 = new ObjectMapper();
        ObjectMapper objectMapper3 = new ObjectMapper();

        JsonNode areas = null;
        JsonNode areas1 = null;
        JsonNode areas3 = null;

        try {
            areas = objectMapper.readTree(new URL("https://statfin.stat.fi/PxWeb/api/v1/en/StatFin/synt/statfin_synt_pxt_12dy.px"));
            areas1 = objectMapper1.readTree(new URL("https://statfin.stat.fi/PxWeb/api/v1/en/StatFin/tyokay/statfin_tyokay_pxt_125s.px"));
            areas3 = objectMapper3.readTree(new URL("https://statfin.stat.fi/PxWeb/api/v1/en/StatFin/tyokay/statfin_tyokay_pxt_115x.px"));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        ArrayList<String> keys1 = new ArrayList<>();
        ArrayList<String> values1 = new ArrayList<>();

        ArrayList<String> keys3 = new ArrayList<>();
        ArrayList<String> values3 = new ArrayList<>();

        assert areas != null;
        for (JsonNode node : areas.get("variables").get(1).get("values")) {
            values.add(node.asText());
        }
        for (JsonNode node : areas.get("variables").get(1).get("valueTexts")) {
            keys.add(node.asText());
        }

        assert areas1 != null;
        for (JsonNode node : areas1.get("variables").get(1).get("values")) {
            values1.add(node.asText());
        }

        for (JsonNode node : areas1.get("variables").get(1).get("valueTexts")) {
            keys1.add(node.asText());
        }

        assert areas3 != null;
        for (JsonNode node : areas3.get("variables").get(0).get("values")) {
            values3.add(node.asText());
        }

        for (JsonNode node : areas3.get("variables").get(0).get("valueTexts")) {
            keys3.add(node.asText());
        }

        HashMap<String, String> municipalityCodes = new HashMap<>();

        HashMap<String, String> municipalityCodes1 = new HashMap<>();

        HashMap<String, String> municipalityCodes3 = new HashMap<>();

        for(int i = 0; i < keys.size(); i++) {
            municipalityCodes.put(keys.get(i), values.get(i));
        }

        for(int i = 0; i < keys1.size(); i++) {
            municipalityCodes1.put(keys1.get(i), values1.get(i));
        }

        for(int i = 0; i < keys3.size(); i++) {
            municipalityCodes3.put(keys3.get(i), values3.get(i));
        }

        String code = null;

        String code1 = null;

        String code2 = null;

        String code3 = null;

        code = null;
        code = municipalityCodes.get(municipality);

        code1 = null;
        code1 = municipalityCodes1.get(municipality);

        code2 = null;
        code2 = municipalityCodes.get(municipality);

        code3 = null;
        code3 = municipalityCodes3.get(municipality);

        try {
            URL url = new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/fi/StatFin/synt/statfin_synt_pxt_12dy.px");
            URL url1 = new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/fi/StatFin/tyokay/statfin_tyokay_pxt_125s.px");
            URL url3 = new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/fi/StatFin/tyokay/statfin_tyokay_pxt_115x.px");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            HttpURLConnection con1 = (HttpURLConnection) url1.openConnection();
            con1.setRequestMethod("POST");
            con1.setRequestProperty("Content-Type", "application/json; utf-8");
            con1.setRequestProperty("Accept", "application/json");
            con1.setDoOutput(true);

            HttpURLConnection con2 = (HttpURLConnection) url.openConnection();
            con2.setRequestMethod("POST");
            con2.setRequestProperty("Content-Type", "application/json; utf-8");
            con2.setRequestProperty("Accept", "application/json");
            con2.setDoOutput(true);

            HttpURLConnection con3 = (HttpURLConnection) url3.openConnection();
            con3.setRequestMethod("POST");
            con3.setRequestProperty("Content-Type", "application/json; utf-8");
            con3.setRequestProperty("Accept", "application/json");
            con3.setDoOutput(true);

            JsonNode jsonInputString = objectMapper.readTree(context.getResources().openRawResource(R.raw.query));

            JsonNode jsonInputString1 = objectMapper1.readTree(context.getResources().openRawResource(R.raw.query1));

            JsonNode jsonInputString2 = objectMapper2.readTree(context.getResources().openRawResource(R.raw.query2));

            JsonNode jsonInputString3 = objectMapper3.readTree(context.getResources().openRawResource(R.raw.query3));

            ((ObjectNode) jsonInputString.get("query").get(0).get("selection")).putArray("values").add(code);
            ((ObjectNode) jsonInputString1.get("query").get(0).get("selection")).putArray("values").add(code1);
            ((ObjectNode) jsonInputString2.get("query").get(0).get("selection")).putArray("values").add(code2);
            ((ObjectNode) jsonInputString3.get("query").get(0).get("selection")).putArray("values").add(code3);

            byte[] input = objectMapper.writeValueAsBytes(jsonInputString);
            OutputStream os = con.getOutputStream();
            os.write(input, 0, input.length);

            byte[] input1 = objectMapper1.writeValueAsBytes(jsonInputString1);
            OutputStream os1 = con1.getOutputStream();
            os1.write(input1, 0, input1.length);

            byte[] input2 = objectMapper2.writeValueAsBytes(jsonInputString2);
            OutputStream os2 = con2.getOutputStream();
            os2.write(input2, 0, input2.length);

            byte[] input3 = objectMapper3.writeValueAsBytes(jsonInputString3);
            OutputStream os3 = con3.getOutputStream();
            os3.write(input3, 0, input3.length);

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }

            BufferedReader br1 = new BufferedReader(new InputStreamReader(con1.getInputStream(), "utf-8"));
            StringBuilder response1 = new StringBuilder();
            String line1 = null;
            while ((line1 = br1.readLine()) != null) {
                response1.append(line1.trim());
            }

            BufferedReader br2 = new BufferedReader(new InputStreamReader(con2.getInputStream(), "utf-8"));
            StringBuilder response2 = new StringBuilder();
            String line2 = null;
            while ((line2 = br2.readLine()) != null) {
                response2.append(line2.trim());
            }

            BufferedReader br3 = new BufferedReader(new InputStreamReader(con3.getInputStream(), "utf-8"));
            StringBuilder response3 = new StringBuilder();
            String line3 = null;
            while ((line3 = br3.readLine()) != null) {
                response3.append(line3.trim());
            }

            JsonNode municipalityData = objectMapper.readTree(response.toString());

            JsonNode municipalityData1 = objectMapper1.readTree(response1.toString());

            JsonNode municipalityData2 = objectMapper2.readTree(response2.toString());

            JsonNode municipalityData3 = objectMapper3.readTree(response3.toString());

            ArrayList<String> years = new ArrayList<>();
            ArrayList<String> populations = new ArrayList<>();

            ArrayList<String> years1 = new ArrayList<>();
            ArrayList<String> populations1 = new ArrayList<>();

            ArrayList<String> populations2 = new ArrayList<>();

            ArrayList<String> years3 = new ArrayList<>();
            ArrayList<String> populations3 = new ArrayList<>();

            for (JsonNode node : municipalityData.get("dimension").get("Vuosi").get("category").get("label")) {
                years.add(node.asText());
            }

            for (JsonNode node : municipalityData.get("value")) {
                populations.add(node.asText());
            }

            for (JsonNode node : municipalityData1.get("dimension").get("Vuosi").get("category").get("label")) {
                years1.add(node.asText());
            }

            for (JsonNode node : municipalityData1.get("value")) {
                populations1.add(node.asText());
            }

            for (JsonNode node : municipalityData2.get("value")) {
                populations2.add(node.asText());
            }

            for (JsonNode node : municipalityData3.get("dimension").get("Vuosi").get("category").get("label")) {
                years3.add(node.asText());
            }

            for (JsonNode node : municipalityData3.get("value")) {
                populations3.add(node.asText());
            }

            ArrayList<MunicipalityData> populationData = new ArrayList<>();

            int i = years.size() -1;
            int ii = years1.size() -1;
            int iii = years3.size() -1;

            double populationDouble = Double.parseDouble(populations1.get(ii));
            int populationInt = (int) populationDouble;

            double populationDouble3 = Double.parseDouble(populations3.get(iii));
            int populationInt3 = (int) populationDouble3;

            populationData.add(new MunicipalityData(Integer.valueOf(years.get(i)), Integer.valueOf(populations.get(i)), populationInt, Integer.valueOf(populations2.get(i)), populationInt3));


            return populationData;

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

}


