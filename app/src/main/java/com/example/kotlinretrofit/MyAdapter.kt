package com.example.kotlinretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MyAdapter (val context:Context, val userList:List<myDataItem>):RecyclerView.Adapter<MyAdapter.ViewHolder>(){
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var userID:TextView
        var title:TextView

        init {
            userID = itemView.findViewById(R.id.textID)
            title =  itemView.findViewById(R.id.textTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_items,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userID.text = userList[position].userId.toString()
        holder.title.text = userList[position].body
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}