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

public class MCableAdapter extends RecyclerView.Adapter<MCableAdapter.ViewHolder> {

    Context context;

    ArrayList<CableItem> items = new ArrayList<CableItem>();

    OnItemClickListener listener;

    public static interface OnItemClickListener {
        public void OnItemClick(ViewHolder holder, View view, int position);
    }

    public MCableAdapter(Context context) {
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
        TextView number;
        ImageView charger;

        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            phone = (ImageView) itemView.findViewById(R.id.phone);
            circle = (ImageView) itemView.findViewById(R.id.circle);
            number = (TextView) itemView.findViewById(R.id.number);
            charger = (ImageView) itemView.findViewById(R.id.charger);

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

//            number.setImageResource(item.getNumber());
//
//            if (item.getCharger() == 1) {
//                phone.setImageResource(R.drawable.phone_blue);
//                circle.setImageResource(R.drawable.circle_blue);
//                charger.setVisibility(View.VISIBLE);
//            } else {
//                phone.setImageResource(R.drawable.phone_gray);
//                circle.setImageResource(R.drawable.circle_gray);
//                charger.setVisibility(View.VISIBLE);
//            }

            //   if(item.getPort_num()==1){
            //number.setImageResource(item.getNumber());
            //   phone.setImageResource(R.drawable.phone_blue);
            circle.setImageResource(R.drawable.circle_gray);
            phone.setImageResource(R.drawable.phone_gray);
            charger.setVisibility(View.INVISIBLE);
            if (item.getPort_num() == 1) {
                number.setText("1");
            } else if (item.getPort_num() == 2) {
                number.setText("2");
            } else {
                number.setText("3");
            }


            if (item.getReport() == 1) {
                phone.setImageResource(R.drawable.phone_yellow);
                circle.setImageResource(R.drawable.circle_yellow);
                charger.setVisibility(View.INVISIBLE);
            }
            if (item.getBroken() == 1) {
                phone.setImageResource(R.drawable.phone_red);
                circle.setImageResource(R.drawable.circle_red);
                charger.setVisibility(View.INVISIBLE);
            }
            if (item.getStatusInfo() == 1) {
                phone.setImageResource(R.drawable.phone_blue);
                circle.setImageResource(R.drawable.circle_blue);
                charger.setVisibility(View.VISIBLE);
                // }


                //   }

            }
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

    }


}
