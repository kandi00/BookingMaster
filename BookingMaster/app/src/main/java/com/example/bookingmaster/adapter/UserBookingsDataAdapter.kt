package com.example.bookingmaster.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingmaster.R
import com.example.bookingmaster.model.Accommodation
import com.example.bookingmaster.model.Booking
import com.example.bookingmaster.model.BookingFull

class UserBookingsDataAdapter(private var list: ArrayList<BookingFull>,
                              private val listener: UserBookingsDataAdapter.OnItemClickListener
) : RecyclerView.Adapter<UserBookingsDataAdapter.BookingsViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(booking: BookingFull)
    }

    inner class BookingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val fromDateTV: TextView = itemView.findViewById(R.id.fromdate_booking_item_TV)
        val toDateTV: TextView = itemView.findViewById(R.id.todate_booking_item_TV)
        val deleteButton: Button = itemView.findViewById(R.id.delete_booking_Button_booking_item)


        init {
            deleteButton.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val currentPosition = this.adapterPosition
            listener.onItemClick(list[currentPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserBookingsDataAdapter.BookingsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.booking_item, parent, false)
        return BookingsViewHolder(itemView)
    }

    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: UserBookingsDataAdapter.BookingsViewHolder, position: Int) {
        val currentItem = list[position]

        holder.fromDateTV.text = currentItem.from_date
        holder.toDateTV.text = currentItem.to_date

    }

    override fun getItemCount() = list.size

    // Update the list
    fun setData(newlist: ArrayList<BookingFull>){
        list = newlist
    }

}