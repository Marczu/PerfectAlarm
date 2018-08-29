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

    var isAlarmEnabled: Boolean = true
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

    fun updateAlarmById(alarmMinutes: String, alarmHours: String, snoozeMinutes: Int, name: String,
                        ringTone: String, daysOfWeek: String, isAlarmOn: Boolean, isSnoozeEnabled: Boolean
                        , mondayCheck: Boolean, tuesdayCheck: Boolean, wednesdayCheck: Boolean,
                        thursdayCheck: Boolean, fridayCheck: Boolean, saturdayCheck: Boolean, sundayCheck: Boolean, id: Int) {
       repository.updateAlarmById(alarmMinutes, alarmHours, snoozeMinutes, name, ringTone, daysOfWeek, isAlarmOn, isSnoozeEnabled,
               mondayCheck, tuesdayCheck, wednesdayCheck, thursdayCheck, fridayCheck,saturdayCheck, sundayCheck, id)
    }

    fun getAlarmById(alarmId: Int): LiveData<AlarmEntity> {
        return repository.getAlarmById(alarmId)
    }

    /*Check what days of week are true, when set text do display*/
    fun setDaysOfWeekText() : String{
        if(mondayToggle && tuesdayToggle && wednesdayToggle && thursdayToggle && fridayToggle
                && saturdayToggle && sundayToggle) return "Codziennie"

        /*Single Days*/
        else if((mondayToggle) && !tuesdayToggle && !wednesdayToggle && !thursdayToggle && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "PONIEDZIAŁEK"

        else if(!mondayToggle && (tuesdayToggle) && !wednesdayToggle && !thursdayToggle && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "WTOREK"

        else if(!mondayToggle && !tuesdayToggle && (wednesdayToggle) && !thursdayToggle && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "ŚRODA"

        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && (thursdayToggle) && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "CZWARTEK"

        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && !thursdayToggle && (fridayToggle)
                && !saturdayToggle && !sundayToggle) return "PIĄTEK"

        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && !thursdayToggle && !fridayToggle
                && (saturdayToggle) && !sundayToggle) return "SOBOTA"

        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && !thursdayToggle && !fridayToggle
                && !saturdayToggle && (sundayToggle)) return "NIEDZIELA"

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

        else if((mondayToggle && tuesdayToggle) && !wednesdayToggle && (thursdayToggle && fridayToggle
                && saturdayToggle && sundayToggle)) return "PON-WTO, CZW-NIE"

        else if((mondayToggle && tuesdayToggle) && !wednesdayToggle && (thursdayToggle && fridayToggle
                        && saturdayToggle) && !sundayToggle) return "PON-WTO, CZW-SOB"

        else if((mondayToggle && tuesdayToggle) && !wednesdayToggle && (thursdayToggle && fridayToggle)
                        && !saturdayToggle && !sundayToggle) return "PON-WTO, CZW-PIĄ"

        else if((mondayToggle && tuesdayToggle) && !wednesdayToggle && (thursdayToggle && fridayToggle)
                && !saturdayToggle && (sundayToggle)) return "PON-WTO, CZW-PIĄ, NIE"

        else if((mondayToggle && tuesdayToggle && wednesdayToggle) && !thursdayToggle && (fridayToggle
                && saturdayToggle && sundayToggle)) return "PON-ŚRO, PIĄ-NIE"

        else if((mondayToggle && tuesdayToggle && wednesdayToggle) && !thursdayToggle && (fridayToggle
                        && saturdayToggle) && !sundayToggle) return "PON-ŚRO, PIĄ-SOB"

        else if((mondayToggle && tuesdayToggle && wednesdayToggle) && !thursdayToggle && (fridayToggle)
                        && !saturdayToggle && !sundayToggle) return "PON-ŚRO, PIĄ"

        else if((mondayToggle && tuesdayToggle && wednesdayToggle) && !thursdayToggle && (fridayToggle)
                && !saturdayToggle && (sundayToggle)) return "PON-ŚRO, PIĄ, NIE"

        else if((mondayToggle && tuesdayToggle && wednesdayToggle && thursdayToggle) && (!fridayToggle)
                && (saturdayToggle && sundayToggle)) return "PON-CZW, SOB-NIE"

        else if((mondayToggle && tuesdayToggle && wednesdayToggle && thursdayToggle) && (!fridayToggle)
                && saturdayToggle && (!sundayToggle)) return "PON-CZW, SOB"

        else if((mondayToggle && tuesdayToggle && wednesdayToggle && thursdayToggle) && (!fridayToggle)
                && !saturdayToggle && (sundayToggle)) return "PON-CZW, NIE"

        else if((mondayToggle && tuesdayToggle && wednesdayToggle && thursdayToggle && fridayToggle)
                && !saturdayToggle && (sundayToggle)) return "PON-PIĄ, NIE"

        else if((mondayToggle) && !tuesdayToggle && (wednesdayToggle) && !thursdayToggle && (fridayToggle)
                && !saturdayToggle && (sundayToggle)) return "PON, ŚRO, PIĄ, NIE"

        else if((mondayToggle) && !tuesdayToggle && (wednesdayToggle) && !thursdayToggle && (fridayToggle)
                && !saturdayToggle && !sundayToggle) return "PON, ŚRO, PIĄ"

        else if((mondayToggle) && !tuesdayToggle && (wednesdayToggle) && !thursdayToggle && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "PON, ŚRO"

        else if((mondayToggle) && !tuesdayToggle && (wednesdayToggle && thursdayToggle) && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "PON, ŚRO-CZW"

        else if((mondayToggle) && !tuesdayToggle && (wednesdayToggle && thursdayToggle && fridayToggle)
                && !saturdayToggle && !sundayToggle) return "PON, ŚRO-PIĄ"

        else if((mondayToggle) && !tuesdayToggle && (wednesdayToggle && thursdayToggle && fridayToggle
                && saturdayToggle) && !sundayToggle) return "PON, ŚRO-SOB"

        else if((mondayToggle) && !tuesdayToggle && (wednesdayToggle && thursdayToggle && fridayToggle
                        && saturdayToggle && sundayToggle)) return "PON, ŚRO-NIE"

        else if((mondayToggle) && !tuesdayToggle && (wednesdayToggle && thursdayToggle) && !fridayToggle
                        && (saturdayToggle && sundayToggle)) return "PON, ŚRO-CZW, SOB-NIE"

        else if((mondayToggle) && !tuesdayToggle && (wednesdayToggle && thursdayToggle) && !fridayToggle
                && (saturdayToggle) && !sundayToggle) return "PON, ŚRO-CZW, SOB"

        else if((mondayToggle) && !tuesdayToggle && (wednesdayToggle && thursdayToggle) && !fridayToggle
                && (!saturdayToggle) && (sundayToggle)) return "PON, ŚRO-CZW, NIE"

        else if((mondayToggle) && !tuesdayToggle && (wednesdayToggle && thursdayToggle && fridayToggle)
                && (!saturdayToggle && !sundayToggle)) return "PON, ŚRO-PIĄ"

        else if((mondayToggle) && !tuesdayToggle && (wednesdayToggle && thursdayToggle && fridayToggle)
                && (!saturdayToggle) && sundayToggle) return "PON, ŚRO-PIĄ, NIE"

        else if((mondayToggle) && !tuesdayToggle && (wednesdayToggle && thursdayToggle && fridayToggle
                && saturdayToggle) && !sundayToggle) return "PON, ŚRO-SOB"

        else if((mondayToggle) && (!tuesdayToggle && !wednesdayToggle) && (thursdayToggle && fridayToggle)
                        && !saturdayToggle && !sundayToggle) return "PON, CZW-PIĄ"

        else if((mondayToggle) && (!tuesdayToggle && !wednesdayToggle) && (thursdayToggle && fridayToggle)
                && (!saturdayToggle) && sundayToggle) return "PON, CZW-PIĄ, NIE"

        else if((mondayToggle) && (!tuesdayToggle && !wednesdayToggle && !thursdayToggle) && (fridayToggle
                && saturdayToggle) && !sundayToggle) return "PON, PIĄ-SOB"

        else if((mondayToggle) && (!tuesdayToggle && !wednesdayToggle && !thursdayToggle && !fridayToggle)
                        && (saturdayToggle) && !sundayToggle) return "PON, SOB"

        else if((mondayToggle) && (!tuesdayToggle && !wednesdayToggle && !thursdayToggle && !fridayToggle
                && !saturdayToggle) && sundayToggle) return "PON, NIE"

        else if((mondayToggle) && (!tuesdayToggle) && wednesdayToggle && (!thursdayToggle) && (fridayToggle
                        && saturdayToggle && sundayToggle)) return "PON, ŚRO, PIĄ-NIE"

        else if((mondayToggle) && (!tuesdayToggle) && wednesdayToggle && (!thursdayToggle) && (fridayToggle
                        && saturdayToggle) && !sundayToggle) return "PON, ŚRO, PIĄ-SOB"

        else if((mondayToggle) && (!tuesdayToggle && !wednesdayToggle) && (thursdayToggle && fridayToggle
                        && saturdayToggle && sundayToggle)) return "PON, CZW-NIE"

        else if((mondayToggle) && (!tuesdayToggle && !wednesdayToggle) && (thursdayToggle) && (!fridayToggle
                        && !saturdayToggle && !sundayToggle)) return "PON, CZW"

        else if((mondayToggle) && (!tuesdayToggle && !wednesdayToggle && !thursdayToggle) && (fridayToggle)
                        && (!saturdayToggle && !sundayToggle)) return "PON, PIĄ"

        else if((mondayToggle) && (!tuesdayToggle && !wednesdayToggle && !thursdayToggle && !fridayToggle)
                && (saturdayToggle) && (!sundayToggle)) return "PON, SOB"

        else if((mondayToggle) && (!tuesdayToggle && !wednesdayToggle && !thursdayToggle && !fridayToggle
                && !saturdayToggle) && (sundayToggle)) return "PON, NIE"







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



        /*Wednesday - xxx */
        else if(!mondayToggle && !tuesdayToggle && (wednesdayToggle && thursdayToggle && fridayToggle
                        && saturdayToggle && sundayToggle)) return "ŚRODA-NIEDZIELA"

        else if(!mondayToggle && !tuesdayToggle && (wednesdayToggle && thursdayToggle && fridayToggle
                        && saturdayToggle) && !sundayToggle) return "ŚRODA-SOBOTA"

        else if(!mondayToggle && !tuesdayToggle && (wednesdayToggle && thursdayToggle && fridayToggle)
                && !saturdayToggle && !sundayToggle) return "ŚRODA-PIĄTEK"

        else if(!mondayToggle && !tuesdayToggle && (wednesdayToggle && thursdayToggle) && !fridayToggle
                && !saturdayToggle && !sundayToggle) return "ŚRODA-CZWARTEK"



        /*Thursday - xxx */
        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && (thursdayToggle && fridayToggle
                        && saturdayToggle && sundayToggle)) return "CZWARTEK-NIEDZIELA"

        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && (thursdayToggle && fridayToggle
                        && saturdayToggle) && !sundayToggle) return "CZWARTEK-SOBOTA"

        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && (thursdayToggle && fridayToggle)
                && !saturdayToggle && !sundayToggle) return "CZWARTEK-PIĄTEK"



        /*Friday - xxx*/
        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && !thursdayToggle && (fridayToggle
                        && saturdayToggle && sundayToggle)) return "PIĄTEK-NIEDZIELA"

        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && !thursdayToggle && (fridayToggle
                        && saturdayToggle) && !sundayToggle) return "PIĄTEK-SOBOTA"



        /*Saturday - xxx*/
        else if(!mondayToggle && !tuesdayToggle && !wednesdayToggle && !thursdayToggle && !fridayToggle
                        && (saturdayToggle && sundayToggle)) return "WEEKEND"

        else return ""
    }
}