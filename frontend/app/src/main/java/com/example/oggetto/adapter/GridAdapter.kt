package com.example.oggetto.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.oggetto.Model.Hobbie
import com.example.oggetto.R


class GridAdapter// TODO Auto-generated constructor stub
    (val mContext: Context, textViewResourceId: Int, val _data: List<Hobbie>) : ArrayAdapter<Hobbie>(mContext, textViewResourceId, _data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // TODO Auto-generated method stub
        var convertView = convertView
        var label = convertView as TextView?
        if (convertView == null) {
            convertView = TextView(mContext)
            label = convertView
        }
        convertView.setBackgroundColor(mContext.getColor(R.color.white))
        label!!.text = _data[position].hobbie
        return convertView
    }

    // возвращает содержимое выделенного элемента списка
    override fun getItem(position: Int): Hobbie {
        return _data[position]
    }

}