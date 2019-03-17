package lv.st.sbogdano.stobjekti.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import lv.st.sbogdano.domain.model.StObject
import lv.st.sbogdano.stobjekti.R
import lv.st.sbogdano.stobjekti.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.stobjekti.navigation.Navigator
import org.koin.android.ext.android.inject

class ObjectDetailActivity : AppCompatActivity() {

    private val navigator: Navigator by inject()

    private val binder by lazyThreadSafetyNone<lv.st.sbogdano.stobjekti.databinding.ActivityObjectDetailBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_object_detail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToolbar()

        val stObject = navigator.getStObject(this)

        binder.stObject = stObject as StObject
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
