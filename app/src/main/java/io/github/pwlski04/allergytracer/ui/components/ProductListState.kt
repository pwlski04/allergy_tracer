package io.github.pwlski04.allergytracer.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.pwlski04.allergytracer.ui.pages.products.Product

class ProductListState{
    val testProducts = listOf(
        // --- REACTED (reacted = true) ---
        Product(
            1,
            "BrandA",
            "Scented Lotion",
            setOf("Aqua", "Glycerin", "Linalool", "Limonene"),
            reacted = true
        ),
        Product(
            2,
            "BrandB",
            "Floral Cream",
            setOf("Aqua", "Glycerin", "Linalool", "Citronellol"),
            reacted = true
        ),
        Product(
            3,
            "BrandC",
            "Perfumed Serum",
            setOf("Aqua", "Linalool", "Dimethicone"),
            reacted = true
        ),
        Product(
            4,
            "BrandD",
            "Rose Moisturizer",
            setOf("Aqua", "Glycerin", "Linalool", "Geraniol"),
            reacted = true
        ),

        // --- NON-REACTED (reacted = false) ---
        Product(
            5,
            "BrandE",
            "Plain Moisturizer",
            setOf("Aqua", "Glycerin", "Dimethicone"),
            reacted = false
        ),
        Product(
            6,
            "BrandF",
            "Basic Cream",
            setOf("Aqua", "Glycerin", "Cetearyl Alcohol"),
            reacted = false
        ),
        Product(
            7,
            "BrandG",
            "Fragrance-Free Gel",
            setOf("Aqua", "Glycerin", "Niacinamide"),
            reacted = false
        ),
        Product(
            8,
            "BrandH",
            "Simple Lotion",
            setOf("Aqua", "Dimethicone", "Panthenol"),
            reacted = false
        ),
        Product(
            9,
            "BrandI",
            "Unscented Balm",
            setOf("Aqua", "Glycerin", "Shea Butter", "Linalool"),
            reacted = false
        )
    )


    val productList = mutableStateListOf<Product>().apply { addAll(testProducts) }
    var currentlyEditing: Boolean by mutableStateOf(false)

    fun save(product: Product){
        productList.add(product)
    }

    fun delete(product: Product){
        productList.remove(product)
    }

    fun edit(product: Product){
        currentlyEditing = true;
    }

    fun loadSample(){
        productList.apply { addAll(testProducts) }
    }

    fun clear(){
        productList.clear()
    }
}