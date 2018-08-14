package com.marcinmejner.simplealarm.alarm

import android.app.Dialog
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
import android.view.Window.FEATURE_NO_TITLE
import android.support.constraint.ConstraintLayout
import kotlinx.android.synthetic.main.fragment_dialog_rington_chooser.view.*
import kotlinx.android.synthetic.main.fragment_dialog_snooze_time_picker.view.*


class AlarmRingtoneChooserDialogFragment : DialogFragment() {
    private val TAG = "AlarmSnoozeTimePickerDi"

    //widgets
    lateinit var cancelDialog: TextView
    lateinit var saveDialog: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dialog_rington_chooser, container, false)
        cancelDialog = view.ringtone_cancel_btn
        saveDialog = view.ringtone_save_btn

        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    /*Set size od dialog fragment to 70% width and 50% height*/
    override fun onResume() {
        val width = (resources.displayMetrics.widthPixels * 0.9).toInt()
        val height = (resources.displayMetrics.heightPixels)
        dialog.window!!.setLayout(width, height)

        super.onResume()
    }


}