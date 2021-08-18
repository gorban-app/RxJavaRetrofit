package ru.xpcom.rxjavaretrofit;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.xpcom.rxjavaretrofit.pojo.Entries;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static List<Entries.Entry> entriesList;

    public RecyclerViewAdapter() {
        entriesList = new ArrayList<>();
    }

    public void setData(List<Entries.Entry> entries) {
        entriesList.addAll(entries);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_layout, parent, false);
        RecyclerViewAdapter.ViewHolder viewHolder = new RecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Entries.Entry entry = entriesList.get(position);
        holder.textApiTitle.setText(entry.apiTitle);
        holder.textApiDescription.setText(entry.apiDescription);
        holder.textApiLink.setText(entry.apiLink);
        if(entry.apiHttps) holder.cardView.setCardBackgroundColor(Color.GREEN);
        else holder.cardView.setCardBackgroundColor(Color.GRAY);
    }

    @Override
    public int getItemCount() {
        return entriesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textApiTitle, textApiDescription, textApiLink;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            textApiTitle = itemView.findViewById(R.id.text_apiTitle);
            textApiDescription = itemView.findViewById(R.id.text_apiDescription);
            textApiLink = itemView.findViewById(R.id.text_apiLink);
        }
    }
}
