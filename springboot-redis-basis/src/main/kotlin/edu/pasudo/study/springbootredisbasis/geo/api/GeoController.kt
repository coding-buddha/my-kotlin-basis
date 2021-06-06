package edu.pasudo.study.springbootredisbasis.geo.api

import edu.pasudo.study.springbootredisbasis.geo.dummy.GeoDummyGenerator
import edu.pasudo.study.springbootredisbasis.geo.model.Location
import edu.pasudo.study.springbootredisbasis.geo.service.GeoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("geo")
@RestController
class GeoController(
    private val geoService: GeoService

) {

    @PostMapping
    fun saveGeo() {
        GeoDummyGenerator.locations.forEach { location ->
            geoService.saveGeoLocation(location)
        }
    }
}