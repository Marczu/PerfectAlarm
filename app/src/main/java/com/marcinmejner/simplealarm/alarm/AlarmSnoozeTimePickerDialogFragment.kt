package com.marcinmejner.simplealarm.alarm

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.NumberPicker
import android.widget.TextView
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.R.id.cancelDialog
import com.marcinmejner.simplealarm.model.AlarmEntity
import kotlinx.android.synthetic.main.fragment_dialog_snooze_time_picker.view.*


class AlarmSnoozeTimePickerDialogFragment : DialogFragment() {
    private val TAG = "AlarmSnoozeTimePickerDi"

    //vars
    lateinit var snoozeTimeViewModel: AlarmEditorViewModel

    //widgets
    lateinit var picker: NumberPicker
    lateinit var cancelDialog: TextView
    lateinit var saveDialog: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dialog_snooze_time_picker, container, false)
        picker = view.numberPicker
        cancelDialog = view.cancelDialog
        saveDialog = view.save_dialog

        initViewModel()
        initNumberPicker()

        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    /*Set size od dialog fragment to 70% width and 50% height*/
    override fun onResume() {
        val width = (resources.displayMetrics.widthPixels * 0.7).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.5).toInt()
        dialog.window!!.setLayout(width, height)

        super.onResume()
    }

    private fun initViewModel() {
        snoozeTimeViewModel = ViewModelProviders.of(activity!!)
                .get(AlarmEditorViewModel::class.java)
    }

    /*Init Number picked, then saving value to snoozeTime viewModel*/
    fun initNumberPicker() {
        picker.minValue = 0
        picker.maxValue = 100
        picker.value = 10
        picker.wrapSelectorWheel = false

        cancelDialog.setOnClickListener {
            AlarmSnoozeTimePickerDialogFragment@ this.dismiss()
        }

        saveDialog.setOnClickListener {
            val snoozeMinutePicked = picker.value.toString()
            snoozeTimeViewModel.snoozeTime.value = snoozeMinutePicked.toInt()
            Log.d(TAG, "initNumberPicker: viewModel ${snoozeTimeViewModel.snoozeTime.value.toString()}")
            Log.d(TAG, "initNumberPicker: picked number is: $snoozeMinutePicked ")

            AlarmSnoozeTimePickerDialogFragment@ this.dismiss()
        }

    }

}