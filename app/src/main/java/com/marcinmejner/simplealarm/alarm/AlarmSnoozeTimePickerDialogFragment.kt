package com.marcinmejner.simplealarm.alarm

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import com.marcinmejner.simplealarm.R

class AlarmSnoozeTimePickerDialogFragment: DialogFragment() {
    private val TAG = "AlarmSnoozeTimePickerDi"

    //widgets
    lateinit var picker: NumberPicker
    lateinit var cancelDialog: TextView
    lateinit var saveDialog: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dialog_snooze_time_picker, container, false)
        picker = view.findViewById(R.id.numberPicker)
        cancelDialog = view.findViewById(R.id.cancelDialog)
        saveDialog = view.findViewById(R.id.save_dialog)

        initNumberPicker()

        return view
    }

    fun initNumberPicker() {
        picker.minValue = 1
        picker.maxValue = 100
        picker.wrapSelectorWheel = false

        cancelDialog.setOnClickListener {
            AlarmSnoozeTimePickerDialogFragment@this.dismiss()
        }

        picker.setOnValueChangedListener { picker, oldVal, newVal ->

            saveDialog.setOnClickListener {
                Log.d(TAG, "initNumberPicker: picked number is: $newVal ")

            }
        }
    }

}