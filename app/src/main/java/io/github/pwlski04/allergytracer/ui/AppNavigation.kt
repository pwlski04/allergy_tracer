package io.github.pwlski04.allergytracer.ui

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.pwlski04.allergytracer.data.local.configuration.ConfigurationRepository
import io.github.pwlski04.allergytracer.data.local.configuration.ConfigurationStore
import io.github.pwlski04.allergytracer.data.local.products.AppDatabase
import io.github.pwlski04.allergytracer.ui.screens.disclaimer.Page_Disclaimer_Text
import io.github.pwlski04.allergytracer.ui.screens.disclaimer.Page_Disclaimer_Verification
import io.github.pwlski04.allergytracer.ui.screens.main.Page_Ingredients_Ranking
import io.github.pwlski04.allergytracer.ui.screens.main.Page_Products
import io.github.pwlski04.allergytracer.ui.screens.main.Page_Settings
import io.github.pwlski04.allergytracer.ui.theme.AppTheme.colors
import io.github.pwlski04.allergytracer.ui.viewModels.ConfigurationViewModel
import io.github.pwlski04.allergytracer.ui.viewModels.SavedProductViewModel

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Composable
fun AppNavigation(){
    val context = LocalContext.current
    val navController = rememberNavController()

    data class Tab(val route: String, val label: String, val icon: ImageVector)
    val disclaimerTabs = listOf(
        Tab("disclaimerText", "Disclaimer", Icons.Default.Home),
        Tab("disclaimerVerification", "Verification", Icons.Default.Home),
    )
    val appTabs = listOf(
        Tab("products", "Products", Icons.Default.Home),
        Tab("risks", "Risks", Icons.Default.Home),
        Tab("settings", "Settings", Icons.Default.Settings)
    )

    val configurationViewModel: ConfigurationViewModel = viewModel(factory = viewModelFactory { initializer { ConfigurationViewModel(
        ConfigurationRepository(ConfigurationStore(context.dataStore))) } })
    val disclaimerAccepted = configurationViewModel.disclaimerAccepted.collectAsStateWithLifecycle()

    val savedProductDao = remember { AppDatabase.get(context).savedProductDao() }
    val savedProductViewModel: SavedProductViewModel = viewModel(factory = viewModelFactory { initializer { SavedProductViewModel(savedProductDao) } } )
    val productList = savedProductViewModel.productList.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = colors.background,
         bottomBar = {
             if (disclaimerAccepted.value) {
                 Column {
                     HorizontalDivider(thickness = 1.dp, color = colors.separation)
                     NavigationBar(containerColor = colors.background) {
                         val backStackEntry by navController.currentBackStackEntryAsState()
                         val currentRoute = backStackEntry?.destination?.route

                         appTabs.forEach { tab ->
                             NavigationBarItem(
                                 selected = currentRoute?.startsWith(tab.route) == true,
                                 onClick = {
                                     if(currentRoute != tab.route){
                                         navController.navigate(tab.route) {
                                             popUpTo(navController.graph.startDestinationId) { saveState = true }
                                             launchSingleTop = true
                                             restoreState = true
                                         }
                                     }
                                 },
                                 icon = { Icon(tab.icon, contentDescription = tab.label) },
                                 label = { Text(tab.label) },
                                 colors = NavigationBarItemDefaults.colors(
                                     selectedIconColor = colors.foreground,
                                     selectedTextColor = colors.foreground,
                                     unselectedIconColor = colors.foreground,
                                     unselectedTextColor = colors.foreground,
                                     indicatorColor = colors.standout2
                                 )
                             )
                         }
                     }
                 }
             }
        }
    ) { padding ->
        NavHost(navController, startDestination = if (disclaimerAccepted.value) "products" else "disclaimerText", modifier = Modifier.padding(padding).verticalScroll(rememberScrollState()).padding(horizontal = 16.dp, vertical = 16.dp)){

            composable("disclaimerText"){
                Page_Disclaimer_Text(onSubmit = {
                    navController.navigate(if (disclaimerAccepted.value) "settings" else "disclaimerVerification")
                })
            }
            composable("disclaimerVerification"){
                Page_Disclaimer_Verification( onSubmit = {
                    configurationViewModel.setDisclaimerAccepted(true)
                    navController.navigate("products")
                })
            }
            composable("products"){
                Page_Products(savedProductViewModel)
            }
            composable("risks"){
                Page_Ingredients_Ranking(productList.value)
            }
            composable("settings"){
                Page_Settings(savedProductViewModel, onDisclaimerReview = { navController.navigate("disclaimerText") })
            }
        }
    }
}