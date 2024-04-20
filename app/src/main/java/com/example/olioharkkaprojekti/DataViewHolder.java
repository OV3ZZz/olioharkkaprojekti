package com.example.olioharkkaprojekti;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataViewHolder extends RecyclerView.ViewHolder{

    TextView txtName, txtPopulation, txtVaesto, txtWork, txtTyoKay, txtWeather, txtTemp, txtWind;

    public DataViewHolder(@NonNull View itemView) {
        super(itemView);
        txtName = itemView.findViewById(R.id.txtName);
        txtPopulation = itemView.findViewById(R.id.txtPopulation);
        txtVaesto = itemView.findViewById(R.id.txtVaesto);
        txtWork = itemView.findViewById(R.id.txtWork);
        txtTyoKay = itemView.findViewById(R.id.txtTyoKay);
        txtWeather = itemView.findViewById(R.id.txtWeather);
        txtTemp = itemView.findViewById(R.id.txtTemp);
        txtWind = itemView.findViewById(R.id.txtWind);
    }
}
