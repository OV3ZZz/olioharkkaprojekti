package com.example.olioharkkaprojekti;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListDataInRecycleViewActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        RecyclerView DataView = findViewById(R.id.rvDataView);
        DataView.setLayoutManager(new LinearLayoutManager(this));

        DataListAdapter adapter = new DataListAdapter(getApplicationContext(), new ArrayList<>(), null);
        DataView.setAdapter(adapter);

        Intent intent = getIntent();
        String location = intent.getStringExtra("LOCATION");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                MunicipalityDataRetriever mr = new MunicipalityDataRetriever();
                WeatherDataRetriever wr = new WeatherDataRetriever();

                ArrayList<MunicipalityData> populationData = mr.getData(getApplicationContext(), location);
                WeatherData weatherData = wr.getWeatherData(location);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setData(populationData, weatherData);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
