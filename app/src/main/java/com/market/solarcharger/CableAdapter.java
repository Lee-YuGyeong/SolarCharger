package com.market.solarcharger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CableAdapter extends RecyclerView.Adapter<CableAdapter.ViewHolder> {

    Context context;

    ArrayList<CableItem> items = new ArrayList<CableItem>();

    OnItemClickListener listener;

    public static interface OnItemClickListener {
        public void OnItemClick(ViewHolder holder, View view, int position);
    }

    public CableAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public int getItemCount() {
        return items.size();
    }//아이템 갯수


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.cable_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CableItem item = items.get(position);
        holder.setItem(item);

        holder.setOnItemClickListener(listener);
    }

    public void addItem(CableItem item) {
        items.add(item);
    }

    public void addItems(ArrayList<CableItem> items) {
        this.items = items;
    }

    public CableItem getItem(int position) {
        return items.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }//클릭 이벤트

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView phone;
        ImageView circle;
        ImageView number;

        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            phone = (ImageView) itemView.findViewById(R.id.phone);
            circle = (ImageView) itemView.findViewById(R.id.circle);
            number = (ImageView) itemView.findViewById(R.id.number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.OnItemClick(ViewHolder.this, v, position);
                    }
                }
            });

        }

        public void setItem(CableItem item) {
            phone.setImageResource(item.getPhone());
            circle.setImageResource(item.getCircle());
            number.setImageResource(item.getNumber());
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

    }


}
