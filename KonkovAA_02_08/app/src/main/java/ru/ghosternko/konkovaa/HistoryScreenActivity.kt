package ru.ghosternko.konkovaa

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class HistoryScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_screen)

        val listView = findViewById<ListView>(R.id.lv_his)

        lifecycleScope.launch {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "orders.db"
            ).build()

            val ordersDao = db.ordersDao()
            val orders = ordersDao.getAllOrders()
            val orderStrings = orders.map { order ->
                "${order.id} - ${order.title} - ${order.count}"
            }.toList()

            val adapter = ArrayAdapter(
                this@HistoryScreenActivity,
                android.R.layout.simple_list_item_1,
                orderStrings,
            )
            listView.adapter = adapter

            db.close()
        }
    }
}