package com.marcinmejner.simplealarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.marcinmejner.simplealarm.Alarm.AlarmsFragment
import com.marcinmejner.simplealarm.Stoper.StoperFragment
import kotlinx.android.synthetic.main.activity_main_screen.*

class MainScreenContainer : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val TAG = "MainScreenContainer"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        initBottomNavView()
        displayFragment(AlarmsFragment())
    }

    /*Displaying fragment selected by botNavView*/
    private fun displayFragment(fragment: Fragment) {
        val fm = supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }

    private fun initBottomNavView() {
        main_navigation_view.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId){
            R.id.alarm_menu -> fragment = AlarmsFragment()
            R.id.stoper_menu -> fragment = StoperFragment()
        }

        if(fragment != null) {
            displayFragment(fragment)
        }
        return true
    }


}
