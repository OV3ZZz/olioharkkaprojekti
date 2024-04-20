package com.example.olioharkkaprojekti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataListAdapter extends RecyclerView.Adapter<DataViewHolder> {
    private Context context;

    private ArrayList<MunicipalityData> municipalityData = new ArrayList<>();

    private WeatherData weatherData;

    public DataListAdapter(Context applicationContext, ArrayList<MunicipalityData> populationData, WeatherData weatherData) {
        this.context = applicationContext;
        this.municipalityData = populationData;
        this.weatherData = weatherData;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataViewHolder(LayoutInflater.from(context).inflate(R.layout.data_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        MunicipalityData data = municipalityData.get(position);
        holder.txtName.setText(weatherData.getName());
        holder.txtPopulation.setText("Väestö: " + data.getPopulation());
        holder.txtVaesto.setText("Väestönmuutos: " + data.getVaesto());
        holder.txtWork.setText("Työllisyysaste: " + data.getWork() + "%");
        holder.txtTyoKay.setText("Työpaikkaomavaraisuus: " + data.getTyokay() + "%");
        holder.txtWeather.setText("Sää nyt: " + weatherData.getMain() + " (" + weatherData.getDescription() + ")");
        holder.txtTemp.setText("Lämpötila: " + weatherData.getTemperature() + " °C");
        holder.txtWind.setText("Tuulennopeus: " + weatherData.getWindSpeed() + " m/s");
    }

    @Override
    public int getItemCount() {
        return municipalityData.size();
    }

    public void setData(ArrayList<MunicipalityData> populationData, WeatherData weatherData) {
        this.municipalityData = populationData;
        this.weatherData = weatherData;
        notifyDataSetChanged();
    }
}
