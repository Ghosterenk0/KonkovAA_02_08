package ru.ghosternko.konkovaa

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)


        val et_log = findViewById<EditText>(R.id.et_login)
        val et_pass = findViewById<EditText>(R.id.et_password)
        val btn1 = findViewById<Button>(R.id.btnLog)

        val setting = getSharedPreferences("pref", MODE_PRIVATE)
        val keyLogin = "LOGIN"
        val keyPassword = "PASS"


        btn1.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            val loginText = et_log.text.toString()
            val passwordText = et_pass.text.toString()

            if(loginText.isEmpty() && passwordText.isEmpty()) {
                Toast.makeText(this, "введите данные", Toast.LENGTH_SHORT).show()
            } else {
                if (!setting.contains(keyLogin) && !setting.contains(keyPassword)) {
                    val editor = setting.edit()
                    editor.putString(keyLogin, loginText)
                    editor.putString(keyPassword, passwordText)
                    editor.apply()
                    startActivity(intent)
                } else if (loginText != setting.getString(keyLogin, "") ||
                    passwordText != setting.getString(keyPassword, "")) {
                    Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
                } else {
                    startActivity(intent)
                }
            }
        }

    }
}
