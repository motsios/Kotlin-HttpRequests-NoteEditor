package com.example.notekotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.ArrayList

class CustomeAdapter (private val context: Context, private val notesModelArrayList: ArrayList<NoteModel>)//o constructor
    :BaseAdapter() {

    override fun getCount(): Int {
        return notesModelArrayList.size
    }
    override fun getItem(position: Int): Any {
        return notesModelArrayList[position]
    }
    override fun getItemId(position: Int): Long {
        return 0
    }

    private inner class ViewHolder {

        var tvtitle: TextView? = null
        var tvcontent: TextView? = null
        var tvcreatedAt: TextView? = null
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    var convertView = convertView
    val holder: ViewHolder

    if (convertView == null) {
        holder = ViewHolder()
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        convertView = inflater.inflate(R.layout.lv_item, null, true)

        holder.tvtitle = convertView!!.findViewById(R.id.title) as TextView
        holder.tvcontent = convertView.findViewById(R.id.content) as TextView
        holder.tvcreatedAt = convertView.findViewById(R.id.createdAt) as TextView
        convertView.tag = holder
    } else {
        // the getTag returns the viewHolder object set as a tag to the view
        holder = convertView.tag as ViewHolder
    }

    holder.tvtitle!!.text = "Title: " + notesModelArrayList[position].getTitles()
    holder.tvcontent!!.text = "Content: " + notesModelArrayList[position].getContents()
    holder.tvcreatedAt!!.text = "CreatedAt: " + notesModelArrayList[position].getCreatedAts()

    return convertView
}


}