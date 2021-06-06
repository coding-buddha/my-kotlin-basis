package edu.pasudo.study.springbootredisbasis.geo.service

import edu.pasudo.study.dummy.GeoDummyGenerator
import edu.pasudo.study.springbootredisbasis.IntegrationTest
import mu.KLogging
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@DisplayName("GeoService 는")
internal class GeoServiceTest : IntegrationTest() {

    @Autowired
    private lateinit var geoService: GeoService

    companion object : KLogging()

    @BeforeEach
    fun setup() {

    }

    @Test
    @DisplayName("초기화 테스트를 수행한다.")
    fun initTest() {
        logger.info { "초기화 수행 완료" }
    }

    @Test
    @DisplayName("위치값을 저장한다.")
    fun saveGeoLocationTest() {

        // given
        val locations = GeoDummyGenerator.locations

        // when
        // FIXME geo Operation 이 잘 작동되지 않는다. : embedded redis 의 서버 버전을 올려주어야 한다.
        locations.forEach { location ->
            geoService.saveGeoLocation(location)
        }

        // then

    }
}