package com.marcinmejner.simplealarm.alarm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.marcinmejner.simplealarm.model.AlarmEntity
import com.marcinmejner.simplealarm.model.AppRepository

class AlarmEditorViewModel(application: Application) : AndroidViewModel(application) {

    var repository: AppRepository = AppRepository.getInstance(application.applicationContext)
    var alarms: LiveData<List<AlarmEntity>>
    var snoozeTime: MutableLiveData<Int> = MutableLiveData()
    var ringtone: MutableLiveData<String> = MutableLiveData()

    var mondayToggle: Boolean = true
    var tuesdayToggle: Boolean = true
    var wednesdayToggle: Boolean = true
    var thursdayToggle: Boolean = true
    var fridayToggle: Boolean = true
    var saturdayToggle: Boolean = false
    var sundayToggle: Boolean = false

    init {
        snoozeTime.value = 10
        ringtone.value = "budzik"
        alarms = repository.alarms
    }

    fun isTitleEmpty(title: String): String{
        if(title.isBlank()){
            return "Mój alarm"
        }
        else return title
    }

    fun addNewAlarm(newAlarm: AlarmEntity){
        repository.addNewAlarm(newAlarm)
    }

    /*Check what days of week are true, when set text do display*/
    fun setDaysOfWeekText() : String{
        if(mondayToggle && tuesdayToggle && wednesdayToggle && thursdayToggle && fridayToggle
                && saturdayToggle && sundayToggle) return "Codziennie"

        /*Monday - xxx */
        else if((mondayToggle && tuesdayToggle && wednesdayToggle && thursdayToggle && fridayToggle
                && saturdayToggle) && !sundayToggle) return "PONIEDZIAŁEK-SOBOTA"

        else if((mondayToggle && tuesdayToggle && wednesdayToggle && thursdayToggle && fridayToggle)
                        && !saturdayToggle && !sundayToggle) return "PONIEDZIAŁEK-PIĄTEK"

        else if((mondayToggle && tuesdayToggle && wednesdayToggle && thursdayToggle) && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "PONIEDZIAŁEK-CZWARTEK"

        else if((mondayToggle && tuesdayToggle && wednesdayToggle) && !thursdayToggle && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "PONIEDZIAŁEK-ŚRODA"

        else if((mondayToggle && tuesdayToggle) && !wednesdayToggle && !thursdayToggle && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "PONIEDZIAŁEK-WTOREK"

        else if((mondayToggle) && !tuesdayToggle && !wednesdayToggle && !thursdayToggle && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "PONIEDZIAŁEK"

        /*Tuesday - xxx*/
        else if((!mondayToggle && tuesdayToggle && wednesdayToggle && thursdayToggle && fridayToggle
                        && saturdayToggle && sundayToggle)) return "WTOREK-NIEDZIELA"

        else if(!mondayToggle && (tuesdayToggle && wednesdayToggle && thursdayToggle && fridayToggle
                        && saturdayToggle) && !sundayToggle) return "WTOREK-SOBOTA"

        else if(!mondayToggle && (tuesdayToggle && wednesdayToggle && thursdayToggle && fridayToggle)
                && !saturdayToggle && !sundayToggle) return "WTOREK-PIĄTEK"

        else if(!mondayToggle && (tuesdayToggle && wednesdayToggle && thursdayToggle) && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "WTOREK-CZWARTEK"

        else if(!mondayToggle && (tuesdayToggle && wednesdayToggle) && !thursdayToggle && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "WTOREK-ŚRODA"

        else if(!mondayToggle && (tuesdayToggle) && !wednesdayToggle && !thursdayToggle && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "WTOREK"

        /*Wednesday - xxx */
        else if(!mondayToggle && !tuesdayToggle && (wednesdayToggle && thursdayToggle && fridayToggle
                        && saturdayToggle && sundayToggle)) return "ŚRODA-NIEDZIELA"

        else if(!mondayToggle && !tuesdayToggle && (wednesdayToggle && thursdayToggle && fridayToggle
                        && saturdayToggle) && !sundayToggle) return "ŚRODA-SOBOTA"

        else if(!mondayToggle && !tuesdayToggle && (wednesdayToggle && thursdayToggle && fridayToggle)
                && !saturdayToggle && !sundayToggle) return "ŚRODA-PIĄTEK"

        else if(!mondayToggle && !tuesdayToggle && (wednesdayToggle && thursdayToggle) && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "ŚRODA-CZWARTEK"

        else if(!mondayToggle && !tuesdayToggle && (wednesdayToggle) && !thursdayToggle && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "ŚRODA"

        /*Thursday - xxx */
        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && (thursdayToggle && fridayToggle
                        && saturdayToggle && sundayToggle)) return "CZWARTEK-NIEDZIELA"

        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && (thursdayToggle && fridayToggle
                        && saturdayToggle) && !sundayToggle) return "CZWARTEK-SOBOTA"

        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && (thursdayToggle && fridayToggle)
                && !saturdayToggle && !sundayToggle) return "CZWARTEK-PIĄTEK"

        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && (thursdayToggle) && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "CZWARTEK"

        /*Friday - xxx*/
        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && !thursdayToggle && (fridayToggle
                        && saturdayToggle && sundayToggle)) return "PIĄTEK-NIEDZIELA"

        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && !thursdayToggle && (fridayToggle
                        && saturdayToggle) && !sundayToggle) return "PIĄTEK-SOBOTA"

        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && !thursdayToggle && (fridayToggle)
                && !saturdayToggle && !sundayToggle) return "PIĄTEK"

        /*Saturday - xxx*/
        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && !thursdayToggle && !fridayToggle
                        && (saturdayToggle && sundayToggle)) return "WEEKEND"

        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && !thursdayToggle && !fridayToggle
                        && (saturdayToggle) && !sundayToggle) return "SOBOTA"

        /*Sunday*/
        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && !thursdayToggle && !fridayToggle
                && !saturdayToggle && (sundayToggle)) return "NIEDZIELA"


        else return "coś"
    }
}