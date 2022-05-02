package com.example.bookingmaster.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingmaster.R
import com.example.bookingmaster.model.Accommodation

class AccommodationDataAdapter(
    private var list: ArrayList<Accommodation>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<AccommodationDataAdapter.AccommodationViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(accommodation: Accommodation)
    }

    // 1. user defined ViewHolder type - Embedded class!
    inner class AccommodationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val textViewName: TextView = itemView.findViewById(R.id.tv_accommodation_name)
        val textViewLocation: TextView = itemView.findViewById(R.id.tv_room_capacity)
        val textViewType: TextView = itemView.findViewById(R.id.tv_accommodation_type)
        val textViewCapacity: TextView = itemView.findViewById(R.id.tv_accommodation_capacity)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val currentPosition = this.adapterPosition
            listener.onItemClick(list[currentPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccommodationDataAdapter.AccommodationViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.accommodation_item, parent, false)
        return AccommodationViewHolder(itemView)
    }

    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: AccommodationViewHolder, position: Int) {
        val currentItem = list[position]
        holder.textViewName.text = currentItem.name
        holder.textViewLocation.text = currentItem.location
        val accommodationTypes = mapOf(0 to "Hotel", 1 to "Apartment", 2 to "Resort", 3 to "Hostel", 4 to "Cabin")
        holder.textViewType.text = accommodationTypes[currentItem.type]
        holder.textViewCapacity.text = "${currentItem.total_Number_Of_Rooms} ${holder.textViewCapacity.text}"
    }

    override fun getItemCount() = list.size

    // Update the list
    fun setData(newlist: ArrayList<Accommodation>){
        list = newlist
    }

}