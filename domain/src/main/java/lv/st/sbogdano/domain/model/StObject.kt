package lv.st.sbogdano.domain.model

import java.io.Serializable

data class StObject(
    var address: String = "",
    var construction: String = "",
    var year_of_manufacture: String = "",
    var in_service_from: String = "",
    var name: String = "",
    var city_region: String = "",
    var technical_object: String = "",
    var type: String = "",
    var x: String = "",
    var y: String = "",
    var zone_of_responsibility: String = ""
) : Serializable