package com.dicoding.jetreward

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dicoding.jetreward.navigation.NavigationItem
import com.dicoding.jetreward.navigation.Screen
import com.dicoding.jetreward.ui.screen.cart.CartScreen
import com.dicoding.jetreward.ui.screen.home.HomeScreen
import com.dicoding.jetreward.ui.screen.profile.ProfileScreen
import com.dicoding.jetreward.ui.theme.JetRewardTheme

@Composable
fun JetRewardApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) {
        it
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(it)
        ){
            composable(Screen.Home.route){
                HomeScreen()
            }
            composable(Screen.Cart.route){
                CartScreen()
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }
        }
    }
}

//for bottom bar
@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home

            ),
            NavigationItem(
                title = stringResource(R.string.menu_cart),
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Cart
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                selected = currentRoute== item.screen.route,
                onClick = {
                          navController.navigate(item.screen.route){
                              popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                              }
                              restoreState = true
                              launchSingleTop = true
                          }
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    JetRewardTheme {
        JetRewardApp()
    }
}
