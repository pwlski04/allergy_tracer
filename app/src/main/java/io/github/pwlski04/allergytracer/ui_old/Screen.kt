/*package io.github.pwlski04.allergytracer.ui_old

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.pwlski04.allergytracer.ui.screens.disclaimer.Page_Disclaimer_Text
import io.github.pwlski04.allergytracer.ui.screens.disclaimer.Page_Disclaimer_Verification
import io.github.pwlski04.allergytracer.ui_old.pages.products.Page_Products
import io.github.pwlski04.allergytracer.ui_old.pages.ranking.Page_Ingredients_Ranking
import io.github.pwlski04.allergytracer.ui_old.pages.settings.Page_Settings
import io.github.pwlski04.allergytracer.ui_old.theme.AppTheme.colors


@Composable
fun Screen(){
    val productListState = remember { ProductListState() }

    var page by rememberSaveable { mutableIntStateOf(0) }
    var changePageTo: (Int) -> Unit = { page = it}

    Column(modifier = Modifier.fillMaxSize().background(colors.background)){
        Column(modifier = Modifier.fillMaxWidth().weight(1f).statusBarsPadding()){
            // AllergyTracer Logo
            Row(modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 32.dp, top = 8.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Text("Allergy", fontSize = 24.sp)
                Text("Tracer", color = colors.accent, fontSize = 24.sp)
            }

            // Page content
            PageWrapper(page){
                when (page){
                    -2 -> Page_Disclaimer_Text( changePageTo = changePageTo)
                    -1-> Page_Disclaimer_Verification( changePageTo = changePageTo)
                    0 -> Page_Products( productListState )
                    1 -> Page_Ingredients_Ranking( productListState )
                    2 -> Page_Settings( changePageTo = changePageTo, productListState )
                }
            }
        }

        // NavBar
        if(page > -1){
            NavBar(changePageTo = changePageTo)
        }
    }
}

@Composable
fun PageWrapper(
    page: Int,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
){
    val scrollState = remember(page) { ScrollState(0) }

    Column(
        modifier = modifier.fillMaxSize().verticalScroll(scrollState).padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content
    )
}

@Composable
fun NavBar( changePageTo: (Int) -> Unit ){
    Column(modifier = Modifier.fillMaxWidth()){
        HorizontalDivider(thickness = 1.dp, color = colors.separation)
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            Column(
                modifier = Modifier.weight(1f).clip(RoundedCornerShape(16.dp)).clickable(onClick = { changePageTo(0) }).padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Box(modifier = Modifier.size(18.dp).border(width = 1.dp, color = colors.foreground))
                Text("Products", fontSize = 14.sp)
            }
            Column(
                modifier = Modifier.weight(1f).clip(RoundedCornerShape(16.dp)).clickable(onClick = { changePageTo(1) }).padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Box(modifier = Modifier.size(18.dp).border(width = 1.dp, color = colors.foreground))
                Text("Ingredients", fontSize = 14.sp)
            }
            Column(
                modifier = Modifier.weight(1f).clip(RoundedCornerShape(16.dp)).clickable(onClick = { changePageTo(2) }).padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Box(modifier = Modifier.size(18.dp).border(width = 1.dp, color = colors.foreground))
                Text("Settings", fontSize = 14.sp)
            }
        }
    }
}
 */