package com.example.bookingmaster.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingmaster.R
import com.example.bookingmaster.model.Room

class RoomDataAdapter(
    private var list: ArrayList<Room>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RoomDataAdapter.RoomViewHolder>() {

    interface OnItemClickListener{
        fun onBookNowButtonClick(room: Room)
    }

    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textViewRoomCapacity: TextView = itemView.findViewById(R.id.tv_room_capacity)
        val textViewPrice: TextView = itemView.findViewById(R.id.tv_price)
        val button : Button = itemView.findViewById(R.id.button_book_now)

        init{
            button.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val currentPosition = this.adapterPosition
            when(v?.id){
                button.id -> listener.onBookNowButtonClick(list[currentPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.room_item, parent, false)
        return RoomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val currentItem = list[position]
        holder.textViewRoomCapacity.text = "${currentItem.capacity.toString()} adults"
        holder.textViewPrice.text = "${currentItem.price.toString()} EURO"
    }

    override fun getItemCount() = list.size

    fun setData(newlist: ArrayList<Room>){
        list = newlist
    }

}