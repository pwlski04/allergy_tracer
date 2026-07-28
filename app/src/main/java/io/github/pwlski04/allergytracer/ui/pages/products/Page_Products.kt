package io.github.pwlski04.allergytracer.ui.pages.products

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.pwlski04.allergytracer.ui.components.BigButton
import io.github.pwlski04.allergytracer.ui.components.Content_Group_withH2
import io.github.pwlski04.allergytracer.ui.components.Content_Group_withTitle
import io.github.pwlski04.allergytracer.ui.components.Content_Surface
import io.github.pwlski04.allergytracer.ui.components.Content_Surface_List
import io.github.pwlski04.allergytracer.ui.components.Content_Surface_Overview
import io.github.pwlski04.allergytracer.ui.components.ProductListState
import io.github.pwlski04.allergytracer.ui.components.Text_H2
import io.github.pwlski04.allergytracer.ui.components.Text_Title
import io.github.pwlski04.allergytracer.ui.theme.AppTheme.colors

data class Product(
    val id: Int,
    val brand: String,
    val name: String,
    val ingredients: Set<String>,
    val reacted: Boolean
)


@Composable
fun Page_Products( state: ProductListState) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(48.dp)
    ){
        with(state){
            Add_Product_Group(::save, currentlyEditing)        // same as Add_Product_Group(addProduct = { product -> addProduct(product) }
            Logged_Product_Group(productList, ::delete)
        }
    }
}



