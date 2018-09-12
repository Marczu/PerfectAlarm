package com.marcinmejner.simplealarm.alarm

import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.viewModels.AlarmEditorViewModel
import kotlinx.android.synthetic.main.fragment_dialog_rington_chooser.view.*


class AlarmRingtoneChooserDialogFragment : DialogFragment() {
    private val TAG = "AlarmSnoozeTimePickerDi"

    //vars
    lateinit var ringtoneChooserViewModel: AlarmEditorViewModel
    lateinit var player: MediaPlayer

    //widgets
    lateinit var cancelDialog: TextView
    lateinit var saveDialog: TextView
    lateinit var radioGroup: RadioGroup

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dialog_rington_chooser, container, false)

        initWidgets(view)

        initViewModel()
        saveRingtone(view)
        cancelDialog()
        ringtonePlayer(view)

        return view
    }

    private fun initWidgets(view: View) {
        cancelDialog = view.ringtone_cancel_btn
        saveDialog = view.ringtone_save_btn
        radioGroup = view.radioGroup
        player = MediaPlayer()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    private fun initViewModel() {
        ringtoneChooserViewModel = ViewModelProviders.of(activity!!)
                .get(AlarmEditorViewModel::class.java)
    }

    fun saveRingtone(view: View){
        saveDialog.setOnClickListener {
            val ringtoneChoosen = (view.findViewById(radioGroup.checkedRadioButtonId) as RadioButton).text.toString()
            ringtoneChooserViewModel.ringtone.value = ringtoneChoosen
            player.stop()
            AlarmRingtoneChooserDialogFragment@ this.dismiss()
        }
    }

    fun cancelDialog(){
        cancelDialog.setOnClickListener {
            player.stop()
            AlarmRingtoneChooserDialogFragment@ this.dismiss()
        }
    }

    private fun ringtonePlayer(view: View) {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->

            player.stop()
            val ringtoneChoosen = (view.findViewById(radioGroup.checkedRadioButtonId) as RadioButton).text.toString()
                    .toLowerCase().replace(" ", "_")
            val resID = resources.getIdentifier(ringtoneChoosen,
                    "raw", activity?.packageName)
            player = MediaPlayer.create(activity, resID)

            player.isLooping = true
            player.start()
        }
    }

    /*Set size od dialog fragment to 70% width and 50% height*/
    override fun onResume() {
        val width = (resources.displayMetrics.widthPixels * 0.9).toInt()
        val height = (resources.displayMetrics.heightPixels)
        dialog.window!!.setLayout(width, height)

        super.onResume()
    }


}