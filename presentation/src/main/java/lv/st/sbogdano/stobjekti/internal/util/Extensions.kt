package lv.st.sbogdano.stobjekti.internal.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import lv.st.sbogdano.domain.model.StObject

fun View.driveToObject(item: StObject) {
    val latLng = lksToLatLon(item.x.toDouble(), item.y.toDouble())
    val geoLocation = Uri.parse("geo:${latLng[0]},${latLng[1]}")
    showMap(this.context, geoLocation)
}

private fun showMap(context: Context, geoLocation: Uri) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = geoLocation
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}