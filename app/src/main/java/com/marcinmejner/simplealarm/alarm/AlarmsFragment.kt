package com.marcinmejner.simplealarm.alarm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.adapters.AlarmsRecyclerViewAdapter
import com.marcinmejner.simplealarm.model.AlarmEntity

class AlarmsFragment : Fragment() {
    private val TAG = "AlarmsFragment"

    //widgets
    lateinit var recyclerView: RecyclerView

    //vars
    var notesData = ArrayList<AlarmEntity>()
    var notesAdapter: AlarmsRecyclerViewAdapter? = null
    lateinit var alarmsViewModel: AlarmsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_alarms, container, false)
        Log.d(TAG, "onCreateView: AlarmsFragment started ")

        recyclerView = view.findViewById(R.id.alarms_recycler_view)
        init()
        return view
    }

    private fun init() {
        initRecyclerView()
        initViewModel()
    }


    private fun initRecyclerView() {
        recyclerView.hasFixedSize()
        val layoutManager = LinearLayoutManager(activity!!)
        recyclerView.layoutManager = layoutManager
        notesAdapter = AlarmsRecyclerViewAdapter(notesData, activity!!)
        recyclerView?.adapter = notesAdapter
    }

    private fun initViewModel() {
        val alarmsObserver: Observer<List<AlarmEntity>> = Observer {
            notesData.clear()
            notesData.addAll(it!!)
            notesAdapter?.notifyDataSetChanged()
        }

        alarmsViewModel = ViewModelProviders.of(activity!!)
                .get(AlarmsViewModel::class.java)
        alarmsViewModel.alarms.observe(this, alarmsObserver)


    }

    private fun addSampleData() {
        Log.d(TAG, "addSampleData: klikniete")
        alarmsViewModel.addSampleData()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: insite onDestroy")
    }


}
