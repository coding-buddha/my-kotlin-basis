package edu.pasudo.study.dummy

import edu.pasudo.study.springbootredisbasis.geo.model.Location

object GeoDummyGenerator {

    val locations = listOf<Location>(
        LocationModel.fixture("경복궁", 37.57813267941899, 126.97683119199652),
        LocationModel.fixture("혜화역", 37.582300027528305, 127.00184796736721),
        LocationModel.fixture("서울시립대학교", 37.58387310181744, 127.05905929674239),
        LocationModel.fixture("왕십리역", 37.560919126776426, 127.03868457429061),
        LocationModel.fixture("춘천시청", 37.88118731355785, 127.72989820079358),
        LocationModel.fixture("한양대", 37.556774413000646, 127.0452599779498),
        LocationModel.fixture("국회의사당", 37.53183914150639, 126.9139860913459),
        LocationModel.fixture("여의도 한강공원", 37.53125879303792, 126.92697594050796),
        LocationModel.fixture("선유도 한강공원", 37.54344562477428, 126.90007852071841),
        LocationModel.fixture("난지 한강공원", 37.56608664678722, 126.87643444803292),
        LocationModel.fixture("서울 월드컵 경기장", 37.568209894298164, 126.89728794254724),
        LocationModel.fixture("용산역", 37.52979673603268, 126.96471637421018),
        LocationModel.fixture("CGV 강남점", 37.50153696360998, 127.02631580436577),
        LocationModel.fixture("잠실 어울림 축구장", 37.503449522443034, 127.07740417512292),
        LocationModel.fixture("천마공원", 37.500307008018105, 127.15679914601765),
        LocationModel.fixture("롯데월드 잠실점", 37.51111191787116, 127.09792074315803),
        LocationModel.fixture("올림픽 공원", 37.5198887887141, 127.12287361108952),
        LocationModel.fixture("수원 지방 검찰청", 37.276586817941784, 127.05241490686895),
        LocationModel.fixture("용인 에버랜드", 37.29637872133433, 127.20341207371884),
    )
}

object LocationModel {
    fun fixture(
        name: String,
        longitude: Double,
        latitude: Double
    ): Location {
        return Location(
            FixtureIdGenerator.id(),
            name,
            longitude,
            latitude
        )
    }
}