package lv.st.sbogdano.stobjekti.login

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import lv.st.sbogdano.stobjekti.R
import lv.st.sbogdano.stobjekti.navigation.Navigator
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private val auth: FirebaseAuth by inject()
    private val navigator: Navigator by inject()

    private lateinit var userEmail: TextInputEditText
    private lateinit var userPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userEmail = textinputedittext_email
        userPassword = textinputedittext_password

        button_login.setOnClickListener { login(userEmail.text.toString(), userPassword.text.toString()) }
    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null) navigator.navigateToMainActivity(this)
    }

    private fun login(userEmail: String, userPassword: String) {

        progressBar_login.visibility = View.VISIBLE

        if (!validateForm()) {
            return
        }

        auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        navigator.navigateToMainActivity(this)
                        progressBar_login.visibility = View.GONE
                    } else {
                        progressBar_login.visibility = View.GONE
                        Toast.makeText(this, "Nepareiza e-pasta adrese vai parole", Toast.LENGTH_LONG).show()
                    }
                }
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = userEmail.text.toString()
        if (TextUtils.isEmpty(email)) {
            userEmail.error = "Nav E-pasta"
            valid = false
        } else {
            userEmail.error = null
        }

        val password = userPassword.text.toString()
        if (TextUtils.isEmpty(password)) {
            userPassword.error = "Nav parole"
            valid = false
        } else {
            userPassword.error = null
        }

        return valid
    }
}
