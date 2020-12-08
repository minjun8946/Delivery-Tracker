package com.example.kotlinpractice2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class NameAdaptor(val context: Context, val UserList1 : ArrayList<Name>): BaseAdapter() {
    override fun getCount(): Int {
        return UserList1.size
    }

    override fun getItem(position: Int): Any {
        return UserList1[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, converterView: View?, p2: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.list_item_name,null)

        val name1 = view.findViewById<TextView>(R.id.dname)

        val name = UserList1[position]

        name1.text = name.name

        return view
    }
}