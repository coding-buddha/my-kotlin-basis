package edu.pasudo.study.springbootredisbasis.geo.storage

import edu.pasudo.study.springbootredisbasis.config.CustomRedisInfo.GEO_TEMPLATE
import edu.pasudo.study.springbootredisbasis.geo.model.Location
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.geo.Point
import org.springframework.data.redis.connection.RedisGeoCommands
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class GeoRedisStorage(
    @Qualifier(GEO_TEMPLATE)
    private val redisGeoTemplate: RedisTemplate<String, String>
) {

    fun saveGeoLocation(location: Location) {
        redisGeoTemplate.opsForGeo()
            .add(
                generateKey(location.id.toString()),
                RedisGeoCommands.GeoLocation(location.name!!, Point(location.longitude, location.latitude))
            )
    }

    fun savePoint(location: Location) {
        redisGeoTemplate.opsForGeo()
            .add(
                generateKey(location.id.toString()),
                Point(location.longitude, location.latitude), location.name!!
            )
    }

    fun savePointMap(location: Location) {
        redisGeoTemplate.opsForGeo()
            .add(
                generateKey(location.id.toString()),
                mapOf(location.name!! to Point(location.longitude, location.latitude))
            )
    }

    fun generateKey(key: String): String {
        return "GEO:$key"
    }
}