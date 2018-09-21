package com.marcinmejner.simplealarm.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.model.AlarmEntity
import com.marcinmejner.simplealarm.viewModels.StoperViewModel
import kotlinx.android.synthetic.main.stoper_list_item.view.*


class StopersRecyclerViewAdapter(val stoperList: ArrayList<AlarmEntity>, val context: Context, val stoperViewModel: StoperViewModel)
    : RecyclerView.Adapter<StopersRecyclerViewAdapter.ViewHolder>() {
    private val TAG = "AlarmsRecyclerViewAdapt"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.alarm_list_item, parent,false)
        return ViewHolder(view)
    }

        override fun getItemCount(): Int {
        return stoperList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      val stoperLayout = view.stoper_item_rel_layout
    }


}