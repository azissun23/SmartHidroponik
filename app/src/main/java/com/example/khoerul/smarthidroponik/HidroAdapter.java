package com.example.khoerul.smarthidroponik;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HidroAdapter extends RecyclerView.Adapter<HidroAdapter.ViewHolder> {

    ArrayList<com.example.khoerul.smarthidroponik.Hidro> listHidro;
    Context context;

    HidroAdapter (Context context){
        this.context = context;
    }

    public ArrayList<com.example.khoerul.smarthidroponik.Hidro> getListHidro(){
        return listHidro;
    }

    public void setListHidro(ArrayList<com.example.khoerul.smarthidroponik.Hidro> listHidro){

        this.listHidro = listHidro;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hidro_item,viewGroup,false);
        return new ViewHolder(view);
}

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.namatanaman.setText(getListHidro().get(i).getNamatanaman());
        viewHolder.usiapanen.setText(getListHidro().get(i).getUsiapanen());
        viewHolder.pH_Air.setText(getListHidro().get(i).getpH_Air());
        viewHolder.minggu1.setText(getListHidro().get(i).getMinggu1());
        viewHolder.minggu2.setText(getListHidro().get(i).getMinggu2());
        viewHolder.minggu3.setText(getListHidro().get(i).getMinggu3());
        viewHolder.minggu4.setText(getListHidro().get(i).getMinggu4());
        viewHolder.minggu6_8.setText(getListHidro().get(i).getMinggu6_8());
        viewHolder.ppm_maksimum.setText(getListHidro().get(i).getPpm_maksimum());
        viewHolder.ket_panen.setText(getListHidro().get(i).getKet_panen());
        viewHolder.caraisinutrisi.setText(getListHidro().get(i).getCaraisinutrisi());
        Glide.with(context).load(getListHidro().get(i).getGambar()).into(viewHolder.gambar);
    }
    @Override
    public int getItemCount() {
        return getListHidro().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.namatanaman)
        TextView namatanaman;
        @BindView(R.id.imginfotanaman)
        ImageView gambar;
        @BindView(R.id.pH_Air)
        TextView pH_Air;
        @BindView(R.id.ppm_maksimum)
        TextView ppm_maksimum;
        @BindView(R.id.usiapanen)
        TextView usiapanen;
        @BindView(R.id.minggu1)
        TextView minggu1;
        @BindView(R.id.minggu2)
        TextView minggu2;
        @BindView(R.id.minggu3)
        TextView minggu3;
        @BindView(R.id.minggu4)
        TextView minggu4;
        @BindView(R.id.minggu6_8)
        TextView minggu6_8;
        @BindView(R.id.ket_panen)
        TextView ket_panen;
        @BindView(R.id.caraisinutrisi)
        TextView caraisinutrisi;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
