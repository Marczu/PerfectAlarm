package com.marcinmejner.simplealarm.alarm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
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
        Log.d(TAG, "onCreateView: AlarmsFragment started ")

        init(view)
        return view
    }

    private fun init(view: View) {
        initRecyclerView(view)
        initViewModel()
        createNewAlarm(view)
    }

    private fun initRecyclerView(view: View) {
        recyclerView = view.alarms_recycler_view
        recyclerView.hasFixedSize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
    }

    private fun initViewModel() {
        /*Observe for changes in notesData*/
        val alarmsObserver: Observer<List<AlarmEntity>> = Observer {
            notesData.clear()
            notesData.addAll(it!!)
            Log.d(TAG, "initViewModel: alarm count: ${notesData.size}")
            notesAdapter?.notifyDataSetChanged()
        }

        alarmsViewModel = ViewModelProviders.of(activity!!)
                .get(AlarmsViewModel::class.java)
        alarmsViewModel.alarms.observe(this, alarmsObserver)

        notesAdapter = AlarmsRecyclerViewAdapter(notesData, activity!!, alarmsViewModel)
        recyclerView.adapter = notesAdapter
    }

    /*Move to new alarm creator or editor*/
    private fun createNewAlarm(view: View){
        view.add_new_alarm_btn.setOnClickListener {
            Intent(activity, AlarmEditor::class.java).apply {
                startActivity(this)
            }
        }
    }

    /*Delete all alarms from Room databse*/
    private fun deleteAllAlarms(){
        alarmsViewModel.deleteAllAlarms()
    }

}
