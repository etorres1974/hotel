package com.thoughtworks.android_test_project

class HotelsRepository {

        fun getHotelList(): List<Hotel> {
            var h0 = DefaultHotel("Parque das flores", 3, Price(110, 80), Price(90, 80))
            var h1 = DefaultHotel("Jardim Botânico", 4, Price(160, 110), Price(60, 50))
            var h2 = DefaultHotel("Mar Atlãntico", 5, Price(220, 100), Price(150, 40))
            return listOf(h0, h1, h2)
        }

}