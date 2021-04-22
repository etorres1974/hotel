package com.thoughtworks.android_test_project

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class CalendarViewModel(
    private val hotelComparator: HotelComparator = DefaultHotelComparator(),
    private val hotelsRepository: HotelsRepository = HotelsRepository()
) : ViewModel(), LifecycleObserver {

    val bestHotel: LiveData<Hotel> get() = _bestHotel
    private val _bestHotel by lazy { MutableLiveData<Hotel>() }

    private val _checkOutDate by lazy { MutableLiveData<LocalDate>() }
    private val _checkInDate by lazy { MutableLiveData<LocalDate>() }
    private val _rewardUser by lazy { MutableLiveData<Boolean>() }
    private val _hotels by lazy { MutableLiveData<List<Hotel>>() }


    init {
        _hotels.value = hotelsRepository.getHotelList()
        _checkInDate.value = LocalDate.now()
        _checkOutDate.value = LocalDate.now()
        _rewardUser.value = false
        _hotels.value = hotelsRepository.getHotelList()
    }

    fun updateCheckInDate(date: LocalDate) {
        _checkInDate.value = date
    }

    fun updateCheckOutDate(date: LocalDate) {
        _checkOutDate.value = date
    }

    fun updateReward(reward: Boolean) {
        _rewardUser.value = reward
    }

    fun calculateBestPrice() {
        _bestHotel.value = _checkInDate.value?.let { checkInDate ->
            _checkOutDate.value?.let { checkoutDate ->
                _hotels.value?.let { hotelList ->
                    _rewardUser.value?.let { reward ->
                        hotelComparator.findBestPrice(
                            checkInDate,
                            checkoutDate,
                            hotelList,
                            reward
                        )
                    }
                }
            }
        }
    }

}