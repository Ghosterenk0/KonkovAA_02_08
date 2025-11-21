package ru.ghosternko.konkovaa

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "orders")
data class Orders(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val count: Int,
)
@Dao
interface OrdersDao {
    @Insert
    suspend fun add(order: Orders)

    @Query("SELECT * FROM orders ORDER BY id DESC")
    fun getAllOrders(): List<Orders>
}
@Database(entities = [Orders::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ordersDao(): OrdersDao
}