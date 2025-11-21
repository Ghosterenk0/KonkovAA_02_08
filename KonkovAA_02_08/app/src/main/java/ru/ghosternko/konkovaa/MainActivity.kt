package ru.ghosternko.konkovaa

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val items = listOf("Сделать заказ", "История заказов", "Выйти")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val lv = findViewById<ListView>(R.id.lv_items)

        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            items
        )

        lv.adapter = adapter
        lv.setOnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
                    val Intent = Intent(this, OrderScreenActivity::class.java)
                    startActivity(Intent)
                }
                1 -> {
                    val Intent = Intent(this, HistoryScreenActivity::class.java)
                    startActivity(Intent)
                }
                2 -> {
                    val Intent = Intent(this, LoginScreenActivity::class.java)
                    startActivity(Intent)
                }
            }
        }
    }
}