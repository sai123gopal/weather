package com.saigopal.weather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.saigopal.weather.databinding.CitesListItemBinding;
import com.saigopal.weather.models.Cites;
import com.saigopal.weather.models.Main;

import java.util.ArrayList;

public class CitesListAdapter extends RecyclerView.Adapter<CitesListAdapter.ViewHolder> {

    ArrayList<Cites> list = new ArrayList<>();
    Context context;

    public CitesListAdapter(ArrayList<Cites> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CitesListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.cites_list_item,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CitesListItemBinding binding;
        public ViewHolder(@NonNull CitesListItemBinding  itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.setClick(new Click());
        }

        public void bind(Cites cites){
            binding.setModel(cites);
        }
    }

    public class Click {
        public void open(View view,Cites cites){
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString("lon", cites.getLng()).apply();
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString("lat", cites.getLat()).apply();
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString("selectedLocationName", cites.getCity()).apply();
            context.startActivity(new Intent(context, MainActivity.class));
        }
    }

}
