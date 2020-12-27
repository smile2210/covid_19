package com.example.covid_19_tracker.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.collection.ArraySet;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.covid_19_tracker.Model.ModelClass;
import com.example.covid_19_tracker.R;
import com.example.covid_19_tracker.SecondActivity;
import com.example.covid_19_tracker.ThirdActivity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> implements Filterable {

    Activity activity;
    List<ModelClass> modelList;
   public List<ModelClass> modelListfiltered;


    public MyAdapter(SecondActivity secondActivity, List<ModelClass> modelList) {
        activity = secondActivity;
        this.modelList = modelList;
        modelListfiltered = modelList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.country_listview,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {

        Glide.with(activity).load(modelListfiltered.get(position).getFlag()).into(holder.flag);
        holder.name.setText(modelListfiltered.get(position).getCountry());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ThirdActivity.class);
                intent.putExtra("position",position);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelListfiltered.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();

                if (constraint == null || constraint.length() == 0){
                    results.count = modelList.size();
                    results.values = modelList;
                }else {
                    List<ModelClass> resultmodel = new ArrayList<>();
                    String searchstr = constraint.toString().toLowerCase();

                    for (ModelClass model : modelList){
                        if (model.getCountry().toLowerCase().contains(searchstr)){
                            resultmodel.add(model);
                        }
                    }
                    results.count = resultmodel.size();
                    results.values = resultmodel;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                modelListfiltered = (List<ModelClass>) filterResults.values;
                SecondActivity.modelList = (List<ModelClass>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView flag;
        TextView name;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            flag = itemView.findViewById(R.id.flag);
            name = itemView.findViewById(R.id.name);
        }
    }
}
