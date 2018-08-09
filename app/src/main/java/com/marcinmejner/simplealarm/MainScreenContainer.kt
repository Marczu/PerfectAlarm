package com.marcinmejner.simplealarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.marcinmejner.simplealarm.alarm.AlarmsFragment
import com.marcinmejner.simplealarm.Stoper.StoperFragment
import kotlinx.android.synthetic.main.activity_main_screen.*

class MainScreenContainer : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val TAG = "MainScreenContainer"

    lateinit var alarmFragment: AlarmsFragment
    lateinit var stoperFragment: StoperFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        initBottomNavView()
        initFragments()
        displayFragment(alarmFragment)

    }

    private fun initFragments() {
        alarmFragment = AlarmsFragment()
        stoperFragment = StoperFragment()
    }

    /*Displaying fragment selected by botNavView*/
    private fun displayFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.alarm_menu -> displayFragment(alarmFragment)
            R.id.stoper_menu -> displayFragment(stoperFragment)
        }
        return true
    }

    private fun initBottomNavView() {
        main_navigation_view.setOnNavigationItemSelectedListener(this)
    }
}
