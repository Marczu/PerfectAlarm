package com.marcinmejner.simplealarm.stoper

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.viewModels.StoperEditorViewModel

class StoperEditor : AppCompatActivity() {

    class StoperEditor : AppCompatActivity() {
        private val TAG = "StoperEditor"

        //widgets
        lateinit var hourPicker: NumberPicker
        lateinit var minutePicker: NumberPicker
        lateinit var secPicker: NumberPicker

        //vars
        lateinit var stoperEditorViewModel: StoperEditorViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_alarm_editor)

            init()
        }

        private fun init() {
            initViewModel()
        }

        private fun initViewModel() {
            stoperEditorViewModel = ViewModelProviders.of(this)
                    .get(StoperEditorViewModel::class.java)

        }


    }
}
