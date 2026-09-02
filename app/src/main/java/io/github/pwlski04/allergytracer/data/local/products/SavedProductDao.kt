package io.github.pwlski04.allergytracer.data.local.products

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedProductDao {
    @Insert
    suspend fun insert(product: Product)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(productList: List<Product>)


    @Delete
    suspend fun delete(product: Product)

    @Query("DELETE FROM saved_products")
    suspend fun deleteAll()


    @Update
    suspend fun edit(product: Product)

    @Query("SELECT * FROM saved_products ORDER BY id")
    fun getAll(): Flow<List<Product>>

}