package com.thoughtworks.android_test_project

import java.time.LocalDate
class DefaultHotelComparator : HotelComparator {

    override fun findBestPrice(
        start: LocalDate,
        end: LocalDate,
        hotels: List<Hotel>,
        reward: Boolean
    ): Hotel {
        var best = hotels.first()
        hotels.forEach { hotel ->
            var comparedValue = hotel.comparePrice(start, end, best, reward)
            if (comparedValue == -1)
                best = hotel
            else if (comparedValue == 0)
                best = hotel.compareClassification(best)
        }
        return best
    }

    private fun Hotel.comparePrice(
        start: LocalDate,
        end: LocalDate,
        hotel: Hotel,
        reward: Boolean
    ): Int {
        val price      = calculatePrice(start,end)
        val otherPrice = hotel.calculatePrice(start,end)
        return if (reward)
            price.reward.compareTo(otherPrice.reward)
        else
            price.regular.compareTo(otherPrice.regular)
    }

    private fun Hotel.compareClassification(hotel: Hotel): Hotel {
        return when {
            classification > hotel.classification -> this
            classification < hotel.classification -> hotel
            else -> this //Todo em caso de empate duplo retornar quem?
        }

    }
}