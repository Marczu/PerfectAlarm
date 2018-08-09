package com.marcinmejner.simplealarm.alarm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.adapters.AlarmsRecyclerViewAdapter
import com.marcinmejner.simplealarm.model.AlarmEntity
import kotlinx.android.synthetic.main.fragment_alarms.*
import kotlinx.android.synthetic.main.fragment_alarms.view.*

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

        recyclerView = view.findViewById(R.id.alarms_recycler_view)
        init()
        return view
    }

    private fun init() {
        initRecyclerView()
        initViewModel()
        addSampleData()
    }


    private fun initRecyclerView() {
        recyclerView.hasFixedSize()
        val layoutManager = LinearLayoutManager(activity!!)
        recyclerView.layoutManager = layoutManager
    }

    private fun initViewModel() {
        val alarmsObserver: Observer<List<AlarmEntity>> = Observer {
            notesData.clear()
            notesData.addAll(it!!)
            Log.d(TAG, "initViewModel: minuty to: ${notesData[1].alarmMinutes} tytuł to  ${notesData[1].name} wielkość bazy to: ${notesData.size}}")

            if(notesAdapter == null){
                notesAdapter = AlarmsRecyclerViewAdapter(notesData, activity!!)
                recyclerView?.adapter = notesAdapter
                Log.d(TAG, "initViewModel: init noteAdapter")
            }else{
                notesAdapter?.notifyDataSetChanged()
            }
        }

        alarmsViewModel = ViewModelProviders.of(activity!!)
                .get(AlarmsViewModel::class.java)
        alarmsViewModel.alarms.observe(this, alarmsObserver)


    }

    private fun addSampleData() {
        Log.d(TAG, "addSampleData: klikniete")
        alarmsViewModel.addSampleData()
    }


}
