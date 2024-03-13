package com.example.khaataapp;// MyAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> khattaList;
    private Context context;

    public MyAdapter(Context context, List<String> khattaList) {

        this.context = context;
        this.khattaList = khattaList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);

    }


    // it will run getItemCount() times

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (khattaList.isEmpty()) {
            return;
        } else {

            String[] splitArray = khattaList.get(position).split(" ");

            holder.tvNumber.setText(splitArray[0]);
            holder.tvTitle.setText(splitArray[1]);
            holder.tvDescription.setText(splitArray[2]);
            holder.tvDate.setText(splitArray[3]);
            holder.tvPrice.setText("Price : " + splitArray[4]);

        }

    }

    @Override
    public int getItemCount() {

        return Math.max(khattaList.size(),1);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumber, tvDate, tvTitle, tvDescription, tvPrice;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);


        }

    }
}
