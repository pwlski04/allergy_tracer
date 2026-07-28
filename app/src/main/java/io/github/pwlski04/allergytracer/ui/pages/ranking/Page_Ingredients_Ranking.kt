package io.github.pwlski04.allergytracer.ui.pages.ranking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.pwlski04.allergytracer.ui.components.Content_Group_withH2
import io.github.pwlski04.allergytracer.ui.components.Content_Group_withTitle
import io.github.pwlski04.allergytracer.ui.components.Content_Surface
import io.github.pwlski04.allergytracer.ui.components.Content_Surface_Overview
import io.github.pwlski04.allergytracer.ui.components.ProductListState
import io.github.pwlski04.allergytracer.ui.components.Text_H2
import io.github.pwlski04.allergytracer.ui.components.Text_Title
import io.github.pwlski04.allergytracer.ui.theme.AppTheme.colors

@Composable
fun Page_Ingredients_Ranking( state: ProductListState ){
    with (state){
        val totalReactions = productList.count{ anyProduct -> anyProduct.reacted }
        val totalNonReactions = productList.size - totalReactions

        Content_Group_withTitle({
            Text_Title("Ingredient ranking")
            Content_Group_withH2({
                Text_H2("SUSPECTS, MOST TO LEAST")

                productList.flatMap{it.ingredients}.distinct().map{ ingredient ->
                    val totalReactionsContaining = productList.count{ anyProduct -> anyProduct.reacted && anyProduct.ingredients.contains(ingredient) }
                    val totalNonReactionsContaining = productList.count{ anyProduct -> !anyProduct.reacted && anyProduct.ingredients.contains(ingredient) }

                    val reactionRate: Float = if (totalReactions > 0) totalReactionsContaining.toFloat() / totalReactions else 1f
                    val nonReactionRate: Float = if (totalNonReactions > 0)totalNonReactionsContaining.toFloat() / totalNonReactions else 1f
                    val suspicious = reactionRate - nonReactionRate

                    Triple(ingredient, totalReactionsContaining, suspicious)
                }.sortedByDescending { it.third }.forEachIndexed { index, (ingredient, totalReactionsContaining, suspicious) ->
                    Ingredient_Card(index + 1,ingredient, totalReactionsContaining = totalReactionsContaining, suspicious = suspicious, totalReactions = totalReactions)
                }
            })
        })
    }
}

@Composable
fun Ingredient_Card(idx: Int, name: String, totalReactionsContaining: Int, suspicious: Float = 1f, totalReactions: Int) {
    Content_Surface(middle = {
        Content_Surface_Overview({
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier.widthIn(min = 20.dp),
                    text = "${idx}.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
            Text("Appeared in $totalReactionsContaining of your $totalReactions reactions", color = colors.separation, fontSize = 14.sp)
            Box(modifier = Modifier.fillMaxWidth()){
                Box(modifier = Modifier.height(8.dp).fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(colors.standout2))
                Box(modifier = Modifier.height(8.dp).fillMaxWidth((suspicious+1)/2).clip(RoundedCornerShape(16.dp)).background(if (suspicious > 0) colors.accent else colors.separation))
            }
        })
    })
}