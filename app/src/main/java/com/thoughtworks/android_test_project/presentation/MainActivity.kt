package com.thoughtworks.android_test_project

import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.thoughtworks.android_test_project.databinding.ActivityMainBinding
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val calendarViewModel: CalendarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupUi()
        subscribeUi()
    }

    private fun setupUi(){
        with(binding){
            rewardSwitch.setOnCheckedChangeListener { _, b ->  calendarViewModel.updateReward(b)}
            bestPricesButton.setOnClickListener{ calendarViewModel.calculateBestPrice() }
        }
    }

    private fun subscribeUi() {
        with(binding) {
            checkOutCalendarView.setOnDateChangeListener(::onSelectedDayChange)
            checkInCalendarView.setOnDateChangeListener(::onSelectedDayChange)
            calendarViewModel.bestHotel.observe(this@MainActivity){
                toastHotel(it?.name)
            }
        }
    }

    private fun toastHotel(hotelName : String?){
        Toast.makeText(this, hotelName, Toast.LENGTH_SHORT).show()
    }

    private fun onSelectedDayChange(calendarView: CalendarView, year: Int, month: Int, monthDay: Int) {
        val date = LocalDate.of(year, month + 1 , monthDay)
        when (calendarView.id) {
            R.id.checkInCalendarView -> {
                calendarViewModel.updateCheckInDate(date)
            }
            R.id.checkOutCalendarView -> {
                calendarViewModel.updateCheckOutDate(date)
            }
        }
    }

}
