package com.example.oggetto.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oggetto.EventInfoActivity
import com.example.oggetto.EventsActivity
import com.example.oggetto.Model.Event
import com.example.oggetto.R
import kotlinx.coroutines.NonDisposableHandle.parent

class RecyclerViewAdapter( private val events: List<Event>, private val context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameEvent: TextView = itemView.findViewById(R.id.nameEvent)
        val address: TextView = itemView.findViewById(R.id.address)
        val countPeople: TextView = itemView.findViewById(R.id.amount_peoples)
        val data: TextView = itemView.findViewById(R.id.data)
        val card: View = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.template_event_info, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameEvent.text = events[position].title
        holder.address.text = events[position].description
        holder.countPeople.text = events[position].subscribers.size.toString()
        holder.data.text = events[position].startDate.toString()
        holder.card.setOnClickListener(View.OnClickListener {
            var intent = Intent(context, EventInfoActivity().javaClass)
            intent.putExtra("id", events[position].id.toString())
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return events.size
    }
}