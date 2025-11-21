package ru.ghosternko.konkovaa

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderScreenActivity : AppCompatActivity() {
    val listProduct = listOf("Роллы", "Пицца", "Кофе", "Чай")
    var titleItem: String = listProduct[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_screen)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val et_count = findViewById<EditText>(R.id.et_count)
        val btn_order = findViewById<Button>(R.id.btn_order)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listProduct
        )
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                titleItem = listProduct[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) { }
        }


        btn_order.setOnClickListener {
            val countText = et_count.text.toString()
            if (countText.isNotEmpty() && countText.toInt() > 0) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val db = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java,
                        "orders.db"
                    ).build()

                    val ordersDao = db.ordersDao()
                    val newOrder = Orders(
                        id = 0,
                        title = titleItem,
                        count = countText.toInt()
                    )
                    ordersDao.add(newOrder)

                    db.close()

                }
                Toast.makeText(this, "Заказ добавлен", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Некорректный ввод количества", Toast.LENGTH_SHORT).show()
            }
        }
    }
}