package com.example.khoerul.smarthidroponik;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.ViewHolder> {

    ArrayList<Sensor> listSensor;
    Context context;

    public SensorAdapter(Context context){
        this.context = context;
    }

    public ArrayList<com.example.khoerul.smarthidroponik.Sensor> getListSensor(){
        return listSensor;
    }

    public void setListSensor(ArrayList<com.example.khoerul.smarthidroponik.Sensor> listSensor){

        this.listSensor = listSensor;
    }

    @NonNull
    @Override
    public SensorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sensor_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorAdapter.ViewHolder viewHolder, int i) {
        viewHolder.nutrisi.setText(getListSensor().get(i).getNutrisi());
        viewHolder.pH.setText(getListSensor().get(i).getpH());
        viewHolder.volair.setText(getListSensor().get(i).getVolair());
    }
    @Override
    public int getItemCount() {
        return getListSensor().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.hasilpH)
        TextView pH;
        @BindView(R.id.hasilnutrisi)
        TextView nutrisi;
        @BindView(R.id.hasilvolair)
        TextView volair;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
