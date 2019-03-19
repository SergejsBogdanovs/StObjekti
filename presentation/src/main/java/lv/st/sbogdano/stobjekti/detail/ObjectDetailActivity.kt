package lv.st.sbogdano.stobjekti.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import lv.st.sbogdano.domain.model.StObject
import lv.st.sbogdano.stobjekti.R
import lv.st.sbogdano.stobjekti.databinding.ActivityObjectDetailBinding
import lv.st.sbogdano.stobjekti.internal.util.driveToObject
import lv.st.sbogdano.stobjekti.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.stobjekti.internal.util.lksToLatLon
import lv.st.sbogdano.stobjekti.navigation.Navigator
import org.koin.android.ext.android.inject

class ObjectDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private val navigator: Navigator by inject()

    lateinit var stObject: StObject

    private val binder by lazyThreadSafetyNone<ActivityObjectDetailBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_object_detail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar()

        stObject = navigator.getStObject(this) as StObject
        binder.stObject = stObject
        binder.drive.setOnClickListener { it.driveToObject(stObject) }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        val coordinates = lksToLatLon(stObject.x.toDouble(), stObject.y.toDouble())
        val latLng = LatLng(coordinates[0], coordinates[1])
        map?.addMarker(MarkerOptions()
                .position(latLng)
                .title(stObject.name))
        map?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f))
    }

    private fun setupToolbar() {
        setSupportActionBar(binder.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
