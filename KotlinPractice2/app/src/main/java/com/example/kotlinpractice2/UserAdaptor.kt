package com.example.kotlinpractice2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class UserAdaptor(val context: Context, val UserList: ArrayList<User>): BaseAdapter() {
    override fun getCount(): Int {
        return UserList.size
    }

    override fun getItem(position: Int): Any {
        return UserList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, converterView: View?, p2: ViewGroup?): View {
       val view :View = LayoutInflater.from(context).inflate(R.layout.list_item_user,null)
        val time = view.findViewById<TextView>(R.id.time)
        val name = view.findViewById<TextView>(R.id.name)
        val description = view.findViewById<TextView>(R.id.description)

        val user = UserList[position]

        time.text = user.time
        name.text = user.name
        description.text = user.description

        return view
    }
}