package com.lifehacker.test

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class Adapter(context: Context,al_details:ArrayList<Model>) : BaseAdapter(){

    private val mInflator: LayoutInflater
    private val al_details:ArrayList<Model>

    init {
        this.mInflator = LayoutInflater.from(context)
        this.al_details=al_details
    }

    override fun getCount(): Int {
        return al_details.size
    }

    override fun getItem(position: Int): Any {
        return al_details.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.adapter_layout, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        vh.tv_name.text = al_details.get(position).name
        vh.tv_email.text = al_details.get(position).email
        vh.tv_address.text = al_details.get(position).address
        if (position%2==0) {
            vh.ll_main.setBackgroundColor(Color.parseColor("#e4e2e2"))
        }else{
            vh.ll_main.setBackgroundColor(Color.parseColor("#FFFFFF"))

        }
        return view
    }
}

private class ListRowHolder(row: View?) {
    public val tv_name: TextView
    public val tv_email: TextView
    public val tv_address: TextView
    public val ll_main: LinearLayout

    init {
        this.tv_name = row?.findViewById<TextView>(R.id.tv_name) as TextView
        this.tv_email = row?.findViewById<TextView>(R.id.tv_email) as TextView
        this.tv_address = row?.findViewById<TextView>(R.id.tv_address) as TextView
        this.ll_main = row?.findViewById<LinearLayout>(R.id.ll_main) as LinearLayout
    }


}