@Composable
fun Add_Product_Group(saveNewProduct: (Product) -> Unit,
                      currentlyEditing: Boolean, saveExistingProduct: (Product) -> Product = { Product(-1, "", "", emptySet(), false)}){
    // current draft
    var id: Int = 0;
    var brand by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var ingredient: String by remember { mutableStateOf("") }
    val ingredients = remember { mutableStateSetOf<String>() }
    var reacted by remember { mutableStateOf<Boolean?>(null) }

    Content_Group_withTitle({
        Text_Title("Add product")

        Content_Surface(
            // TODO: ADD FUNCTIONALITY
            left = {
                Box(modifier = Modifier.size(18.dp).border(width = 1.dp, color = colors.foreground))
            },
            middle = {
                Text("Add photo", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            },
            modifier = Modifier.fillMaxWidth().clickable(onClick = {})
        )

        Content_Surface_List(
            listOf(
                Pair(
                    { Box(modifier = Modifier.size(18.dp).border(width = 1.dp, color = colors.separation)) },
                    { BasicTextField(
                        value = brand,
                        onValueChange = { brand = it },
                        singleLine = true,
                        textStyle = TextStyle(color = Color(0xFFF5F3F7), fontSize = 14.sp),
                        cursorBrush = SolidColor(Color(0xFFA855F7)),
                        modifier = Modifier.fillMaxWidth()
                    )
                        if (brand.isEmpty()) {
                            Text("Brand", color = Color(0xFF8A8492), fontSize = 14.sp)
                        }
                    }
                ),
                Pair(
                    { Box(modifier = Modifier.size(18.dp).border(width = 1.dp, color = colors.separation)) },
                    { BasicTextField(
                        value = name,
                        onValueChange = { name = it },
                        singleLine = true,
                        textStyle = TextStyle(color = Color(0xFFF5F3F7), fontSize = 14.sp),
                        cursorBrush = SolidColor(Color(0xFFA855F7)),
                        modifier = Modifier.fillMaxWidth()
                    )
                        if (name.isEmpty()) {
                            Text("Product name", color = Color(0xFF8A8492), fontSize = 14.sp)
                        }
                    }
                )
            )
        )

        Content_Group_withH2({
            Content_Surface(
                left = {
                    Box(modifier = Modifier.size(18.dp).border(width = 1.dp, color = colors.separation))
                },
                middle = {
                    BasicTextField(
                        value = ingredient,
                        onValueChange = { ingredient = it },
                        singleLine = true,
                        textStyle = TextStyle(color = Color(0xFFF5F3F7), fontSize = 14.sp),
                        cursorBrush = SolidColor(Color(0xFFA855F7)),
                        modifier = Modifier.fillMaxWidth()
                    )
                        if (ingredient.isEmpty()) {
                            Text("Add ingredient", color = Color(0xFF8A8492), fontSize = 14.sp)
                        }
                },
                right = {
                    Box(modifier = Modifier.size(24.dp).border(width = 1.dp, color = colors.separation).clickable(onClick = {
                        if(!ingredient.isEmpty()){      // TODO: stricter requirements => such as handling spaces; autocomplete
                            ingredients.add(ingredient)
                            ingredient = ""
                        }
                    }),
                    )
                }
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                ingredients.forEach { item ->
                    Row(
                        modifier = Modifier.clip(RoundedCornerShape(16.dp)).border(1.dp, colors.separation, RoundedCornerShape(16.dp)).padding(vertical = 8.dp, horizontal = 16.dp).clickable(onClick = {
                            ingredients.remove(item)
                        }),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(text = item, color = colors.separation, fontSize = 14.sp)
                        Text("X", color = colors.separation)
                    }
                }
            }
        })

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ){
            Content_Surface(
                left = {
                    Box(modifier = Modifier.size(16.dp).clip(RoundedCornerShape(16.dp)).background(colors.alert))
                },
                middle = {
                    Text("Reacted", fontSize = 14.sp)
                },
                modifier = Modifier.weight(1f).clickable(onClick = { reacted = if (reacted == true) null else true }),
                boxColor = if(reacted == true) colors.alert.copy(0.33f) else colors.standout1,
                borderColor = if (reacted == true) colors.alert else colors.separation
            )
            Content_Surface(
                left = {
                    Box(modifier = Modifier.size(16.dp).clip(RoundedCornerShape(16.dp)).background(colors.accent))
                },
                middle = {
                    Text("No reaction", fontSize = 14.sp)
                },
                modifier = Modifier.weight(1f).clickable(onClick = { reacted = if (reacted == false) null else false }),
                boxColor = if(reacted == false) colors.accent.copy(0.33f) else colors.standout1,
                borderColor = if (reacted == false) colors.accent else colors.separation
            )
        }
        BigButton("Save product", enabled = (!brand.isEmpty() && !name.isEmpty() && !ingredients.isEmpty() && reacted != null), onClick = {
            saveNewProduct(Product(id, brand, name, ingredients, reacted!!)) //TODO NULL CHECK
        })
    })
}



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Logged_Product_Group(productList: MutableList<Product>, deleteSavedProduct: (Product) -> Unit){
    Content_Group_withH2({
        Text_H2("LOGGED PRODUCTS", "${productList.size} products logged")     // TODO: make number automatic

        productList.forEach { product ->
            var expanded by remember { mutableStateOf(false) }

            Logged_Product(
                expanded,
                product,
                deleteSavedProduct,
                onClick = { expanded = !expanded }
            )
        }
    })
}



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Logged_Product(expanded: Boolean, product: Product, deleteSavedProduct: (Product) -> Unit, onClick: () -> Unit){
    Content_Surface(
        modifier = Modifier.fillMaxWidth().clickable(onClick = { onClick() }),
        left = {
            Box(modifier = Modifier.size(18.dp).border(width = 1.dp, color = colors.separation))
        },
        middle = {
            Content_Surface_Overview({
                Text(product.name, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Text(product.brand, color = colors.separation, fontSize = 14.sp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    Box(modifier = Modifier.size(8.dp).clip(RoundedCornerShape(16.dp)).background(if (product.reacted) colors.alert else colors.accent))
                    Text(text = if (product.reacted) "Reacted" else "No reaction" + " · ${product.ingredients.size} ingredients", color = colors.separation, fontSize = 14.sp)
                }
            })
        },
        right = {
            Box(modifier = Modifier.size(18.dp).border(width = 1.dp, color = colors.separation))
        },
        bottom =
            if (expanded){
                {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ){
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ){
                            product.ingredients.forEach { item ->
                                Box(modifier = Modifier.clip(RoundedCornerShape(16.dp)).border(1.dp, colors.separation, RoundedCornerShape(16.dp)).padding(vertical = 4.dp, horizontal = 8.dp),){
                                    Text(text = item, color = colors.separation, fontSize = 14.sp)
                                }
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Box(
                                modifier = Modifier.clickable(onClick = { }),
                                // todo add pressing effect
                                contentAlignment = Alignment.Center
                            ){
                                Text("Edit product", color = colors.accent, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                            }
                            Box(
                                modifier = Modifier.clickable(onClick = { deleteSavedProduct(product) }),
                                contentAlignment = Alignment.Center
                            ){
                                Text("Delete", color = colors.alert, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
            } else null,
        verticalAlignment = Alignment.Top
    )
}