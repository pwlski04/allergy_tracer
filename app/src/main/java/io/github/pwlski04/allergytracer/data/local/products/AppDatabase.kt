package io.github.pwlski04.allergytracer.data.local.products

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

class IngredientSetConverters {
    @TypeConverter
    fun fromIngredientSet(ingredients: Set<String>): String =
        ingredients.joinToString(",")

    @TypeConverter
    fun toIngredientSet(data: String): Set<String> =
        if (data.isEmpty()) emptySet() else data.split(",").toSet()
}

@Database(entities = [Product::class], version = 1)
@TypeConverters(IngredientSetConverters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun savedProductDao(): SavedProductDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun get(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder<AppDatabase>(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "products.db",
                ).build().also { INSTANCE = it }
            }
    }
}