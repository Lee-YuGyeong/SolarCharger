package com.market.solarcharger

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CableAdapter(var context: Context) : RecyclerView.Adapter<CableAdapter.ViewHolder>() {
    @JvmField
    var items = ArrayList<CableItem>()
    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun OnItemClick(holder: ViewHolder?, view: View?, position: Int)
    }

    override fun getItemCount(): Int {
        return items.size
    } //아이템 갯수

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.cable_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
        holder.setOnItemClickListener(listener)
    }

    fun addItem(item: CableItem) {
        items.add(item)
    }

    fun addItems(items: ArrayList<CableItem>) {
        this.items = items
    }

    fun getItem(position: Int): CableItem {
        return items[position]
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    } //클릭 이벤트

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var phone: ImageView
        var circle: ImageView
        var number: TextView
        var charger: ImageView
        var listener: OnItemClickListener? = null
        fun setItem(item: CableItem) {

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
            circle.setImageResource(R.drawable.circle_gray)
            phone.setImageResource(R.drawable.phone_gray)
            charger.visibility = View.INVISIBLE
            if (item.port_num == 1) {
                number.text = "1"
            } else if (item.port_num == 2) {
                number.text = "2"
            } else {
                number.text = "3"
            }
            if (item.broken == 1) {
                phone.setImageResource(R.drawable.phone_red)
                circle.setImageResource(R.drawable.circle_red)
                charger.visibility = View.INVISIBLE
            }
            if (item.statusInfo == 1) {
                phone.setImageResource(R.drawable.phone_blue)
                circle.setImageResource(R.drawable.circle_blue)
                charger.visibility = View.VISIBLE
                // }


                //   }
            }
        }

        fun setOnItemClickListener(listener: OnItemClickListener?) {
            this.listener = listener
        }

        init {
            phone = itemView.findViewById<View>(R.id.phone) as ImageView
            circle = itemView.findViewById<View>(R.id.circle) as ImageView
            number = itemView.findViewById<View>(R.id.number) as TextView
            charger = itemView.findViewById<View>(R.id.charger) as ImageView
            itemView.setOnClickListener { v ->
                val position = adapterPosition
                if (listener != null) {
                    listener!!.OnItemClick(this@ViewHolder, v, position)
                }
            }
        }
    }

}