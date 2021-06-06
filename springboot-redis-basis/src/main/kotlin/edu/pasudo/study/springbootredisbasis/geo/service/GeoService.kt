package edu.pasudo.study.springbootredisbasis.geo.service

import edu.pasudo.study.springbootredisbasis.geo.model.Location
import edu.pasudo.study.springbootredisbasis.geo.storage.GeoRedisStorage
import org.springframework.stereotype.Service

@Service
class GeoService(
    private val geoRedisStorage: GeoRedisStorage
) {

    fun saveGeoLocation(location: Location) {
        geoRedisStorage.saveGeoLocation(location)
    }

    fun getGeos() {

    }
}