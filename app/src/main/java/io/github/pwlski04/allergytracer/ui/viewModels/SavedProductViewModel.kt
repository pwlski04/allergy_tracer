package io.github.pwlski04.allergytracer.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.pwlski04.allergytracer.data.local.products.Product
import io.github.pwlski04.allergytracer.data.local.products.SavedProductDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class SavedProductViewModel(private val dao: SavedProductDao): ViewModel() {
    val testProducts = listOf(
        // --- REACTED (reacted = true) ---
        Product(
            1,
            "BrandA",
            "Scented Lotion",
            setOf("Aqua", "Glycerin", "Linalool", "Limonene"),
            reacted = true,
            imagePath = null
        ),
        Product(
            2,
            "BrandB",
            "Floral Cream",
            setOf("Aqua", "Glycerin", "Linalool", "Citronellol"),
            reacted = true,
            imagePath = null
        ),
        Product(
            3,
            "BrandC",
            "Perfumed Serum",
            setOf("Aqua", "Linalool", "Dimethicone"),
            reacted = true,
            imagePath = null
        ),
        Product(
            4,
            "BrandD",
            "Rose Moisturizer",
            setOf("Aqua", "Glycerin", "Linalool", "Geraniol"),
            reacted = true,
            imagePath = null
        ),

        // --- NON-REACTED (reacted = false) ---
        Product(
            5,
            "BrandE",
            "Plain Moisturizer",
            setOf("Aqua", "Glycerin", "Dimethicone"),
            reacted = false,
            imagePath = null
        ),
        Product(
            6,
            "BrandF",
            "Basic Cream",
            setOf("Aqua", "Glycerin", "Cetearyl Alcohol"),
            reacted = false,
            imagePath = null
        ),
        Product(
            7,
            "BrandG",
            "Fragrance-Free Gel",
            setOf("Aqua", "Glycerin", "Niacinamide"),
            reacted = false,
            imagePath = null
        ),
        Product(
            8,
            "BrandH",
            "Simple Lotion",
            setOf("Aqua", "Dimethicone", "Panthenol"),
            reacted = false,
            imagePath = null
        ),
        Product(
            9,
            "BrandI",
            "Unscented Balm",
            setOf("Aqua", "Glycerin", "Shea Butter", "Linalool"),
            reacted = false,
            imagePath = null
        )
    )


    val productList: StateFlow<List<Product>> = dao.getAll().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val testProductList = mutableStateListOf<Product>().apply { addAll(testProducts) }
    var currentlyEditing: Boolean by mutableStateOf(false)

    fun save(product: Product){
        viewModelScope.launch {
            dao.insert(product)
        }
    }

    fun delete(product: Product){
        viewModelScope.launch {
            dao.delete(product)
        }
    }

    fun edit(previous: Product, updated: Product){
        viewModelScope.launch {
            dao.edit(updated.copy(id = previous.id))
        }
    }

    fun loadSample(){
        viewModelScope.launch {
            dao.deleteAll()
            dao.insertAll(testProducts)
        }
    }

    fun clear(){
        viewModelScope.launch {
            dao.deleteAll()
        }
    }
}