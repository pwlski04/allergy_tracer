package io.github.pwlski04.allergytracer.ui.theme

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColors(
    val background: Color,      // background
    val foreground: Color,      // text
    val standout1: Color,       // buttons, cards
    val standout2: Color,       // standout from buttons and cards
    val separation: Color,      // borders, light text
    val accent: Color,          // app accent color
    val alert: Color            // alert color
)

@Composable
fun appColors(isDark: Boolean = true) : AppColors = if (isDark){
    // TODO: create actual dark mode button
    AppColors(
        background = page_background_dark,
        foreground = text_main_dark,
        standout1 = grey_dark_1,
        standout2 = grey_dark_2,
        separation = grey_medium_1,
        accent = accentColor_dark,
        alert = alertColor_dark,
    )
} else {
    // TODO: add light mode
    AppColors(
        background = page_background_dark,
        foreground = text_main_dark,
        standout1 = grey_dark_1,
        standout2 = grey_dark_2,
        separation =  grey_medium_1,
        accent = accentColor_dark,
        alert = alertColor_dark,
    )
}

val LocalAppColors = staticCompositionLocalOf<AppColors>{
    error("LocalApColors not provided => wrap content in AppTheme { }")
}

@Composable
fun AppTheme(isDark: Boolean = true, content: @Composable () -> Unit){
    val colors = appColors(isDark)
    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalContentColor provides colors.foreground
    ) {
        content()
    }
}

object AppTheme{
    val colors: AppColors
        @Composable get() = LocalAppColors.current
}