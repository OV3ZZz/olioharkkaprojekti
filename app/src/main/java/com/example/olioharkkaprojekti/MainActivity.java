package com.example.olioharkkaprojekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private TextView txtPopulationData;
    private TextView txtWeatherData;
    private EditText editTextLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPopulationData = findViewById(R.id.txtPopulation);
        txtWeatherData = findViewById(R.id.txtWeather);
        editTextLocation = findViewById(R.id.txtEditlocation);

    }

    public void onFindBtnClick(View view) {
        Context context = this;

        MunicipalityDataRetriever mr = new MunicipalityDataRetriever();
        WeatherDataRetriever wr = new WeatherDataRetriever();

        String location = editTextLocation.getText().toString();

        ExecutorService service = Executors.newSingleThreadExecutor();

        service.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<MunicipalityData> populationData = mr.getData(context, location);
                WeatherData weatherData = wr.getWeatherData(location);

                if (populationData == null) {
                    return;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String s = "";
                        for(MunicipalityData data : populationData) {
                            s = "Väestö: " + data.getPopulation() + "\n" + "Työpaikkaomavaraisuus: " + data.getTyokay() + "%\n" + "Väestönmuutos: " + data.getVaesto() + "\n" + "Työllisyysaste: " + data.getWork() + "%";
                        }

                        txtPopulationData.setText(s);


                        txtWeatherData.setText(
                                weatherData.getName() + "\n" +
                                        "Sää nyt: " + weatherData.getMain() + " (" + weatherData.getDescription() +")\n" +
                                        "Lämpötila: " + weatherData.getTemperature() + " °C\n" +
                                        "Tuulennopeus: " + weatherData.getWindSpeed() + " m/s\n"
                        );
                    }
                });

            }
        });


    }
}
