package lv.st.sbogdano.stobjekti.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_stobject_detail.*
import lv.st.sbogdano.domain.model.StObject
import lv.st.sbogdano.stobjekti.R
import lv.st.sbogdano.stobjekti.databinding.ActivityStobjectDetailBinding
import lv.st.sbogdano.stobjekti.internal.util.driveToStObject
import lv.st.sbogdano.stobjekti.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.stobjekti.internal.util.lksToLatLng
import lv.st.sbogdano.stobjekti.navigation.Navigator
import org.koin.android.ext.android.inject

class StObjectDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private val navigator: Navigator by inject()

    lateinit var stObject: StObject

    private val binder by lazyThreadSafetyNone<ActivityStobjectDetailBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_stobject_detail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        stObject = navigator.getStObject(this) as StObject
        binder.stObject = stObject

        setupToolbar()

        btn_stobject_detail_drive.setOnClickListener { it.driveToStObject(stObject) }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar_stobject_detail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onMapReady(map: GoogleMap?) {
        val coordinates = lksToLatLng(stObject.x.toDouble(), stObject.y.toDouble())
        val latLng = LatLng(coordinates[0], coordinates[1])
        map?.addMarker(MarkerOptions()
            .position(latLng)
            .title(stObject.name))?.showInfoWindow()
        map?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
