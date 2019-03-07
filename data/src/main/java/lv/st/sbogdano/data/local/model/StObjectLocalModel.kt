package lv.st.sbogdano.data.local.model

import androidx.room.Entity

@Entity(tableName = "RecentStObjects", primaryKeys = ["technical_object"])
data class StObjectLocalModel(
    var address: String?,
    var construction: String?,
    var date_of_manufacture: String?,
    var in_service_from: String?,
    var name: String?,
    var region_city: String?,
    var technical_object: String,
    var type: String?,
    var x: String?,
    var y: String?
)
