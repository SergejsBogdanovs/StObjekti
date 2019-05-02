package lv.st.sbogdano.stobjekti.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_privacy_policy.*
import lv.st.sbogdano.stobjekti.R

class PrivacyPolicyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        setupToolbar()

        markdownview_privacy_policy.loadMarkdownFromAssets("privacy_policy.md")
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar_privacy_policy)
        supportActionBar!!.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
