package io.github.pwlski04.allergytracer.ui.pages.disclaimer_text

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.pwlski04.allergytracer.ui.components.BigButton
import io.github.pwlski04.allergytracer.ui.components.Content_Group_withTitle
import io.github.pwlski04.allergytracer.ui.components.Content_List
import io.github.pwlski04.allergytracer.ui.components.Content_Surface
import io.github.pwlski04.allergytracer.ui.components.Text_H1
import io.github.pwlski04.allergytracer.ui.components.Text_Title
import io.github.pwlski04.allergytracer.ui.theme.AppTheme.colors

@Composable
fun ColumnScope.Page_Disclaimer_Verification( changePageTo: (Int) -> Unit){
    var chosenMC by remember { mutableIntStateOf(-1) }

    Content_Group_withTitle({
        Text_Title("Disclaimer")
        Text_H1("According to the disclaimer, AllergyTracer...")
        Content_List({
            List_Element_MC(modifier = Modifier.clickable(onClick = {chosenMC = 0 }),"...gives my an official medical diagnosis for my allergies.")
            List_Element_MC(modifier = Modifier.clickable(onClick = {chosenMC = 1 }),"...only helps me notice patterns in my history, but is not a medical tool.")
            List_Element_MC(modifier = Modifier.clickable(onClick = {chosenMC = 2 }),"...guarantees which ingredients are safe for me to use.")
        })
    })

    Spacer(modifier = Modifier.weight(1f))

    BigButton("Continue", enabled = chosenMC == 1, onClick = { changePageTo(0)})
}

@Composable
fun List_Element_MC(modifier: Modifier, text: String){
    Content_Surface(
        modifier = modifier.fillMaxWidth(),
        left = {
            Box(modifier = Modifier.size(10.dp).clip(RoundedCornerShape(16.dp)).background(colors.separation))
        },
        middle = {
            Text(text, fontSize = 14.sp)
        },
        right = {

        }
    )

}