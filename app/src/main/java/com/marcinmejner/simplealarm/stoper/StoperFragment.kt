package com.marcinmejner.simplealarm.stoper


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.adapters.StopersRecyclerViewAdapter
import com.marcinmejner.simplealarm.alarm.AlarmEditor
import com.marcinmejner.simplealarm.model.StoperEntity
import com.marcinmejner.simplealarm.utils.SampleStoperData
import com.marcinmejner.simplealarm.utils.TimerState
import com.marcinmejner.simplealarm.viewModels.StoperViewModel
import kotlinx.android.synthetic.main.fragment_alarms.view.*
import kotlinx.android.synthetic.main.fragment_stoper.view.*

class StoperFragment : Fragment() {
    private val TAG = "StoperFragment"

    //widgets
    lateinit var recyclerView: RecyclerView


    var stoperList = ArrayList<StoperEntity>()
    var notesAdapter: StopersRecyclerViewAdapter? = null
    private lateinit var stoperViewModel: StoperViewModel
    private var timerLenghtSeconds: Long = 0L
    private var secondsRemaining: Long = 0L
    private var timerState = TimerState.Stopped


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_stoper, container, false)

        init(view)

        return view
    }

    private fun init(view: View){
        initRecyclerView(view)
        initViewModel()
        createNewStoper(view)
    }

    private fun initRecyclerView(view: View) {
        recyclerView = view.stoper_recycler_view
        recyclerView.hasFixedSize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
    }

    private fun initViewModel() {
        val stopersObserver: Observer<List<StoperEntity>> = Observer {
            stoperList.clear()
            stoperList.addAll(it!!)

            notesAdapter?.notifyDataSetChanged()
        }

        stoperViewModel = ViewModelProviders.of(this)
                .get(StoperViewModel::class.java)
        stoperViewModel.stopers.observe(this, stopersObserver)

        stoperList.addAll(SampleStoperData().insertStubStoper())
        notesAdapter = StopersRecyclerViewAdapter(stoperList, activity!!, stoperViewModel)
        recyclerView.adapter = notesAdapter
    }

    /*Move to new stoper creator or editor*/
    private fun createNewStoper(view: View){
        view.add_new_stoper_btn.setOnClickListener {
            Intent(activity, StoperEditor::class.java).apply {
                startActivity(this)
            }
        }
    }
}
