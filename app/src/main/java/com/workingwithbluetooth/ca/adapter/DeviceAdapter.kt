package com.workingwithbluetooth.ca.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.workingwithbluetooth.ca.R
import com.workingwithbluetooth.ca.dto.DeviceDTO
import kotlinx.android.synthetic.main.item_list_devices.view.*

class DeviceAdapter(items : List<DeviceDTO>, mContext: Context) : RecyclerView.Adapter<DeviceAdapter.ViewHolder>(){

    private var list = items
    private var context = mContext

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DeviceAdapter.ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.address.text = list[position].address
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_list_devices,parent,false))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name = view.tvName!!
        val address = view.tvAddress!!
    }
}