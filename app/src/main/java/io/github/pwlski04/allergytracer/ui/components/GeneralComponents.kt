package io.github.pwlski04.allergytracer.ui.components

import android.util.Pair
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.pwlski04.allergytracer.ui.theme.AppTheme.colors


@Composable
fun BigButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit
){
    Box(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(if (enabled) colors.accent else colors.standout1).padding(16.dp).then(if (enabled) Modifier.clickable(onClick = onClick) else Modifier),
        contentAlignment = Alignment.Center
    ){
        Text(text, color = if (enabled) colors.foreground else colors.separation, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
    }
}


@Composable
fun Content_List(content: @Composable () -> Unit){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        content()
    }
}


@Composable
fun Content_Surface(
    modifier: Modifier = Modifier.fillMaxWidth(),
    left: (@Composable () -> Unit)? = null,
    middle: (@Composable () -> Unit)? = null,
    right: (@Composable () -> Unit)? = null,
    bottom: (@Composable () -> Unit)? = null,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    boxColor: Color = colors.standout1,
    borderColor: Color = colors.separation
){
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(boxColor)
            .border(0.5.dp, borderColor, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ){
        Row(
            verticalAlignment = verticalAlignment,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            left?.invoke()
            Box(modifier = Modifier.weight(1f)){
                middle?.invoke()
            }
            right?.invoke()
        }

        if(bottom != null) {
            Box(modifier = Modifier.fillMaxWidth().padding(top = 20.dp, start = 16.dp, end = 16.dp)) {       // clear separation
                bottom()
            }
        }
    }
}
@Composable
fun Content_Surface_Overview(content: @Composable () -> Unit){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        content()
    }
}


@Composable
fun Content_Surface_List(
    items: List<Pair<@Composable () -> Unit, @Composable () -> Unit>>
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(colors.standout1)
            .border(0.5.dp, colors.separation, RoundedCornerShape(16.dp))
    ){
        items.forEachIndexed { index, item -> Content_Surface_Item(left = item.first, middle = item.second, topBorder = index != 0)}
    }
}

@Composable
fun Content_Surface_Item(
    left: @Composable () -> Unit = {},
    middle: @Composable () -> Unit = {},
    right: @Composable () -> Unit = {},
    topBorder: Boolean = true
){
    Column(modifier = Modifier.fillMaxWidth()){
        if(topBorder)
            Box(modifier = Modifier.fillMaxWidth().height(0.5.dp).background(colors.separation))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            left()
            Box(modifier = Modifier.weight(1f)){
                middle()
            }
            right()
        }
    }
}


@Composable
fun Content_Group_withTitle(groupContent: @Composable () -> Unit){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ){
        groupContent()
    }
}
@Composable
fun Content_Group_withH2(groupContent: @Composable () -> Unit){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        groupContent()
    }
}


@Composable
fun Text_Title(text: String){
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart){
        Text(text, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}
@Composable
fun Text_Default(text: AnnotatedString){
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart){
        Text(text, fontSize = 14.sp)
    }
}
@Composable
fun Text_H1(text: String){
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        Text(text, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    }
}
@Composable
fun Text_H2(text: String, extra: String? = null){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
        Text(text, color = colors.separation, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        if(extra != null) Text(extra, color = colors.separation, fontSize = 12.sp)
    }
}