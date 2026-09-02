package io.github.pwlski04.allergytracer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.github.pwlski04.allergytracer.ui.AppNavigation
import io.github.pwlski04.allergytracer.ui.theme.AppTheme

class MainActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            AppTheme{
                AppNavigation()         //Screen()
            }
        }
    }
}