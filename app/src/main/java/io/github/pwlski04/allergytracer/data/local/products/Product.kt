package io.github.pwlski04.allergytracer.data.local.products

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="saved_products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val brand: String,
    val name: String,
    val ingredients: Set<String>,
    val reacted: Boolean,
    val imagePath: String?
)