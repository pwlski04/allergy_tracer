package io.github.pwlski04.allergytracer.ui.screens.main

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.pwlski04.allergytracer.ui.Content_Group_withH2
import io.github.pwlski04.allergytracer.ui.Content_Group_withTitle
import io.github.pwlski04.allergytracer.ui.Content_Surface
import io.github.pwlski04.allergytracer.ui.Text_H2
import io.github.pwlski04.allergytracer.ui.Text_Title
import io.github.pwlski04.allergytracer.ui.viewModels.SavedProductViewModel
import io.github.pwlski04.allergytracer.ui.theme.AppTheme.colors

@Composable
fun Page_Settings( viewModel: SavedProductViewModel, onDisclaimerReview: () -> Unit ){
    Content_Group_withTitle({
        Text_Title("Settings")

        Content_Group_withH2({
            Text_H2("YOUR DATA")

            Content_Surface(
                left = {
                    Text("Export all data", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                },
                right = {
                    Box(
                        modifier = Modifier.size(18.dp).border(width = 1.dp, color = colors.separation)
                    )
                }
            )

            Content_Surface(
                left = {
                    Text("Import data", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                },
                right = {
                    Box(
                        modifier = Modifier.size(18.dp).border(width = 1.dp, color = colors.separation)
                    )
                }
            )
        })

        Content_Group_withH2({
            Text_H2("GENERAL")

            Content_Surface(
                modifier = Modifier.fillMaxWidth().clickable(onClick = { viewModel.loadSample() }),
                left = {
                    Text("Load sample products", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                },
                right = {
                    Box(
                        modifier = Modifier.size(18.dp).border(width = 1.dp, color = colors.separation)
                    )
                }
            )

            Content_Surface(
                modifier = Modifier.fillMaxWidth().clickable(onClick = { viewModel.clear() }),
                left = {
                    Text("Clear all products", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                },
                right = {
                    Box(
                        modifier = Modifier.size(18.dp).border(width = 1.dp, color = colors.separation)
                    )
                },
                boxColor = colors.alert.copy(alpha = 0.33f),
                borderColor = colors.alert
            )
        })

        Content_Group_withH2({
            Text_H2("ABOUT")

            Content_Surface(
                modifier = Modifier.fillMaxWidth().clickable(onClick =  { onDisclaimerReview() }),
                left = {
                    Text("Review disclaimer", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                },
                right = {
                    Box(
                        modifier = Modifier.size(18.dp).border(width = 1.dp, color = colors.separation)
                    )
                }
            )
        })

        Content_Group_withH2({
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text("AllergyTracer v1.0", color = colors.separation, fontSize = 12.sp)
                Text("Not a diagnostic tool", color = colors.separation, fontSize = 12.sp)
            }
        })
    })

}